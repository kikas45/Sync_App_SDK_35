package sync2app.com.syncapplive.additionalSettings.savedDownloadHistory.scanutil


import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import sync2app.com.syncapplive.WebViewPage
import sync2app.com.syncapplive.additionalSettings.SplashVideoActivity
import sync2app.com.syncapplive.additionalSettings.utils.Constants

@RequiresApi(Build.VERSION_CODES.N_MR1)
object CustomShortcutsDemo {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun setUp(context: Context, textView: String, iconBitmap: Bitmap) {
        val shortcutManager = getSystemService<ShortcutManager>(context, ShortcutManager::class.java)

        val intents = arrayOf(
            Intent(Intent.ACTION_VIEW, null, context, SplashVideoActivity::class.java),
        )

        // Ensure the iconBitmap is square
        val squareIconBitmap = if (iconBitmap.width != iconBitmap.height) {
            val size = minOf(iconBitmap.width, iconBitmap.height)
            Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888).apply {
                eraseColor(Color.WHITE)
                val canvas = Canvas(this)
                val left = (size - iconBitmap.width) / 2f
                val top = (size - iconBitmap.height) / 2f
                canvas.drawBitmap(iconBitmap, left, top, null)
            }
        } else {
            iconBitmap
        }

        val shortcut = ShortcutInfo.Builder(context, Constants.shortcut_messages_id)
            .setShortLabel(textView)
            .setLongLabel("Open $textView")
            .setIcon(Icon.createWithBitmap(squareIconBitmap))
            .setIntents(intents)
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcut)

    }
}


