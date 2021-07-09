package com.test.service.impl;

import com.test.model.WeatherForecast;
import com.test.service.WeatherConsumerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherConsumerServiceImpl implements WeatherConsumerService {
//    @Value("${weather.api.url}")
//    private String weatherApiUrl;

    private final WebClient client = WebClient.create("api.openweathermap.org/data/2.5/forecast?q=Adelaide&appid=b3f16f8fad023a328dec3696ef1d96ea");

    public void findForecasts(String city) {
//        System.out.println(weatherApiUrl);
        Mono<WeatherForecast> forecastMono = client.get()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherForecast.class).log();

        forecastMono.subscribe(forecast -> System.out.println("Mono without block:" + forecast));


//        ObjectMapper mapper = new ObjectMapper();
//        Object[] objects = response.block();
//        System.out.println(objects);
//        return Arrays.stream(objects)
//                .map(object -> mapper.convertValue(object, Reader.class))
//                .map(Reader::getFavouriteBook)
//                .collect(Collectors.toList());
    }

}
