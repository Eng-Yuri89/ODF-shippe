package com.nanotrick.ordershipper.Remote;

import com.nanotrick.ordershipper.Model.Request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGeoCoordinates {
    @GET("maps/api/geocode/json")
    Call<String> getGeocode(@Query("address") Request address);

    @GET("maps/api/direction/json")
    Call<String> getDirection(@Query("origin") String origin, @Query("destination") String destination);

    Call<String> getGeoCode(Request address);

    Call<String> getDirections(String s, String s1);
}
