package sync2app.com.syncapplive.additionalSettings.cloudAppsync.util;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import io.paperdb.Paper;
import sync2app.com.syncapplive.additionalSettings.cloudAppsync.models.AppSettings;
import sync2app.com.syncapplive.additionalSettings.cloudAppsync.models.Domains;

public class MethodsSchedule {


    //check internet
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if (connectivityManager!=null)
        {
            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
            if (info!=null)
            {
                if (info.getState()== NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }


    public static String today(){

        //get today in millis
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());

    }

    public static void setPersistentDefaults(){

        //get current settings
        AppSettings currentSettings = Paper.book().read(Common.CURRENT_SETTING);

        //check for null
        if (currentSettings == null){
            currentSettings = new AppSettings();
        }

        //init list
        List<Domains> theDomains = new ArrayList<>();

        //set defaults
        currentSettings.setDb_licence("");
        currentSettings.setCustom_background("");
        currentSettings.setCustom_img("");
        currentSettings.setMaster_domains(theDomains);
        currentSettings.setIs_night_mode(false);
        currentSettings.setLoad_online(false);
        currentSettings.setCurrent_day(today());
        currentSettings.setIs_test_mode(false);
        currentSettings.setStay_in_test_mode(false);
        currentSettings.setStart_with_sync(false);
        currentSettings.setPage_timeout(15);
        currentSettings.setRefresh_page(false);
        currentSettings.setPage_refresh(10);
        currentSettings.setPersistent_password_req(true);
        currentSettings.setIs_password_provided(false);
        currentSettings.setAccess_password("00000");
        currentSettings.setSync_type("");
        currentSettings.setApi_sync_type("");
        currentSettings.setSync_interval(60);
        currentSettings.setSync_on_change(true);
        currentSettings.setCustom_url("https://cloudappsync.com");
        currentSettings.setShow_online_indicator(true);
        currentSettings.setLoad_schedule_online(false);
        currentSettings.setStart_at_boot(true);
        currentSettings.setWeb_agent(Common.WEB_AGENT_DESKTOP);
        currentSettings.setUse_custom_launch_link(false);
        currentSettings.setOnline_page_url("");
        currentSettings.setOffline_page_url("");
        currentSettings.setOne_time_load_style(false);
        currentSettings.setUse_server_time(false);
        currentSettings.setSupportUrl("");

        //save default settings
        Paper.book().write(Common.CURRENT_SETTING, currentSettings);

    }




}
