package sync2app.com.syncapplive.additionalSettings.urlchecks

import java.net.MalformedURLException
import java.net.URL

fun isUrlValid(inputUrl: String): Boolean {
    return try {
        URL(inputUrl)
        true
    } catch (e: MalformedURLException) {
        false
    }
}