package com.example.user.driverapp.webService;

import android.content.SharedPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String BACE_ULR = "http://192.168.7.7:8080/Barbanet/";
    private static Retrofit retrofit = null;
    private OkHttpClient okHttpClient;

    public static Retrofit getClient(SharedPreferences sharedPreferences) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BACE_ULR)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient(sharedPreferences))
                    .build();
        }
        return retrofit;
    }


    private static OkHttpClient okHttpClient(SharedPreferences sharedPreferences) {
       return new OkHttpClient.Builder()
                .connectTimeout(60 , TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request()
                                .newBuilder()
                                .addHeader("CSN", "100000000000000"/*prefStorage.readMessage(ConstantManager.DEVICE_INF0O.IMEI.value())*/)
                                .addHeader("CTY" , "m")
//                                .addHeader("Authorization" , "Bearer " +prefStorage.readMessage(ConstantManager.PUBLIC.ACCESS_TOKEN.value()))
                                .build());
                    }
                })
                .build();

    }
}
