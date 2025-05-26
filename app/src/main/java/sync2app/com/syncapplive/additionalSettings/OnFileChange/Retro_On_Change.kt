package sync2app.com.syncapplive.additionalSettings.OnFileChange

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retro_On_Change {
    fun create(baseUrl: String): ApiOnChnage {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiOnChnage::class.java)
    }
}
