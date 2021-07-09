package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private double temperature;
    private String description;
    @JsonProperty("dt_txt")
    private String dateString;

    @JsonProperty("main")
    private void unpackMain(Map<String,Object> mainMap) {
        this.temperature = (double)mainMap.get("temp");
    }

    @JsonProperty("weather")
    private void unpackWeather(List<Map<String,Object>> weatherList) {
        if(weatherList.isEmpty()){
           return;
        }
        Map<String, Object> weatherMap = weatherList.get(0);
        this.description = (String) weatherMap.get("description");
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", description='" + description + '\'' +
                ", dateString='" + dateString + '\'' +
                '}';
    }
}
