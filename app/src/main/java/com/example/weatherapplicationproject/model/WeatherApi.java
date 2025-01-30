package com.example.weatherapplicationproject.model;

import android.util.Log;

import com.example.weatherapplicationproject.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApi {
    private static final String TAG = "WeatherAPI";
    private ForecastListener listener;

    public WeatherForecast callWeatherAPI() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=62.39&lon=17.31");
            connection = (HttpURLConnection) url.openConnection();

            // Tells the API who's making request - and it's needed for the API
            connection.setRequestProperty("User-Agent", "WeatherApp/1.0");

            int responseCode = connection.getResponseCode();

            // If connection was unsuccessful
            if (responseCode != 200) {
                Log.e(TAG, "Connection was unsuccessful");
                return null;
            }

            // Read response and store it as a string
            String jsonResponse = readApiResponse(connection);

            // Parse the response and return forecast
            return parseWeatherData(jsonResponse);

        } catch (Exception e) {
            Log.e(TAG, "Error in API call: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    private WeatherForecast parseWeatherData(String jsonResponse) throws Exception {

        // Create JSONObject directly from string
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Navigate to timeseries array and get first forecast
        JSONArray timeseries = jsonObject.getJSONObject("properties")
                .getJSONArray("timeseries");
        JSONObject currentForecast = timeseries.getJSONObject(0);

        // Get current weather details
        JSONObject details = currentForecast.getJSONObject("data")
                .getJSONObject("instant")
                .getJSONObject("details");

        // Current weather values
        double temperature = details.getDouble("air_temperature");
        double windSpeed = details.getDouble("wind_speed");
        double cloudiness = details.getDouble("cloud_area_fraction");

        // Get data object for precipitation
        JSONObject data = currentForecast.getJSONObject("data");

        // Get precipitation values
        double rain1Hour = data.getJSONObject("next_1_hours")
                .getJSONObject("details")
                .getDouble("precipitation_amount");

        double rain6Hours = data.getJSONObject("next_6_hours")
                .getJSONObject("details")
                .getDouble("precipitation_amount");

        // Create and return forecast object
        WeatherForecast result =  new WeatherForecast(temperature, windSpeed, rain1Hour, rain6Hours, cloudiness);
        listener.updateForecast(result);
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
