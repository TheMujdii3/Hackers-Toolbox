package com.example.hackerstoolbox;

import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

public interface WriteupService
{
    // Fetch all writeups
    @GET("api/get_writeup.php")
    Call<List<Writeup>> getAllWriteups();

    // Fetch writeups filtered by tag
    @GET("api/get_writeups.php")
    Call<List<Writeup>> getWriteupsByTag(@Query("tag") String tag);

    // Upload a new writeup
    @POST("api/upload_writeup.php")
    Call<Map<String, Object>> uploadWriteup(@Body Writeup writeup);
}
