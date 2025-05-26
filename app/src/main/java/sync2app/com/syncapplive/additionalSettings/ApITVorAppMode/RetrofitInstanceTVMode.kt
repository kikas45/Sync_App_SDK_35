package sync2app.com.syncapplive.additionalSettings.ApITVorAppMode
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceTVMode {
    fun createApiService(baseUrl: String): ApiTVModeService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiTVModeService::class.java)
    }
}
