package sync2app.com.syncapplive.additionalSettings.cloudAppsync.util;

public class Common {

    //app data
    public static final String CURRENT_USER = "CurrentUser";
    public static final String CURRENT_SETTING = "CurrentSettings";
    public static final String set_schedule_key = "set_schedule_key";
    public static final String schedule_online = "schedule_online";
    public static final String schedule_offline = "schedule_offline";


    public static final String CURRENT_VERIFICATION = "CurrentVerification";

    //user types
    public static final String USER_TYPE_BASIC = "Starter";
    public static final String USER_TYPE_ULTRA = "Ultra";
    public static final String USER_TYPE_LITE = "Lite";
    public static final String USER_TYPE_PLUS = "Plus";
    public static final String USER_TYPE_HERO = "Hero";

    //downloadable file names
    public static final String DEFAULT_DOMAIN_FILE = "DefaultDomains.csv";
    public static final String CUSTOM_DOMAINS_FILE = "CustomDomains.csv";
    public static final String VERIFICATION_FILE = "UserVerification.csv";

    //app file names
    public static final String ONLINE_SCHEDULE_FILE = "/onlineSchedules.csv";
    public static final String LOCAL_SCHEDULE_FILE = "/localSchedules.csv";

    //schedule string values
    public static final String DAY_SUNDAY = "Sunday";
    public static final String DAY_MONDAY = "Monday";
    public static final String DAY_TUESDAY = "Tuesday";
    public static final String DAY_WEDNESDAY = "Wednesday";
    public static final String DAY_THURSDAY = "Thursday";
    public static final String DAY_FRIDAY = "Friday";
    public static final String DAY_SATURDAY = "Saturday";
    public static final String SCHEDULE_SCOPE_DAILY = "Daily";
    public static final String SCHEDULE_SCOPE_WEEKLY = "Weekly";
    public static final String SCHEDULE_TYPE_NORMAL = "Normal Schedule";
    public static final String SCHEDULE_TYPE_SPECIFIC = "Specific Schedule";

    //string intent keys
    public static final String EXIT_APP_INTENT = "ExitApp";

    //splash file names
    public static final String SPLASH_IMAGE_PORTRAIT = "PortraitSplash.png";
    public static final String SPLASH_IMAGE_LANDSCAPE = "LandscapeSplash.png";
    public static final String SPLASH_VIDEO = "Splash.mp4";

    //file directories
    public static final String CRASH_FOLDER_NAME = "Crash Reports";
    public static final String SPLASH_FOLDER_NAME = "Splash";
    public static final String TEMP_FOLDER_NAME = "Temp";
    public static final String LICENCE_FOLDER_DOWNLOAD_NAME = "LicencedDownload";
    public static final String LICENCED_FOLDER_NAME = "Licenced Files";
    public static final String TEMP_LICENCED_FOLDER_NAME = "Temp Licenced Files";
    public static final String USER_CONFIG_FOLDER = "Config";
    public static final String USER_SCHEDULE_FOLDER = "Schedules";

    //notification
    public static final String DEFAULT_NOTIFICATION_CHANNEL = "esw.mooreadvice.cloudappsync.DEFAULT_NOTIFICATION";

    //sync types
    public static final String SYNC_TYPE_FTP_FOLDER = "FTP Folder";
    public static final String SYNC_TYPE_FTP_ZIP = "FTP Zip";
    public static final String SYNC_TYPE_URL_ZIP = "URL Zip";
    public static final String SYNC_TYPE_API = "Api";
    public static final String SYNC_TYPE_INDEX = "Index Change";
    public static final String SYNC_TYPE_INDEX_ZIP = "Index Change Zip";
    public static final String SYNC_TYPE_PARSE = "Parsing";

    //api methods
    public static final String API_INIT_ZIP = "Url Zip";
    public static final String API_INIT_PARSE = "Parsing";
    public static final String API_INIT_CSV = "Start.csv";
    public static final String PREVIOUS_CSV_UPDATE = "PreviousCsvUpdate";

    //demo values
    //public static final String DEMO_COMPANY = "Demo";
    public static final String DEMO_COMPANY = "BV";
    //public static final String DEMO_LICENCE = "LDD_ZD2_20190621";
    public static final String DEMO_LICENCE = "141627";
    public static final String DEMO_USERNAME = "Appmaster123456";
    public static final String DEMO_PASSWORD = "Appmaster123456";

    //web agent
    public static final String WEB_AGENT_DESKTOP = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/37.0.2062.94 Chrome/37.0.2062.94 Safari/537.36";
    public static final String WEB_AGENT_MOBILE = "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";



    public static final String MANAGE_EXTERNAL_STORAGE_PERMISSION = "android:manage_external_storage";
    public static final int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST = 2;



}
