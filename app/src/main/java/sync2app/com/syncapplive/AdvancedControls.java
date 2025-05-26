package sync2app.com.syncapplive;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import static android.content.ContentValues.TAG;


public class AdvancedControls {


    public static File path = constants.DownloadPath;

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert con_manager != null;
        return (con_manager.getActiveNetworkInfo() == null
                || !con_manager.getActiveNetworkInfo().isAvailable()
                || !con_manager.getActiveNetworkInfo().isConnected());
    }

/*
    public static void CompletionReciever(Context context) {

        constants.currentFileUri = FileProvider.getUriForFile(context, constants.AUTHORITY, new File(path
                + "/" + constants.currentDownloadFileName));
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            //    DownloadFinishedAction(context);

            }
        };
        context.registerReceiver(receiver, filter);
    }
*/

    public static void DownloadFinishedAction(Context context) {
        try {


            new AlertDialog.Builder(context)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Download finished")
//                .setMessage("What do you like to do?")
                    .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            openx(context, path);
                        }

                    })


                    .setNeutralButton("Share", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Uri uri = FileProvider.getUriForFile(context, constants.AUTHORITY, new File(path
                                            + "/" + constants.currentDownloadFileName));
                                    ShareFile(context, uri, constants.currentDownloadFileMimeType);
                                }

                            }

                    ).setNegativeButton("Cancel", null)

                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void openx(Context context, File path) {


        Uri uri;
        try {
            uri = FileProvider.getUriForFile(context, constants.AUTHORITY, new File(path
                    + "/" + constants.currentDownloadFileName));


        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unable to get content url from FileProvider", e);
            return;
        }

        @SuppressLint("UnsafeImplicitIntentLaunch") Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(uri.toString()), constants.currentDownloadFileMimeType);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {

            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void ShareFile(Context context, Uri fileUri, String mimetype) {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType(mimetype);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Share with"));
    }


    public static void showToast(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }catch ( Exception e ){}

    }

    public static boolean isColorDark(int color){
        double darkness = 1-(0.299* Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
        if(darkness<0.5){
            return false; // It's a light color
        }else{
            return true; // It's a dark color
        }
    }



    public static void DownloadRemoteFile(Context context, String fileurl) {



//        showToast(context,"executing");

        try {
        URL url = new URL(fileurl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.connect();

            File path  = Environment.getExternalStorageDirectory();
            File dir = new File(path+"/Gallery");
            dir.mkdir();

        String fname="splash.png";
            File file = new File(dir,fname);
        FileOutputStream fileOutput = new FileOutputStream(file);
        InputStream inputStream = urlConnection.getInputStream();

        byte[] buffer = new byte[1024];
        int bufferLength;

        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
            fileOutput.write(buffer, 0, bufferLength);
        }
        fileOutput.close();
    } catch (final Exception e) {
        e.printStackTrace();
//            showToast(context, Arrays.toString(e.getStackTrace()));
    }
}

    }

