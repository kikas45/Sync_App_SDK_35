package sync2app.com.syncapplive.additionalSettings.cloudAppsync.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import sync2app.com.syncapplive.additionalSettings.cloudAppsync.responses.ServerTimeResponse

interface ApiKT {

    // Fetch server time
    @GET("{company}/Servertime")
    suspend fun getServerTime(
        @Path("company") company: String?
    ): Response<ServerTimeResponse>

}