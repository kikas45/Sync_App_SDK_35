package sync2app.com.syncapplive.additionalSettings.cloudAppsync.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sync2app.com.syncapplive.additionalSettings.utils.Constants;

public class RetrofitClientJava {

    private static RetrofitClientJava mInstance;
    private Retrofit retrofit;

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();

    private RetrofitClientJava() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.CLOUD_APP_SYNC_SERVER_TIME_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClientJava getInstance(){

        if (mInstance == null){

            mInstance = new RetrofitClientJava();

        }
        return mInstance;

    }

    public ApiJava getApi(){

        return retrofit.create(ApiJava.class);

    }
}
