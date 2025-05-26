package sync2app.com.syncapplive.additionalSettings.HardwareModel

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("hdw_check.json")
    suspend fun getAppConfig(): Response<DeviceList>
}
