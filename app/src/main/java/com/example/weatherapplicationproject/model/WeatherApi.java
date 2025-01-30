package com.example.weatherapplicationproject.model;

import android.os.Looper;
import android.util.Log;

import com.example.weatherapplicationproject.MainActivity;
import android.app.Activity;
// or if using AndroidX
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherApi extends AppCompatActivity {
    private static final String TAG = "WeatherAPI";
    private ForecastListener listener;

    public void fetchWeatherData() {
        String url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=62.39&lon=17.31";
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {  // Fixed lambda syntax
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()  // Fixed typo in "Request"
                    .url(url)
                    .addHeader("User-Agent", "WeatherApplicationProject/1.0")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String result = response.body().string();  // Fixed: need to call .string()
                runOnUiThread(() -> updateUI(result));  // Fixed capitalization
            } catch (IOException e) {
                e.printStackTrace();  // Removed extra semicolon
            }
        });
    }

    private void updateUI(String result) {
        if (result != null) {
            try {
                JSONObject jsonObject = new JSONObject(result);

                JSONArray timeseries = jsonObject.getJSONObject("properties")
                        .getJSONArray("timeseries");
                JSONObject currentForecast = timeseries.getJSONObject(0);

                JSONObject details = currentForecast.getJSONObject("data")
                        .getJSONObject("instant")
                        .getJSONObject("details");

                JSONObject data = currentForecast.getJSONObject("data");


                double temperature = details.getDouble("air_temperature");
                double windSpeed = details.getDouble("wind_speed");
                double cloudiness = details.getDouble("cloud_area_fraction");
                // Get precipitation values
                double rain = data.getJSONObject("next_1_hours")
                        .getJSONObject("details")
                        .getDouble("precipitation_amount");


                WeatherForecast forecast = new WeatherForecast(
                        temperature,
                        windSpeed,
                        rain,
                        cloudiness
                );

                // Fixed Handler creation
                if (listener != null) {
                    listener.updateForecast(forecast);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String readApiResponse(HttpURLConnection apiConnection) {
        try {
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(apiConnection.getInputStream());

            // Loop through each line in the response
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }



            // Close scanner to release resources
            scanner.close();

            // Return JSON data as a string
            return resultJson.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error reading API response: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void setForecastListener(ForecastListener listener) {
        this.listener = listener;
    }
}
