package com.example.weatherapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapplicationproject.model.WeatherApi;
import com.example.weatherapplicationproject.model.WeatherForecast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView cityNameText, tempText, humText, descText, windText;
    private ImageView weatherIcon;
    private Button refreshButton;
    private EditText cityNameInput;

    WeatherApi api = new WeatherApi();
    WeatherForecast forecast = api.callWeatherAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        descText = findViewById(R.id.descText);
        windText = findViewById(R.id.windText);

        api = new WeatherApi();

        updateCloudiness("Sunny %");
        updateWind(forecast.getWindSpeed());

    }

    private void updateCloudiness(String description){
        if (descText != null) descText.setText(description);

    }

    private void updateWind(double windSpeed) {
        if (windText != null) {
            windText.setText(String.format("%.0f km/h", windSpeed));
        }
    }
}








