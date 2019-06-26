package com.qati.openweather.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qati.openweather.R;
import com.qati.openweather.data.City;
import com.qati.openweather.ui.adapter.CityAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    RecyclerView rvCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCities = findViewById(R.id.rvCities);

        rvCities.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<City> cities = loadCitiesFromResources();
        rvCities.setAdapter(new CityAdapter(cities) {
            @Override
            public void onCityClick(City city) {
                Intent intent = new Intent(CityActivity.this, DayActivity.class);
                intent.putExtra(DayActivity.EXTRAS_CITY_NAME, city.getName());
                intent.putExtra(DayActivity.EXTRAS_CITY_ID, city.getId());
                startActivity(intent);
            }
        });

    }

    private ArrayList<City> loadCitiesFromResources() {
        try {
            String JSONCities = readJSONFromRaw();


            ArrayList<City> cities = new Gson().fromJson(JSONCities, new TypeToken<List<City>>() {
            }.getType());
            return cities;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Не удалось загрузить список городов", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private String readJSONFromRaw() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.cities);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        return writer.toString();
    }
}
