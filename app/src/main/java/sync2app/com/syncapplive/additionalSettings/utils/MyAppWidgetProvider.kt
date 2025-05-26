package sync2app.com.syncapplive.additionalSettings.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import sync2app.com.syncapplive.R
import sync2app.com.syncapplive.additionalSettings.SplashVideoActivity


class MyAppWidgetProvider : AppWidgetProvider() {
    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        appWidgetIds.forEach { appWidgetId ->
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, SplashVideoActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_layout
            ).apply { setOnClickPendingIntent(R.id.widget_text, pendingIntent) }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}