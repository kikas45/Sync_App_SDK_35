package sync2app.com.syncapplive.additionalSettings.myCompleteDownload;

public interface DownloadHelper {

    void afterExecutionIsComplete();

    void whenExecutionStarts();

    void whileInProgress(int i);

}
