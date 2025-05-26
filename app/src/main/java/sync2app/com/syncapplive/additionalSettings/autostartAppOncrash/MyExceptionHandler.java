package sync2app.com.syncapplive.additionalSettings.autostartAppOncrash;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import sync2app.com.syncapplive.MyApplication;
import sync2app.com.syncapplive.additionalSettings.TvActivityOrAppMode;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Activity activity;

    public MyExceptionHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {


        //restart app
        Intent intent = new Intent(activity, TvActivityOrAppMode.class);
        intent.putExtra("crash", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        //pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        //delay for restart
        AlarmManager mgr = (AlarmManager) MyApplication.getContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);    activity.finish();
        System.exit(2);

    }


}
