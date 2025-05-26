package sync2app.com.syncapplive.QrPages;


import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sync2app.com.syncapplive.WebViewPage;
import sync2app.com.syncapplive.R;
import sync2app.com.syncapplive.additionalSettings.savedDownloadHistory.scanutil.CaptureAct;
import sync2app.com.syncapplive.additionalSettings.utils.Constants;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class QRSanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrsan);


        scanCode();

    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }


    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result ->
    {
        if (result.getContents() != null) {

            String content = result.getContents();

            manageMyAction(content);

            Log.d("MYINtents", result.getContents().toString());


        }
    });


    private String extractValue(String text, String startTag) {
        int startIndex = text.indexOf(startTag);
        if (startIndex != -1) {
            startIndex += startTag.length();
            int endIndex = text.indexOf(";", startIndex);
            if (endIndex != -1) {
                return text.substring(startIndex, endIndex);
            }
        }
        return "";
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }




    void sendPlainText(String text) {
        // Create the intent to send plain text
        Intent sendIntent = new Intent(Intent.ACTION_SEND);

        // Set the type of the content
        sendIntent.setType("text/plain");

        // Set the text content
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);

        try {
            // Start the activity to open the app for sending plain text
            startActivity(Intent.createChooser(sendIntent, "Send text via:"));
            finish();
        } catch ( ActivityNotFoundException e ) {
            // Handle case where no app is available for sending text
            Toast.makeText(this, "No app found to send text", Toast.LENGTH_SHORT).show();
        }
    }


    void openWhatsApp(String result) {
        try {
            URI uri = new URI(result);

            // Get the phone number from the path of the URI
            String phoneNumber = uri.getPath().replace("/", "");

            // Remove any non-numeric characters from the phone number
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "");

            // Check if the phone number is empty
            if (phoneNumber.isEmpty()) {
                throw new IllegalArgumentException("Phone number not found in the URI");
            }

            // Get the text parameter from the query part of the URI
            String query = uri.getQuery();
            String text = null;
            if (query != null) {
                String[] queryParams = query.split("&");
                for (String param : queryParams) {
                    if (param.startsWith("text=")) {
                        text = param.substring("text=".length());
                        break;
                    }
                }
            }

            // Encode the text message
            String encodedText = text != null ? Uri.encode(text) : "";

            // Create the URI for sending a WhatsApp message
            Uri whatsappUri = Uri.parse("https://wa.me/" + phoneNumber + "?text=" + encodedText);

            // Create the intent to open WhatsApp
            @SuppressLint("UnsafeImplicitIntentLaunch") Intent intent = new Intent(Intent.ACTION_VIEW, whatsappUri);

            // Start the activity to open WhatsApp
            startActivity(intent);
            finish();
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        } catch ( IllegalArgumentException e ) {
            Toast.makeText(this, "Phone number not found in the URI", Toast.LENGTH_SHORT).show();
        } catch ( ActivityNotFoundException e ) {
            // Handle case where WhatsApp is not installed
            Toast.makeText(this, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
        startActivity(intent);
        finish();


    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void manageMyAction(String content) {
        SharedPreferences sharedBiometric = getSharedPreferences(Constants.SHARED_BIOMETRIC, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedBiometric.edit();
        // Check for email
        if (content.contains("MATMSG:TO") && content.contains("SUB") && content.contains("BODY")) {

            String emailTo = extractValue(content, "MATMSG:TO:");
            String emailSubject = extractValue(content, "SUB:");
            String emailBody = extractValue(content, "BODY:");


            editor.putString("emailTo", emailTo);
            editor.putString("emailSubject", emailSubject);
            editor.putString("emailBody", emailBody);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), EmailActivity.class);
            intent.putExtra(Constants.QR_CODE_KEY, "" + content);
            startActivity(intent);
            finish();


        }

        // Check for website link
        else if (content.startsWith("https://") || content.startsWith("http://")) {

            if (content.contains("https://wa.me/")) {
                openWhatsApp(content);

            } else {
                Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
                intent.putExtra(Constants.QR_CODE_KEY, "" + content);
                startActivity(intent);
                finish();

            }


        }

        // Check for phone number
        else if (content.contains("tel:")) {

            Pattern phoneNumberPattern = Pattern.compile("^tel:\\+?[0-9]+$");
            Matcher matcher = phoneNumberPattern.matcher(content);
            if (matcher.find()) {
                String phoneNumber = matcher.group().substring("tel:".length());
                editor.putString("phoneNumber", phoneNumber);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), PhoneActivity.class);
                intent.putExtra(Constants.QR_CODE_KEY, "" + content);
                startActivity(intent);
                finish();
            } else {
                showToast("No valid phone number found");
                Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
                startActivity(intent);
                finish();
            }

        }

        // Check for SMS
        else if (content.contains("SMSTO:")) {
            String[] parts = content.split(":");
            if (parts.length >= 3) {
                String phoneNumber = parts[1]; // Extract the phone number
                String message = parts[2]; // Extract the message

                editor.putString("phoneNumber", phoneNumber);
                editor.putString("message", message);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), SMSActivity.class);
                intent.putExtra(Constants.QR_CODE_KEY, "" + content);
                startActivity(intent);
                finish();


            } else {
                showToast("Invalid SMS format");
                Intent intent = new Intent(getApplicationContext(), WebViewPage.class);
                startActivity(intent);
                finish();
            }
        } else if (content.contains("WIFI:S")) {

            String getWifiName = extractValue(content, "WIFI:S:");
            String get_WAp = extractValue(content, ";T:");
            String get_Password = extractValue(content, ";P:");
            String get_bolan = extractValue(content, ";H:");


            editor.putString("getWifiName", getWifiName);
            editor.putString("get_WAp", get_WAp);
            editor.putString("get_Password", get_Password);
            editor.putString("get_bolan", get_bolan);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), WiFiActivity.class);
            intent.putExtra(Constants.QR_CODE_KEY, "" + content);
            startActivity(intent);
            finish();

        } else {
            sendPlainText(content);
        }


    }




}