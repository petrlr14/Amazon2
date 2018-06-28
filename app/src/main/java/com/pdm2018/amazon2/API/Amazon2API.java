package com.pdm2018.amazon2.API;

import com.pdm2018.amazon2.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Amazon2API {

    @GET("/products")
    Call<Example> login();
}
