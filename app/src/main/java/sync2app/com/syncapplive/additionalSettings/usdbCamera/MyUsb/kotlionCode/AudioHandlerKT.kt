package sync2app.com.syncapplive.additionalSettings.usdbCamera.MyUsb.kotlionCode

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import android.media.audiofx.AcousticEchoCanceler
import android.media.audiofx.AutomaticGainControl
import android.media.audiofx.BassBoost
import android.media.audiofx.NoiseSuppressor
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.*


class AudioHandlerKT(private val context: Context) {

    private var record: AudioRecord? = null
    private var player: AudioTrack? = null
    private var bufferSize = 0
    private var isRecording = false
    private val sampleRate = 48000
    private val channelIn = AudioFormat.CHANNEL_IN_STEREO
    private val channelOut = AudioFormat.CHANNEL_OUT_STEREO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT

    // CoroutineScope with IO dispatcher for background operations
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        // Initialize audio components
        coroutineScope.launch {
            initAudio()
        }
    }

    private suspend fun initAudio() = withContext(Dispatchers.IO) {
        try {
            bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelIn, audioFormat)
            if (bufferSize == AudioRecord.ERROR || bufferSize == AudioRecord.ERROR_BAD_VALUE) {
                bufferSize = 4096 * 8
            }
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.e(TAG, "Permission to record audio is not granted")
                return@withContext
            }
            record = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                sampleRate,
                channelIn,
                audioFormat,
                bufferSize
            )
            player = AudioTrack(
                AudioAttributes.Builder()
                    .setLegacyStreamType(AudioTrack.MODE_STREAM)
                    .setFlags(AudioAttributes.FLAG_LOW_LATENCY)
                    .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build(),
                AudioFormat.Builder()
                    .setEncoding(audioFormat)
                    .setSampleRate(sampleRate)
                    .setChannelMask(channelOut)
                    .build(),
                bufferSize,
                AudioTrack.MODE_STREAM,
                AudioManager.AUDIO_SESSION_ID_GENERATE
            )
            val audioSessionId = record!!.audioSessionId
            AcousticEchoCanceler.create(audioSessionId)?.apply { enabled = true }
            NoiseSuppressor.create(audioSessionId)?.apply { enabled = true }
            AutomaticGainControl.create(audioSessionId)?.apply { enabled = true }
            BassBoost(1, player!!.audioSessionId).apply { setStrength(1000.toShort()) }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing audio: ${e.message}", e)
        }
    }

    fun startAudio() {
        coroutineScope.launch(Dispatchers.IO) { // Ensure operations run on a background thread
            try {
                if (record?.state == AudioRecord.STATE_INITIALIZED && player?.state == AudioTrack.STATE_INITIALIZED) {
                    record!!.startRecording()
                    player!!.play()
                    isRecording = true
                    val buffer = ShortArray(bufferSize)
                    while (isRecording) {
                        val read = record!!.read(buffer, 0, bufferSize)
                        if (read > 0) {
                            player!!.write(buffer, 0, read)
                        }
                    }
                } else {
                    Log.e(TAG, "AudioRecord or AudioTrack not initialized")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error in startAudio: ${e.message}", e)
            }
        }
    }

    fun stopAudio() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                isRecording = false
                record?.takeIf { it.recordingState == AudioRecord.RECORDSTATE_RECORDING }?.stop()
                player?.takeIf { it.playState == AudioTrack.PLAYSTATE_PLAYING }?.stop()
            } catch (e: Exception) {
                Log.e(TAG, "Error in stopAudio: ${e.message}", e)
            }
        }
    }

    fun endAudio() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                record?.takeIf { it.recordingState == AudioRecord.RECORDSTATE_RECORDING }?.stop()
                player?.takeIf { it.playState == AudioTrack.PLAYSTATE_PLAYING }?.stop()
                isRecording = false
            } catch (e: Exception) {
                Log.e(TAG, "Error in endAudio: ${e.message}", e)
            }
        }
    }

    fun releaseAudio() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                record?.release()
                player?.release()
                record = null
                player = null
            } catch (e: Exception) {
                Log.e(TAG, "Error in releaseAudio: ${e.message}", e)
            }
        }
    }

    companion object {
        private const val TAG = "AudioHandler"
    }
}
