package sync2app.com.syncapplive;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.multidex.MultiDexApplication;

import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;


import io.paperdb.Paper;
import sync2app.com.syncapplive.additionalSettings.CrashReportDB.CrashHandler;

public class MyApplication extends MultiDexApplication {

    private static Application instance;

    private static int numberOfRunningActivities = 0;



    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Paper.init(getApplicationContext());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(instance));

                if (!(constants.OnesigID ==null)){
                    OneSignal.initWithContext(MyApplication.this);
                    OneSignal.setAppId(constants.OnesigID);
                }

            }
        }, 1000);


        OneSignal.setNotificationOpenedHandler(new OneSignal.OSNotificationOpenedHandler() {
            @Override
            public void notificationOpened(OSNotificationOpenedResult result) {
                String launchURL = result.getNotification().getLaunchURL();

                if (launchURL != null) {
//                    Log.d(Const.DEBUG, "Launch URL: " + launchURL);
                    Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url", launchURL);
                    startActivity(intent);
                }
            }
        });
    }

    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }


}