package sync2app.com.syncapplive

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.text.Html
import android.util.Log
import androidx.core.app.NotificationCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import sync2app.com.syncapplive.additionalSettings.utils.Constants
import java.util.concurrent.CountDownLatch

class RemotexNotifierKT : Service() {

    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    private var runnable: Runnable? = null
    private val notifFrequency = 30000L

    private val preferences: SharedPreferences by lazy {
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }

    private val simpleSavedPassword: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            Constants.SIMPLE_SAVED_PASSWORD, Context.MODE_PRIVATE
        )
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("RemoteX", "RemoteX Notification Service Active!")

        val get_tMaster = simpleSavedPassword.getString(Constants.get_editTextMaster, "")
        val get_UserID = simpleSavedPassword.getString(Constants.get_UserID, "")
        val get_LicenseKey = simpleSavedPassword.getString(Constants.get_LicenseKey, "")
        val notificationUrl = "$get_tMaster/$get_UserID/$get_LicenseKey/AppConfig/remotexNotif.json"

        runnable = Runnable {
            coroutineScope.launch {
                NotifApiCall(notificationUrl)
            }
            handler.postDelayed(runnable!!, notifFrequency)
        }
        handler.postDelayed(runnable!!, 10000) // Initial delay
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() // Cancel all coroutines when the service is destroyed
        runnable?.let { handler.removeCallbacks(it) }
    }

    private suspend fun NotifApiCall(url: String) = withContext(Dispatchers.IO) {
        try {
            val response = fetchApiResponse(url)
            processApiResponse(response)
        } catch (e: Exception) {
            Log.e("NotifApiCall", "Exception: ${e.message}")
        }
    }

    private fun fetchApiResponse(url: String): String {
        val queue = Volley.newRequestQueue(applicationContext)
        var response: String? = null

        val request = StringRequest(
            Request.Method.GET, url,
            { responseString -> response = responseString },
            { error -> Log.e("NotifApiCall", "Error: ${error.message}") }
        )

        queue.add(request)
        // Wait for the request to complete
        val latch = CountDownLatch(1)
        queue.addRequestFinishedListener<Any> { latch.countDown() }
        latch.await()

        return response ?: ""
    }

    private fun processApiResponse(response: String) {
        try {
            val jsonObject = JSONObject(response)
            val remoteNotifJson = jsonObject.getJSONObject("remoteNotif")
            constants.NotifAvailable = remoteNotifJson.getBoolean("liveNotifAvailable")
            constants.Notif_ID = remoteNotifJson.getString("liveNotifID")
            val lastNotifxId = preferences.getString("lastId", "")
            constants.Notif_title = remoteNotifJson.getString("liveNotifTitle")
            constants.Notif_desc = remoteNotifJson.getString("liveNotifDesc")
            constants.Notif_Img_url = remoteNotifJson.getString("liveNotifImage")
            Log.d("Notif_Img_url", constants.Notif_Img_url.toString())
            constants.Notif_button_action = remoteNotifJson.getString("liveNotifUrl")
            constants.NotifLinkExternal = remoteNotifJson.getBoolean("liveNotifLinkExternal")
            constants.NotifSound = remoteNotifJson.getBoolean("liveNotifSound")
            val intent = Intent("notifx_ready")
            applicationContext.sendBroadcast(intent)

            if (constants.NotifAvailable && (lastNotifxId != constants.Notif_ID)) {
                constants.Notif_Shown = false
                Handler(Looper.getMainLooper()).postDelayed({
                    val lastPanelID = preferences.getString("lastIdPanel", "")
                    if (lastPanelID != constants.Notif_ID) {
                        SendNotifX(constants.Notif_title, constants.Notif_desc)
                        stopService(Intent(applicationContext, RemotexNotifierKT::class.java))
                    }
                }, 5000)
            }
        } catch (e: JSONException) {
            Log.e("NotifApiCall", "JSON Parsing error: ${e.message}")
        }
    }

    private fun SendNotifX(title: String, messageBody: String) {
        val editor = preferences.edit()
        editor.putString("lastIdPanel", constants.Notif_ID).apply()

        val intent = Intent(this, WebViewPage::class.java).apply {
            if (constants.Notif_button_action.startsWith("http") || constants.Notif_button_action.startsWith("https")) {
                putExtra("url", constants.Notif_button_action)
            }
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        Glide.with(applicationContext)
            .asBitmap()
            .load(constants.Notif_Img_url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                    sendNotificationWithLargeIcon(title, messageBody, resource, pendingIntent)
                }
            })
    }

    private fun sendNotificationWithLargeIcon(
        title: String,
        messageBody: String,
        largeIcon: Bitmap,
        pendingIntent: PendingIntent
    ) {
        val notificationBuilder = NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(largeIcon))
            .setContentTitle(title)
            .setContentText(Html.fromHtml(messageBody))
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.default_notification_channel_id),
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}
