package sync2app.com.syncapplive.additionalSettings.ApITVorAppMode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiTVModeService {
    @GET("{path}")
    suspend fun getAppConfig(@Path("path") path: String): Response<ApiResponse>
}

