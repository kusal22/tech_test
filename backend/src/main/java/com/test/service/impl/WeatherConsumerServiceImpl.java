package com.test.service.impl;

import com.test.model.WeatherRecord;
import com.test.model.dto.WeatherForecastDto;
import com.test.repository.WeatherRecordRepository;
import com.test.service.WeatherConsumerException;
import com.test.service.WeatherConsumerService;
import com.test.util.HelperUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class WeatherConsumerServiceImpl implements WeatherConsumerService {
    @Value("${weather.api.url}")
    private String weatherApiUrl;
    @Value("${weather.api.key}")
    private String apiKey;

    private final WeatherRecordRepository weatherRecordRepository;
    private final WebClient client = WebClient.create();

    public WeatherConsumerServiceImpl(WeatherRecordRepository weatherRecordRepository) {
        this.weatherRecordRepository = weatherRecordRepository;
    }

    /**
     * Find weather forecasts for a given city
     * @param city to search
     * @return collection of weather records
     */
    public List<WeatherRecord> findForecasts(String city){
        System.out.println(weatherApiUrl);
        //Search in cache
        List<WeatherRecord> weatherRecords = weatherRecordRepository.findAllByCity(city);
        if(!weatherRecords.isEmpty()){
            return weatherRecords;
        }
        //Call weather API and save
        String uri = String.format("q=%s&appid=%s", city, apiKey);
        Mono<WeatherForecastDto> forecastMono = client.get()
                .uri(weatherApiUrl+uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> !Objects.equals(HttpStatus.OK, status),
                    clientResponse -> Mono.error(new WeatherConsumerException(clientResponse.statusCode().toString())))
                .bodyToMono(WeatherForecastDto.class).log();

        WeatherForecastDto forecastDto = forecastMono.block();

        return weatherRecordRepository.saveAll(HelperUtils.mapToWeatherRecords(forecastDto));
    }
}
