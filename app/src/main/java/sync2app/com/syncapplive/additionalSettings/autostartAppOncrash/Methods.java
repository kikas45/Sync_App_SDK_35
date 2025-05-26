package sync2app.com.syncapplive.additionalSettings.autostartAppOncrash;


import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

import sync2app.com.syncapplive.additionalSettings.utils.Constants;


public class Methods {

    public static void addExceptionHandler(Activity activity){

        SharedPreferences sharedBiometric = activity.getApplicationContext().getSharedPreferences(Constants.SHARED_BIOMETRIC, MODE_PRIVATE);
        String getTvMode = sharedBiometric.getString(Constants.MY_TV_OR_APP_MODE, "");
        String get_imgStartAppRestartOnTvMode= sharedBiometric.getString(Constants.imgStartAppRestartOnTvMode, "");

      //  SharedPreferences.Editor editor = sharedBiometric.edit();


        if (get_imgStartAppRestartOnTvMode.equals(Constants.imgStartAppRestartOnTvMode)) {
            Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(activity));
        }


//
//        if (getTvMode.equals(Constants.TV_Mode)) {
//            editor.putString(Constants.imgStartAppRestartOnTvMode, Constants.imgStartAppRestartOnTvMode);
//            editor.apply();
//        }else {
//            editor.remove(Constants.imgStartAppRestartOnTvMode);
//            editor.apply();
//        }





    }

}
