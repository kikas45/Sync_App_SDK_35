package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import java.net.URLConnection

class ZipDownloader(private val helper: DownloadHelper) : AsyncTask<String, String, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        helper.whenExecutionStarts()
    }

    override fun onCancelled() {
        super.onCancelled()
        cancel(true)
    }

    override fun doInBackground(vararg f_url: String): String? {
        try {
            // Get strings
            val fileUrl = f_url[0]
            val theFileDestination = f_url[1]

            // Check file
            val theDes = File(theFileDestination)
            if (theDes.exists()) {
                theDes.delete()
            }

            // Parse URL and connect
            val url = URL(fileUrl)
            val connection: URLConnection = url.openConnection()
            connection.connect()

            // Get length of file for progress calculation
            val lengthOfFile: Int = connection.contentLength

            // Save file to phone memory
            val input: InputStream = BufferedInputStream(url.openStream(), 8192)
            val output: OutputStream = FileOutputStream(theFileDestination, false)
            val data = ByteArray(1024)
            var total: Long = 0
            while (true) {
                val read: Int = input.read(data)
                val count = read
                if (read != -1) {
                    total += count.toLong()
                    try {
                        publishProgress("${(total * 100 / lengthOfFile).toInt()}")
                        output.write(data, 0, count)
                    } catch (e: Exception) {
                       // Log.e("Error Occurred: ", e.message)
                        return null
                    }
                } else {
                    output.flush()
                    output.close()
                    input.close()
                    return null
                }
            }
        } catch (e2: Exception) {
           // Log.e("Error Occurred: ", e2.message)
            helper.afterExecutionIsComplete()
            return null
        }
    }

    override fun onProgressUpdate(vararg progress: String) {
        helper.whileInProgress(progress[0].toInt())
    }

    override fun onPostExecute(file_url: String?) {
        helper.afterExecutionIsComplete()
    }
}
