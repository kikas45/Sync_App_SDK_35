package sync2app.com.syncapplive.additionalSettings.cloudAppsync.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sync2app.com.syncapplive.additionalSettings.cloudAppsync.responses.ServerTimeResponse;

public interface ApiJava {

    //fetch server time
    @GET("{company}/Servertime")
    Call<ServerTimeResponse> getServerTime(
            @Path("company") String company
    );

}