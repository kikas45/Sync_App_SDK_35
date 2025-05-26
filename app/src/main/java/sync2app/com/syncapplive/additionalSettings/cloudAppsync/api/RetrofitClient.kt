package sync2app.com.syncapplive.additionalSettings.cloudAppsync.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {

    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    internal fun getApi(): ApiKT {
        return retrofit.create(ApiKT::class.java)
    }

    companion object {
        private const val BASE_URL = "https://cloudappserver.co.uk/cp/app_base/public/"
        @Volatile
        private var mInstance: RetrofitClient? = null

        fun getInstance(): RetrofitClient {
            return mInstance ?: synchronized(this) {
                mInstance ?: RetrofitClient().also { mInstance = it }
            }
        }
    }
}
