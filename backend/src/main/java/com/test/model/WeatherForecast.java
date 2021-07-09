package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {

    private String city;
    private String country;
    private List<WeatherData> list;

    @JsonProperty("city")
    private void unpackCity(Map<String,Object> mainMap) {
        this.city = (String) mainMap.get("name");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", list=" + list +
                '}';
    }
}
