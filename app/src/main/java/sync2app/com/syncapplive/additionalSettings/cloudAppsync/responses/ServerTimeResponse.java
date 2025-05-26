package sync2app.com.syncapplive.additionalSettings.cloudAppsync.responses;

public class ServerTimeResponse {

    private String day;
    private String date;
    private String time;
    private String timezone;

    public ServerTimeResponse() {
    }

    public ServerTimeResponse(String day, String date, String time, String timezone) {
        this.day = day;
        this.date = date;
        this.time = time;
        this.timezone = timezone;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
