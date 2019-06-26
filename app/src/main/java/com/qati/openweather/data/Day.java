package com.qati.openweather.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Day {

    private long dt;
    private Temp temp;
    @SerializedName("weather")
    private ArrayList<WeatherDescription> weatherDescriptions;


    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public ArrayList<WeatherDescription> getWeatherDescriptions() {
        return weatherDescriptions;
    }

    public void setWeatherDescriptions(ArrayList<WeatherDescription> weatherDescriptions) {
        this.weatherDescriptions = weatherDescriptions;
    }
}
