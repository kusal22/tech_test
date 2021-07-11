package com.test.util;

import com.test.model.Itinerary;
import com.test.model.WeatherRecord;
import com.test.model.dto.ItineraryDto;
import com.test.model.dto.WeatherDataDto;
import com.test.model.dto.WeatherForecastDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

public class HelperUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static WeatherForecastDto mapToWeatherForecast(List<WeatherRecord> weatherRecords){
        WeatherForecastDto weatherForecast = new WeatherForecastDto();
        weatherForecast.setCity(weatherRecords.get(0).getCity());
        weatherForecast.setCountryCode(weatherRecords.get(0).getCountry());

        List<WeatherDataDto> weatherDataList = weatherRecords.stream()
                .map(record -> new WeatherDataDto(
                        record.getTemperature(),
                        record.getDescription(),
                        record.getDateTime().format(formatter)))
                .collect(Collectors.toList());
        weatherForecast.setList(weatherDataList);
        return weatherForecast;
    }

    public static List<WeatherRecord> mapToWeatherRecords(WeatherForecastDto dto){
        return dto.getList().stream()
                .map(data -> new WeatherRecord(
                        dto.getCity(),
                        dto.getCountryCode(),
                        data.getTemperature(),
                        data.getDescription(),
                        LocalDateTime.parse(data.getDateString(), formatter)))
                .collect(Collectors.toList());
    }

    public static List<ItineraryDto> mapToItineraryDtos(List<Itinerary> itineraries){
        return itineraries.stream()
                .map(itinerary -> {
                    Map<String, Set<Long>> cityIdMap = itinerary.getWhetherRecords().stream()
                            .collect(groupingBy(WeatherRecord::getCity,
                                    mapping(WeatherRecord::getId, Collectors.toSet())));
                    return new ItineraryDto(itinerary.getId(), cityIdMap);
                }).collect(Collectors.toList());
    }


}
