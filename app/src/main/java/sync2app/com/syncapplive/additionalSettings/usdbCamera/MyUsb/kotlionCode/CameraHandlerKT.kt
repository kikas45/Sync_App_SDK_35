package sync2app.com.syncapplive.additionalSettings.usdbCamera.MyUsb

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.os.Build
import android.util.Log
import android.view.Surface
import android.view.TextureView
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.*
import sync2app.com.syncapplive.additionalSettings.utils.Constants
import kotlin.coroutines.CoroutineContext

class CameraHandlerKT(
    private val context: Context,
    private val cameraManager: CameraManager,
    private val textureView: TextureView,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) {
    private var cameraDevice: CameraDevice? = null
    private var cameraCaptureSession: CameraCaptureSession? = null
    private var captureRequestBuilder: CaptureRequest.Builder? = null
    private var cameraId: String? = null
    private var surfaceTexture: SurfaceTexture? = null
    private var surface: Surface? = null

    private val coroutineScope = CoroutineScope(coroutineContext + Job())

    fun startCamera() {
        coroutineScope.launch {
            try {
                val cameraIds = cameraManager.cameraIdList
                cameraId = if (cameraIds.isNotEmpty()) {
                    cameraIds[0]
                } else {
                    val intent = Intent(Constants.SYNC_CAMERA_DISCONNECTED)
                    context.sendBroadcast(intent)
                    return@launch
                }
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@launch
                }
                openCamera()
            } catch (e: CameraAccessException) {
                Log.e(TAG, "CameraAccessException: ${e.message}", e)
            }
        }
    }

    private suspend fun openCamera() {
        withContext(Dispatchers.Main) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return@withContext
            }
            cameraManager.openCamera(cameraId!!, stateCallback, null)
        }
    }

    private val stateCallback: CameraDevice.StateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            coroutineScope.launch {
                try {
                    cameraDevice = camera
                    Log.d(TAG, "Camera opened.")
                    startPreviewSession()
                } catch (e: Exception) {
                    Log.e(TAG, "Exception in onOpened: ${e.message}", e)
                }
            }
        }

        override fun onDisconnected(camera: CameraDevice) {
            coroutineScope.launch {
                try {
                    camera.close()
                    cameraDevice = null
                    Log.d(TAG, "Camera disconnected.")
                } catch (e: Exception) {
                    Log.e(TAG, "Exception in onDisconnected: ${e.message}", e)
                }
            }
        }

        override fun onError(camera: CameraDevice, error: Int) {
            coroutineScope.launch {
                try {
                    camera.close()
                    cameraDevice = null
                    Log.e(TAG, "Camera error: $error")
                    val intent = Intent(Constants.SYNC_CAMERA_DISCONNECTED)
                    context.sendBroadcast(intent)
                } catch (e: Exception) {
                    Log.e(TAG, "Exception in onError: ${e.message}", e)
                }
            }
        }
    }

    private suspend fun startPreviewSession() {
        withContext(Dispatchers.Main) {
            surfaceTexture = textureView.surfaceTexture
            if (surfaceTexture == null) {
                Log.e(TAG, "SurfaceTexture is null")
                return@withContext
            }
            surface = Surface(surfaceTexture)
            try {
                captureRequestBuilder =
                    cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                captureRequestBuilder!!.addTarget(surface!!)
                val outputConfiguration = OutputConfiguration(surface!!)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val sessionConfiguration = SessionConfiguration(
                        SessionConfiguration.SESSION_REGULAR, listOf(outputConfiguration),
                        context.mainExecutor,
                        object : CameraCaptureSession.StateCallback() {
                            override fun onConfigured(session: CameraCaptureSession) {
                                cameraCaptureSession = session
                                captureRequestBuilder!!.set(
                                    CaptureRequest.CONTROL_MODE,
                                    CameraMetadata.CONTROL_MODE_AUTO
                                )
                                try {
                                    cameraCaptureSession!!.setRepeatingRequest(
                                        captureRequestBuilder!!.build(),
                                        null,
                                        null
                                    )
                                } catch (e: CameraAccessException) {
                                    Log.e(TAG, "CameraAccessException: ${e.message}", e)
                                }
                            }

                            override fun onConfigureFailed(session: CameraCaptureSession) {
                                cameraCaptureSession = null
                                Log.e(TAG, "Camera configuration failed")
                            }
                        })
                    cameraDevice!!.createCaptureSession(sessionConfiguration)
                }
            } catch (e: CameraAccessException) {
                Log.e(TAG, "CameraAccessException: ${e.message}", e)
            }
        }
    }

    fun stopCamera() {
        coroutineScope.launch {
            try {
                cameraCaptureSession?.let {
                    try {
                        it.abortCaptures()
                        it.close()
                        cameraCaptureSession = null
                        Log.d(TAG, "Camera stopped.")
                    } catch (e: CameraAccessException) {
                        Log.e(TAG, "CameraAccessException: ${e.message}", e)
                    }
                }
                cameraDevice?.let {
                    it.close()
                    cameraDevice = null
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception in stopCamera: ${e.message}", e)
            }
        }
    }

    companion object {
        private const val TAG = "CameraHandler"
    }
}
