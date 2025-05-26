package sync2app.com.syncapplive.additionalSettings.ApiUrls

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import sync2app.com.syncapplive.additionalSettings.utils.Constants

interface ApiUrlService {
    @GET
    suspend fun getAppConfig(@Url fullPath: String): Response<Api_Ur_lModels>
}
