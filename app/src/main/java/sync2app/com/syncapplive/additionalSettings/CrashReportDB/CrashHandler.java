package sync2app.com.syncapplive.additionalSettings.CrashReportDB;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sync2app.com.syncapplive.additionalSettings.utils.Constants;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;

    public CrashHandler(Context context) {
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        handleUncaughtException(thread, throwable);
    }

    private void handleUncaughtException(Thread thread, Throwable throwable) {
        throwable.printStackTrace();

        if (!throwable.getMessage().isEmpty()) {
            String crashInfo = "\nDevice: " + Build.DEVICE +
                    "\nModel: " + Build.MODEL +
                    "\nAndroid Version: " + Build.VERSION.RELEASE +
                    "\n\nDate and Time: " + getCurrentDateAndTime() +
                    "\n\nCrash Message: " + throwable.getMessage() +
                    "\n\nCrash in Method: " + getCrashMethodName(throwable) +
                    "\n\nCrash in Activity: " + getCurrentActivityName() +
                    "\n\nStack Trace: " + Log.getStackTraceString(throwable);
            showToast("Crash Occurred !, Please go to Maintenance Page to Send Crash");
            SharedPreferences sharedBiometric = mContext.getSharedPreferences(Constants.SHARED_SAVED_CRASH_REPORT, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedBiometric.edit();
            editor.putString(Constants.crashInfo, "" + crashInfo);
            editor.putString(Constants.crashCalled, Constants.crashCalled);
            editor.apply();

            // showAlertDialog();
        }
    }

    private String getCurrentActivityName() {

        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        if (taskInfo != null && !taskInfo.isEmpty()) {
            return taskInfo.get(0).topActivity.getClassName();
        }
        return "Unknown";
    }


    private String getCrashMethodName(Throwable throwable) {
        // Retrieve the method name where the crash occurred
        StackTraceElement[] elements = throwable.getStackTrace();
        if (elements.length > 0) {
            return elements[0].getMethodName();
        }
        return "Unknown";
    }

    private String getCurrentDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date currentDate = new Date(System.currentTimeMillis());
        return dateFormat.format(currentDate);
    }
    private void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
    private void closeApp() {
        System.exit(0);
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle("Oops! Something went wrong.");
        builder.setMessage("We apologize for the inconvenience. Please close the app.");
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                closeApp();
            }
        });
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    ///  minifyEnabled = false

}
