package com.example.weatherapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapplicationproject.model.ForecastListener;
import com.example.weatherapplicationproject.model.WeatherApi;
import com.example.weatherapplicationproject.model.WeatherForecast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ForecastListener {
    private WeatherApi api = new WeatherApi();
    private TextView tempText, humText, descText, windText;
    private ImageView weatherIcon;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate started");

        // Initialize views
        tempText = findViewById(R.id.tempText);
        humText = findViewById(R.id.humText);
        descText = findViewById(R.id.descText);
        windText = findViewById(R.id.windText);

        Log.d("MainActivity", "Views initialized");

        api = new WeatherApi();
        api.setForecastListener(this);
        Log.d("MainActivity", "WeatherApi initialized");

        // Add this line to trigger the API call
        api.fetchWeatherData();
        Log.d("MainActivity", "fetchWeatherData called");
    }

    @Override
    public void updateForecast(WeatherForecast forecast) {
        Log.d("MainActivity", "updateForecast called");

        tempText.setText(String.format("%.0f°", forecast.getTemperature()));
        humText.setText(String.format("%.0f mm", forecast.getRain()));
        windText.setText(String.format("%.0f m/s", forecast.getWindSpeed()));
        descText.setText(String.format("Cloudiness: %.0f%%", forecast.getCloudiness()));

        Log.d("MainActivity", "UI update complete");
    }

}