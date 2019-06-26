package com.qati.openweather.data;

import java.util.ArrayList;

public class Weather {
    City city;
    ArrayList<Day> list;

    public ArrayList<Day> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setList(ArrayList<Day> list) {
        this.list = list;
    }
}
