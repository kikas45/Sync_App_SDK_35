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
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import sync2app.com.syncapplive.WebViewPage
import sync2app.com.syncapplive.R
import sync2app.com.syncapplive.additionalSettings.utils.Constants

@RequiresApi(Build.VERSION_CODES.N_MR1)
object DefaultCustomShortCut {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun setUp(context: Context, textView: String, iconBitmap: Bitmap? = null) {
        val shortcutManager =
            getSystemService<ShortcutManager>(context, ShortcutManager::class.java)

        val intents = arrayOf(
            Intent(Intent.ACTION_VIEW, null, context, WebViewPage::class.java),
        )

        // If iconBitmap is null, use the default icon from resources
        val icon = iconBitmap?.let {
            // Ensure the iconBitmap is square with a white background
            val size = minOf(it.width, it.height)
            Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888).apply {
                eraseColor(Color.WHITE)
                val canvas = Canvas(this)
                val left = (size - it.width) / 2f
                val top = (size - it.height) / 2f
                canvas.drawBitmap(it, left, top, null)
            }
        } ?: Icon.createWithResource(context, R.drawable.img_logo_icon)

        val shortcut = ShortcutInfo.Builder(context, Constants.shortcut_website_id)
            .setShortLabel(textView)
            .setLongLabel("Open $textView")
            .setIcon(icon as Icon?)
            .setIntents(intents)
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcut)
    }
}













/*

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.*
import sync2app.com.syncapplive.R
import sync2app.com.syncapplive.UnUsedPackages.WebActivity
import sync2app.com.syncapplive.additionalSettings.utils.Constants


//Requires api level 25
@RequiresApi(Build.VERSION_CODES.N_MR1)
object DefaultCustomShortCut {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun setUp(context: Context, textView: String) {  // to pass image on the constructor if possible
        val shortcutManager =
            getSystemService<ShortcutManager>(context, ShortcutManager::class.java)


        val intents = arrayOf(
            Intent(Intent.ACTION_VIEW, null, context, WebActivity::class.java),
        )

        val shortcut = ShortcutInfo.Builder(context, Constants.shortcut_website_id)
            .setShortLabel(textView)
            .setLongLabel("Open $textView")
            .setIcon(Icon.createWithResource(context, R.drawable.img_logo_icon))
            .setIntents(intents)
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcut)
    }

}*/
