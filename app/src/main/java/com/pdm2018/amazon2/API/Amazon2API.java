package com.pdm2018.amazon2.API;

import com.pdm2018.amazon2.models.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Amazon2API {

    String END_POINT = "http://gamenewsuca.herokuapp.com";

    @FormUrlEncoded
    @POST("/login")
    Call<Login> login(
            @Field("user") String username,
            @Field("password") String password
    );
}
