package sync2app.com.syncapplive.additionalSettings.cloudAppsync.models;

import java.io.Serializable;
import java.util.List;

public class AppSettings implements Serializable {

    private String user_type;
    private String db_licence;
    private List<Domains> master_domains;
    private boolean is_night_mode;
    private boolean load_online;
    private boolean one_time_load_style;
    private String current_day;
    private boolean is_test_mode;
    private boolean stay_in_test_mode;
    private boolean start_with_sync;
    private int page_timeout;
    private boolean refresh_page;
    private int page_refresh;
    private boolean is_video_splash;
    private boolean persistent_password_req;
    private boolean is_password_provided;
    private String access_password;
    private int sync_interval;
    private boolean sync_on_change;
    private String custom_url;
    private String custom_img;
    private boolean show_online_indicator;
    private boolean load_schedule_online;
    private String custom_background;
    private String sync_type;
    private String api_sync_type;
    private boolean start_at_boot;
    private String web_agent;
    private boolean use_custom_launch_link;
    private String offline_page_url;
    private String online_page_url;
    private boolean use_server_time;
    private String supportUrl;

    public AppSettings() {
    }

    public AppSettings(String user_type, String db_licence, List<Domains> master_domains, boolean is_night_mode, boolean load_online, boolean one_time_load_style, String current_day, boolean is_test_mode, boolean stay_in_test_mode, boolean start_with_sync, int page_timeout, boolean refresh_page, int page_refresh, boolean is_video_splash, boolean persistent_password_req, boolean is_password_provided, String access_password, int sync_interval, boolean sync_on_change, String custom_url, String custom_img, boolean show_online_indicator, boolean load_schedule_online, String custom_background, String sync_type, String api_sync_type, boolean start_at_boot, String web_agent, boolean use_custom_launch_link, String offline_page_url, String online_page_url, boolean use_server_time, String supportUrl) {
        this.user_type = user_type;
        this.db_licence = db_licence;
        this.master_domains = master_domains;
        this.is_night_mode = is_night_mode;
        this.load_online = load_online;
        this.one_time_load_style = one_time_load_style;
        this.current_day = current_day;
        this.is_test_mode = is_test_mode;
        this.stay_in_test_mode = stay_in_test_mode;
        this.start_with_sync = start_with_sync;
        this.page_timeout = page_timeout;
        this.refresh_page = refresh_page;
        this.page_refresh = page_refresh;
        this.is_video_splash = is_video_splash;
        this.persistent_password_req = persistent_password_req;
        this.is_password_provided = is_password_provided;
        this.access_password = access_password;
        this.sync_interval = sync_interval;
        this.sync_on_change = sync_on_change;
        this.custom_url = custom_url;
        this.custom_img = custom_img;
        this.show_online_indicator = show_online_indicator;
        this.load_schedule_online = load_schedule_online;
        this.custom_background = custom_background;
        this.sync_type = sync_type;
        this.api_sync_type = api_sync_type;
        this.start_at_boot = start_at_boot;
        this.web_agent = web_agent;
        this.use_custom_launch_link = use_custom_launch_link;
        this.offline_page_url = offline_page_url;
        this.online_page_url = online_page_url;
        this.use_server_time = use_server_time;
        this.supportUrl = supportUrl;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getDb_licence() {
        return db_licence;
    }

    public void setDb_licence(String db_licence) {
        this.db_licence = db_licence;
    }

    public List<Domains> getMaster_domains() {
        return master_domains;
    }

    public void setMaster_domains(List<Domains> master_domains) {
        this.master_domains = master_domains;
    }

    public boolean isIs_night_mode() {
        return is_night_mode;
    }

    public void setIs_night_mode(boolean is_night_mode) {
        this.is_night_mode = is_night_mode;
    }

    public boolean isLoad_online() {
        return load_online;
    }

    public void setLoad_online(boolean load_online) {
        this.load_online = load_online;
    }

    public boolean isOne_time_load_style() {
        return one_time_load_style;
    }

    public void setOne_time_load_style(boolean one_time_load_style) {
        this.one_time_load_style = one_time_load_style;
    }

    public String getCurrent_day() {
        return current_day;
    }

    public void setCurrent_day(String current_day) {
        this.current_day = current_day;
    }

    public boolean isIs_test_mode() {
        return is_test_mode;
    }

    public void setIs_test_mode(boolean is_test_mode) {
        this.is_test_mode = is_test_mode;
    }

    public boolean isStay_in_test_mode() {
        return stay_in_test_mode;
    }

    public void setStay_in_test_mode(boolean stay_in_test_mode) {
        this.stay_in_test_mode = stay_in_test_mode;
    }

    public boolean isStart_with_sync() {
        return start_with_sync;
    }

    public void setStart_with_sync(boolean start_with_sync) {
        this.start_with_sync = start_with_sync;
    }

    public int getPage_timeout() {
        return page_timeout;
    }

    public void setPage_timeout(int page_timeout) {
        this.page_timeout = page_timeout;
    }

    public boolean isRefresh_page() {
        return refresh_page;
    }

    public void setRefresh_page(boolean refresh_page) {
        this.refresh_page = refresh_page;
    }

    public int getPage_refresh() {
        return page_refresh;
    }

    public void setPage_refresh(int page_refresh) {
        this.page_refresh = page_refresh;
    }

    public boolean isIs_video_splash() {
        return is_video_splash;
    }

    public void setIs_video_splash(boolean is_video_splash) {
        this.is_video_splash = is_video_splash;
    }

    public boolean isPersistent_password_req() {
        return persistent_password_req;
    }

    public void setPersistent_password_req(boolean persistent_password_req) {
        this.persistent_password_req = persistent_password_req;
    }

    public boolean isIs_password_provided() {
        return is_password_provided;
    }

    public void setIs_password_provided(boolean is_password_provided) {
        this.is_password_provided = is_password_provided;
    }

    public String getAccess_password() {
        return access_password;
    }

    public void setAccess_password(String access_password) {
        this.access_password = access_password;
    }

    public int getSync_interval() {
        return sync_interval;
    }

    public void setSync_interval(int sync_interval) {
        this.sync_interval = sync_interval;
    }

    public boolean isSync_on_change() {
        return sync_on_change;
    }

    public void setSync_on_change(boolean sync_on_change) {
        this.sync_on_change = sync_on_change;
    }

    public String getCustom_url() {
        return custom_url;
    }

    public void setCustom_url(String custom_url) {
        this.custom_url = custom_url;
    }

    public String getCustom_img() {
        return custom_img;
    }

    public void setCustom_img(String custom_img) {
        this.custom_img = custom_img;
    }

    public boolean isShow_online_indicator() {
        return show_online_indicator;
    }

    public void setShow_online_indicator(boolean show_online_indicator) {
        this.show_online_indicator = show_online_indicator;
    }

    public boolean isLoad_schedule_online() {
        return load_schedule_online;
    }

    public void setLoad_schedule_online(boolean load_schedule_online) {
        this.load_schedule_online = load_schedule_online;
    }

    public String getCustom_background() {
        return custom_background;
    }

    public void setCustom_background(String custom_background) {
        this.custom_background = custom_background;
    }

    public String getSync_type() {
        return sync_type;
    }

    public void setSync_type(String sync_type) {
        this.sync_type = sync_type;
    }

    public String getApi_sync_type() {
        return api_sync_type;
    }

    public void setApi_sync_type(String api_sync_type) {
        this.api_sync_type = api_sync_type;
    }

    public boolean isStart_at_boot() {
        return start_at_boot;
    }

    public void setStart_at_boot(boolean start_at_boot) {
        this.start_at_boot = start_at_boot;
    }

    public String getWeb_agent() {
        return web_agent;
    }

    public void setWeb_agent(String web_agent) {
        this.web_agent = web_agent;
    }

    public boolean isUse_custom_launch_link() {
        return use_custom_launch_link;
    }

    public void setUse_custom_launch_link(boolean use_custom_launch_link) {
        this.use_custom_launch_link = use_custom_launch_link;
    }

    public String getOffline_page_url() {
        return offline_page_url;
    }

    public void setOffline_page_url(String offline_page_url) {
        this.offline_page_url = offline_page_url;
    }

    public String getOnline_page_url() {
        return online_page_url;
    }

    public void setOnline_page_url(String online_page_url) {
        this.online_page_url = online_page_url;
    }

    public boolean isUse_server_time() {
        return use_server_time;
    }

    public void setUse_server_time(boolean use_server_time) {
        this.use_server_time = use_server_time;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }
}
