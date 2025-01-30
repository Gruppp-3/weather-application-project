package com.example.weatherapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.weatherapplicationproject.model.ForecastListener;
import com.example.weatherapplicationproject.model.WeatherApi;
import com.example.weatherapplicationproject.model.WeatherForecast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ForecastListener
{
    WeatherApi api = new WeatherApi();
    WeatherForecast forecast = api.callWeatherAPI();
    private TextView tempText, humText, descText, windText;
    private ImageView weatherIcon;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        descText = findViewById(R.id.descText);
        windText = findViewById(R.id.windText);

        api = new WeatherApi();
        api.setForecastListener(this);
        updateCloudiness("Sunny %");
        updateWind(windVal," km/h");
    }


    private void updateCloudiness(String description) {
        if (descText != null) descText.setText(description);

    }
    double windVal = forecast.getWindSpeed();

    private void updateWind(double windVal, String description) {
        if (windText != null) {
            windText.setText(description + windVal);
        }
    }


    @Override
    public void updateForecast(WeatherForecast forecast) {

    }
}






