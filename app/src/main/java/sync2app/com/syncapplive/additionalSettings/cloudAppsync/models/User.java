package sync2app.com.syncapplive.additionalSettings.cloudAppsync.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "users", indices = {@Index(value = {"company_id", "licence_key"}, unique = true)})
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("master_url")
    private String master_url;

    @SerializedName("ftp_host")
    private String ftp_host;

    @SerializedName("ftp_user")
    private String ftp_user;

    @SerializedName("ftp_password")
    private String ftp_password;

    @SerializedName("ftp_port")
    private int ftp_port;

    @ColumnInfo(name = "company_id")
    private String company_id;

    @ColumnInfo(name = "licence_key")
    private String licence_key;

    @SerializedName("user_type")
    private String user_type;

    @SerializedName("api")
    private boolean api;

    @SerializedName("ftp_folder")
    private boolean ftp_folder;

    @SerializedName("ftp_zip")
    private boolean ftp_zip;

    @SerializedName("index_change")
    private boolean index_change;

    @SerializedName("index_zip")
    private boolean index_zip;

    @SerializedName("parsing")
    private boolean parsing;

    @SerializedName("url_zip")
    private boolean url_zip;

    @SerializedName("licence")
    private String licence;

    @SerializedName("support_url")
    private String support_url;

    public User() {
    }

    public User(String master_url, String ftp_host, String ftp_user, String ftp_password, int ftp_port, String company_id, String licence_key, String user_type, boolean api, boolean ftp_folder, boolean ftp_zip, boolean index_change, boolean index_zip, boolean parsing, boolean url_zip, String licence, String support_url) {
        this.master_url = master_url;
        this.ftp_host = ftp_host;
        this.ftp_user = ftp_user;
        this.ftp_password = ftp_password;
        this.ftp_port = ftp_port;
        this.company_id = company_id;
        this.licence_key = licence_key;
        this.user_type = user_type;
        this.api = api;
        this.ftp_folder = ftp_folder;
        this.ftp_zip = ftp_zip;
        this.index_change = index_change;
        this.index_zip = index_zip;
        this.parsing = parsing;
        this.url_zip = url_zip;
        this.licence = licence;
        this.support_url = support_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaster_url() {
        return master_url;
    }

    public void setMaster_url(String master_url) {
        this.master_url = master_url;
    }

    public String getFtp_host() {
        return ftp_host;
    }

    public void setFtp_host(String ftp_host) {
        this.ftp_host = ftp_host;
    }

    public String getFtp_user() {
        return ftp_user;
    }

    public void setFtp_user(String ftp_user) {
        this.ftp_user = ftp_user;
    }

    public String getFtp_password() {
        return ftp_password;
    }

    public void setFtp_password(String ftp_password) {
        this.ftp_password = ftp_password;
    }

    public int getFtp_port() {
        return ftp_port;
    }

    public void setFtp_port(int ftp_port) {
        this.ftp_port = ftp_port;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getLicence_key() {
        return licence_key;
    }

    public void setLicence_key(String licence_key) {
        this.licence_key = licence_key;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public boolean isApi() {
        return api;
    }

    public void setApi(boolean api) {
        this.api = api;
    }

    public boolean isFtp_folder() {
        return ftp_folder;
    }

    public void setFtp_folder(boolean ftp_folder) {
        this.ftp_folder = ftp_folder;
    }

    public boolean isFtp_zip() {
        return ftp_zip;
    }

    public void setFtp_zip(boolean ftp_zip) {
        this.ftp_zip = ftp_zip;
    }

    public boolean isIndex_change() {
        return index_change;
    }

    public void setIndex_change(boolean index_change) {
        this.index_change = index_change;
    }

    public boolean isIndex_zip() {
        return index_zip;
    }

    public void setIndex_zip(boolean index_zip) {
        this.index_zip = index_zip;
    }

    public boolean isParsing() {
        return parsing;
    }

    public void setParsing(boolean parsing) {
        this.parsing = parsing;
    }

    public boolean isUrl_zip() {
        return url_zip;
    }

    public void setUrl_zip(boolean url_zip) {
        this.url_zip = url_zip;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getSupport_url() {
        return support_url;
    }

    public void setSupport_url(String support_url) {
        this.support_url = support_url;
    }
}
