package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastDto {
    private long id;
    private String city;
    private String countryCode;
    private List<WeatherDataDto> list;

    @JsonProperty("city")
    private void unpackCity(Map<String,Object> mainMap) {
        this.city = (String) mainMap.get("name");
        this.countryCode = (String) mainMap.get("country");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WeatherForecastDto() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<WeatherDataDto> getList() {
        return list;
    }

    public void setList(List<WeatherDataDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "city='" + city + '\'' +
                ", country='" + countryCode + '\'' +
                ", list=" + list +
                '}';
    }
}
