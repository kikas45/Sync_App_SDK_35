package sync2app.com.syncapplive.additionalSettings.savedDownloadHistory.scanutil;

import android.content.Intent;

import com.journeyapps.barcodescanner.CaptureActivity;

import sync2app.com.syncapplive.WebViewPage;

public class CaptureAct extends CaptureActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
        startActivity(intent);
        finish();
        finishAffinity();
        finishAndRemoveTask();
    }
}
