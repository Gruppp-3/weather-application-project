package com.example.weatherapplicationproject.model;
public class WeatherForecast {
    private final double temperature;
    private final double windSpeed;
    private final double rain1h;
    private final double rain6h;
    private final double cloudiness;

    public WeatherForecast(double temperature, double windSpeed,
                           double rain1h, double rain6h, double cloudiness) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.rain1h = rain1h;
        this.rain6h = rain6h;
        this.cloudiness = cloudiness;
    }

    // Getters
    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getRain1h() {
        return rain1h;
    }

    public double getRain6h() {
        return rain6h;
    }

    public double getCloudiness() {
        return cloudiness;
    }
}
