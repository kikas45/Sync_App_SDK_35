package sync2app.com.syncapplive.additionalSettings.ApiUrls

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sync2app.com.syncapplive.additionalSettings.utils.Constants


object RetrofitInstance_Ap_iUrls {
    fun createApiService(baseUrl: String): ApiUrlService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiUrlService::class.java)
    }
}

