package com.example.weatherapplicationproject.model;
public class WeatherForecast {
    private final double temperature;
    private final double windSpeed;
    private final double rain;
    private final double cloudiness;


    public WeatherForecast(double temperature, double windSpeed,
                           double rain, double cloudiness) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.rain = rain;
        this.cloudiness = cloudiness;
    }

    // Getters
    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
    public double getRain() {
        return rain;
    }
    public double getCloudiness() {
        return cloudiness;
    }
}
