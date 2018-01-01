package com.napt.studentregister.di.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sreelal on 6/12/17.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("center-login")
    Call<ResponseBody> login_call
            (@Field("username") String name,
             @Field("password") String password);

    @GET("listattachmenttype")
    Call<ResponseBody> attachment_call();





}
