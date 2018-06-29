package com.etisalat.sampletask.network;

import com.etisalat.sampletask.model.Menu;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public interface ApiService {
    @GET("pizza/?format=xml")
    Call<Menu> getMenu();
}
