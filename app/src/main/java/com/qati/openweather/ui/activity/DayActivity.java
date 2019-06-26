package com.qati.openweather.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.qati.openweather.R;
import com.qati.openweather.api.ProjectRequests;
import com.qati.openweather.data.Weather;
import com.qati.openweather.ui.adapter.DayAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DayActivity extends AppCompatActivity {

    private static final String TAG = "DayActivity";
    public static final String EXTRAS_CITY_NAME = "city_name";
    public static final String EXTRAS_CITY_ID = "city_id";
    RecyclerView rvDays;
    TextView tvChosenCityName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);


        rvDays = findViewById(R.id.rvDaysAndWeather);
        tvChosenCityName = findViewById(R.id.tvChosenCityName);

        String cityName = getIntent().getStringExtra(EXTRAS_CITY_NAME);
        tvChosenCityName.setText(cityName);

        final int cityId = getIntent().getIntExtra(EXTRAS_CITY_ID, -1);

        rvDays.setLayoutManager(new LinearLayoutManager(this));

        new Thread(new Runnable() {
            @Override
            public void run() {
                ProjectRequests
                        .getInstance()
                        .getOpenWeatherApi()
                        .getWeather(cityId, "b1b15e88fa797225412429c1c50c122a1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Weather>() {
                            @Override
                            public void accept(Weather weather) throws Exception {
                                Log.d(TAG, "response.body().toString()");
                                rvDays.setAdapter(new DayAdapter(weather.getList()));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
            }
        }).run();


    }
}
