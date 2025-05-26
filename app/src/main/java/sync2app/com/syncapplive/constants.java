package sync2app.com.syncapplive;

import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class constants {

    private static final String app_package_id = MyApplication.getContext().getPackageName();
    public static String CurrVersion;


//    public static String ServerUrl = "https://sync2app.com/abc/123/admin_config/appConfig.json";
//    public static String NotificationUrl = "https://sync2app.com/abc/123/admin_config/appConfig.json";
//
//   public static String splashScreenUrl = "https://sync2app.com/abc/123/logo_3.svg";



  //  public static String ServerUrl = "https://sync2app.com/abc/123/admin_config/appConfig.json";

  //  public static String NotificationUrl = "https://sync2app.com/abc/123/admin_config/appConfig.json";


    // this is good one

  //  public static String NotificationUrl = "https://cp.cloudappserver.co.uk/app_base/public/CLO/DE_MO_2021000/AppConfig/remotexNotif.json ";


    // this bad one
 //   public static String NotificationUrl = "https://cp.cloudappserver.co.uk/app_base/public/CLO/DE_MO_2021000/AppConfig/appConfig.json";




    // public static String splashScreenUrl = "https://sync2app.com/abc/123/logo_3.png";
    public static String splashScreenUrl = "https://sync2app.com/abc/123/admin_config/icons/logo.png ";




    /*Strictly do not change anything below*/
    public static String jsonUrl;
    public static String filterdomain;

    public static String bottomUrl1;
    public static String bottomUrl2;
    public static String bottomUrl3;
    public static String bottomUrl4;
    public static String bottomUrl5;
    public static String bottomUrl6;

    public static String bottomBtn1ImgUrl;
    public static String bottomBtn2ImgUrl;
    public static String bottomBtn3ImgUrl;
    public static String bottomBtn4ImgUrl;
    public static String bottomBtn5ImgUrl;
    public static String bottomBtn6ImgUrl;

    public static String drawerMenuImgUrl;

    public static String drawerMenuItem1ImgUrl;
    public static String drawerMenuItem2ImgUrl;
    public static String drawerMenuItem3ImgUrl;
    public static String drawerMenuItem4ImgUrl;
    public static String drawerMenuItem5ImgUrl;
    public static String drawerMenuItem6ImgUrl;
    public static String drawerMenuItem7ImgUrl;


    public static String drawerMenuItem1Url;
    public static String drawerMenuItem2Url;
    public static String drawerMenuItem3Url;
    public static String drawerMenuItem4Url;
    public static String drawerMenuItem5Url;
    public static String drawerMenuItem6Url;
    public static String drawerMenuItem7Url;

    public static String drawerMenuItem1Text;
    public static String drawerMenuItem2Text;
    public static String drawerMenuItem3Text;
    public static String drawerMenuItem4Text;
    public static String drawerMenuItem5Text;
    public static String drawerMenuItem6Text;
    public static String drawerMenuItem7Text;

    public static String drawerHeaderImgUrl;
    public static String drawerHeaderImgCommand;
    public static String drawerHeaderText;
    public static String drawerHeaderTextColor;

    public static String drawerHeaderBgColor;

    public static String ToolbarTitleText;
    public static String ToolbarTitleTextColor;
    public static String ToolbarBgColor;





    public static String drawerMenuBtnUrl;


//    public static String MainWebsiteLink = jsonUrl; //Your Site URL (Supports http, https, file://)
    public static String MyPhoneNo = "919633039471";
    public static String GooglePlayLink = "https://play.google.com/store/apps/details?id=" + app_package_id; // No need edit this


    public static String Web_button_link ;
    public static String Web_button_Img_link ;

    public static boolean AllowOnlyHostUrlInApp ;
    public static boolean ShowBottomBar;
    public static boolean ShowWebBtn;
    public static boolean ShowAdmobBanner;
    public static boolean ShowAdmobInterstitial;
    public static boolean ShowDrawer;
    public static boolean ShowToolbar;
    public static boolean ChangeTittleTextColor;
    public static boolean ChangeToolbarBgColor;
    public static boolean ChangeDrawerHeaderBgColor;
    public static boolean ChangeBottombarBgColor;
    public static boolean ChangeHeaderTextColor;
    public static boolean ShowServerUrlSetUp;


    public static String OnesigID;
    public static String splashUrl;
    public static String bottomBarBgColor;





    //Welcome Screen
    public static boolean EnableWelcomeSlider;


    public static String screen1TitleText;
    public static String screen2TitleText;
    public static String screen3TitleText;
    public static String screen4TitleText;

    public static String screen1Img;
    public static String screen2Img;
    public static String screen3Img;
    public static String screen4Img;

    public static String screen1Desc;
    public static String screen2Desc;
    public static String screen3Desc;
    public static String screen4Desc;

    public static String screen1TextColor;
    public static String screen2TextColor;
    public static String screen3TextColor;
    public static String screen4TextColor;

    public static String screen1BgColor;
    public static String screen2BgColor;
    public static String screen3BgColor;
    public static String screen4BgColor;


    //App Update
     public static boolean UpdateAvailable;
     public static boolean ForceUpdate;
    public static String UpdateTitle;
    public static String UpdateMessage;
    public static String UpdateUrl;
    public static String NewVersion;


    //live notification
    public static boolean Notifx_service;
    public static boolean NotifAvailable;

    public static boolean NotifLinkExternal;
//    public static boolean NotifShowInUi;
    public static boolean NotifSound;
    public static String Notif_title;
    public static String Notif_desc;
    public static String Notif_ID;
    public static String Notif_button_action;
    public static String Notif_Img_url;

    public static String Current_Notif_ID;
    public static boolean Notif_Shown;

    public static boolean isAppOpen;




    /*Do not remove or edit the codes below!*/
    public static String AUTHORITY = app_package_id + ".fileprovider";
    public static String currentDownloadFileName;
    public static String currentDownloadFileMimeType;
    public static Uri currentFileUri;
    public static File DownloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
}
