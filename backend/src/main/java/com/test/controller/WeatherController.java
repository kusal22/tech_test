package com.test.controller;

import com.test.service.WeatherConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
public class WeatherController {
    private WeatherConsumerService weatherConsumerService;

    public WeatherController(WeatherConsumerService weatherConsumerService) {
        this.weatherConsumerService = weatherConsumerService;
    }

    @GetMapping("/forecast")
    public void getForecasts(){
        weatherConsumerService.findForecasts("Adelaide");
        System.out.println("Get Receiced");
    }
}
