package sync2app.com.syncapplive.additionalSettings.USBCamera.CamModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CamApiService {
    @GET
    suspend fun getAppConfig(@Url url: String): Response<CamDisplaySettings>
}
