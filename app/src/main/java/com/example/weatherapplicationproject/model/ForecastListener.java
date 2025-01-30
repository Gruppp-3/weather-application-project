package com.example.weatherapplicationproject.model;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public interface ForecastListener {
    void updateForecast(WeatherForecast forecast);
}