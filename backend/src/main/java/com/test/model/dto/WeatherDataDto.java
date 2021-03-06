package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataDto {
    private static DecimalFormat df = new DecimalFormat("0.00");

    private double temperature;
    private String description;
    @JsonProperty("dt_txt")
    private String dateString;

    @JsonProperty("main")
    private void unpackMain(Map<String,Object> mainMap) {
        double tempKelvin = ((Number)mainMap.get("temp")).doubleValue();
        this.temperature = Math.round((tempKelvin - 273.15)*100)/100.00;
    }

    @JsonProperty("weather")
    private void unpackWeather(List<Map<String,Object>> weatherList) {
        if(weatherList.isEmpty()){
           return;
        }
        Map<String, Object> weatherMap = weatherList.get(0);
        this.description = (String) weatherMap.get("description");
    }

    public WeatherDataDto() {
    }

    public WeatherDataDto(double temperature, String description, String dateString) {
        this.temperature = temperature;
        this.description = description;
        this.dateString = dateString;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDataDto that = (WeatherDataDto) o;
        return Objects.equals(dateString, that.dateString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateString);
    }
}
