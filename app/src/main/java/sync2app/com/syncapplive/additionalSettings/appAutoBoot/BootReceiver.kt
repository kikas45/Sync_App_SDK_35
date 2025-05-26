package sync2app.com.syncapplive.additionalSettings.appAutoBoot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import sync2app.com.syncapplive.additionalSettings.SplashVideoActivity
import sync2app.com.syncapplive.additionalSettings.utils.Constants

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val sharedBiometric = context.getSharedPreferences(Constants.SHARED_BIOMETRIC, AppCompatActivity.MODE_PRIVATE)
        val getStateOfBootToggle = sharedBiometric.getString(Constants.imgEnableAutoBoot, "").toString()

        if (Intent.ACTION_BOOT_COMPLETED == intent.action && getStateOfBootToggle == Constants.imgEnableAutoBoot) {
            val activityIntent = Intent(context, SplashVideoActivity::class.java)
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(activityIntent)
        }
    }
}
