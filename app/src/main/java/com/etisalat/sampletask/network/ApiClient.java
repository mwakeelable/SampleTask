package com.etisalat.sampletask.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class ApiClient {
    private static String API_BASE_URL = "https://api.androidhive.info/";
    public static ApiService service;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        } else {
            return retrofit;
        }
        return retrofit;
    }

    public ApiService getWebServices() {
        if (service == null) {
            service = getRetrofit().create(ApiService.class);
        } else {
            return service;
        }
        return service;
    }
}
