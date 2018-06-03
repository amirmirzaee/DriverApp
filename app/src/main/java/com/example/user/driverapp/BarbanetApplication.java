package com.example.user.driverapp;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.user.driverapp.webService.ApiInterface;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BarbanetApplication extends Application {
private String tok = "tok";
    private ApiInterface client;
    private Retrofit retrofit;
    private static final String BACE_ULR = "http://192.168.7.7:8080/Barbanet/";

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sharedPreferences = getSharedPreferences(tok, MODE_PRIVATE);
        client= getClient(sharedPreferences).create(ApiInterface.class);


    }


    private synchronized Retrofit getClient(SharedPreferences sharedPreferences) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BACE_ULR)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient(sharedPreferences))
                    .build();
        }
        return retrofit;
    }
    private  OkHttpClient okHttpClient(final SharedPreferences sharedPreferences) {
//        String acssesToken;
//        if (sharedPreferences.contains(tok)){
//            acssesToken=sharedPreferences.getString(tok," ");
//        }
//        else
//        {
//            acssesToken=" ";
//        }
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
                                .addHeader("Authorization" , "Bearer " +sharedPreferences.getString(tok," "))
                                .build());
                    }
                })
                .build();

    }
    public ApiInterface getRetrofitClient(){return client;}
}
