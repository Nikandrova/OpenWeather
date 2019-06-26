package com.qati.openweather.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qati.openweather.R;
import com.qati.openweather.data.Day;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    public static final String FORMAT_dd_MMMM_yyyy = "dd MMMM yyyy";

    private ArrayList<Day> days;


    public DayAdapter(ArrayList<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_day, viewGroup, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder vh, int i) {
        vh.tvDay.setText(getFormattedDate(days.get(i).getDt()));
        vh.tvWeatherDay.setText(days.get(i).getWeatherDescriptions().get(0).getMain() + "\n" + days.get(i).getWeatherDescriptions().get(0).getDescription());
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay;
        TextView tvWeatherDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDay = itemView.findViewById(R.id.tvDay);
            tvWeatherDay = itemView.findViewById(R.id.tvWeatherDay);
        }
    }


    public static String getFormattedDate(long millis) {

        Date date = new Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_dd_MMMM_yyyy);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }
}
