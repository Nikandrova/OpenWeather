package com.qati.openweather.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {
    @GET("/data/2.5/forecast/daily")
    Observable<com.qati.openweather.data.Weather> getWeather(@Query("id") int cityId, @Query("appid") String appid);

}
