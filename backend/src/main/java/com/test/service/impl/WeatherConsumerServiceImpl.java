package com.test.service.impl;

import com.test.model.WeatherRecord;
import com.test.model.dto.WeatherDataDto;
import com.test.model.dto.WeatherForecastDto;
import com.test.repository.WeatherRecordRepository;
import com.test.service.WeatherConsumerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherConsumerServiceImpl implements WeatherConsumerService {
    @Value("${weather.api.url}")
    private String weatherApiUrl;
    @Value("${weather.api.key}")
    private String apiKey;

    private final WeatherRecordRepository weatherRecordRepository;
    private final WebClient client = WebClient.create();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public WeatherConsumerServiceImpl(WeatherRecordRepository weatherRecordRepository) {
        this.weatherRecordRepository = weatherRecordRepository;
    }

    /**
     * Find weather forecasts for a given city
     * @param city to search
     * @return collection of weather records
     */
    public WeatherForecastDto findForecasts(String city) {
        System.out.println(weatherApiUrl);
        //Search in cache
//        List<WeatherRecord> weatherRecords = weatherRecordRepository.findAllByCity(city);
//        if(!weatherRecords.isEmpty()){
//            return mapToWeatherForecast(weatherRecords);
//        }
        //Call weather API and save
        String uri = String.format("q=%s&appid=%s", city, apiKey);
        Mono<WeatherForecastDto> forecastMono = client.get()
                .uri(weatherApiUrl+uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherForecastDto.class).log();

        forecastMono.subscribe(forecast -> System.out.println("Mono without block:" + forecast));
        WeatherForecastDto forecastDto = forecastMono.block();

//        weatherRecordRepository.saveAll(mapToWeatherRecords(forecastDto));

        return forecastDto;
    }

    private WeatherForecastDto mapToWeatherForecast(List<WeatherRecord> weatherRecords){
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

    private List<WeatherRecord> mapToWeatherRecords(WeatherForecastDto dto){
       return dto.getList().stream()
                .map(data -> new WeatherRecord(
                                dto.getCity(),
                                dto.getCountryCode(),
                                data.getTemperature(),
                                data.getDescription(),
                                LocalDateTime.parse(data.getDateString(), formatter)))
                .collect(Collectors.toList());
    }
}
