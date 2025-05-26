package sync2app.com.syncapplive.additionalSettings.USBCamera.CamModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object CamRetrofitInstance {
    fun create(baseUrl: String): CamApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CamApiService::class.java)
    }
}
