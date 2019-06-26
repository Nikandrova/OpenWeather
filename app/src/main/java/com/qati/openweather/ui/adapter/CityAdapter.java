package com.qati.openweather.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qati.openweather.R;
import com.qati.openweather.data.City;
import com.qati.openweather.ui.activity.DayActivity;

import java.util.ArrayList;

public abstract class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private ArrayList<City> cities;

    public CityAdapter(ArrayList<City> cities) {
        this.cities = cities;
    }


    //определяем вертску для отдельного элемента списка
    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_city, viewGroup, false);

        return new CityViewHolder(view);
    }

    //устанавливаем данные в отедльный элемент списка
    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder vh, final int index) {
        vh.tvCityName.setText(cities.get(index).getName());
        vh.tvWeather.setText(cities.get(index).getCountry());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCityClick(cities.get(index));
            }
        });

    }

    public abstract void onCityClick(City city);

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView tvCityName;
        TextView tvWeather;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCityName = itemView.findViewById(R.id.tvCityName);
            tvWeather = itemView.findViewById(R.id.tvWeather);
        }
    }
}
