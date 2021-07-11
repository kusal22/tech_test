package com.test.controller;

import com.test.model.dto.WeatherForecastDto;
import com.test.service.WeatherConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travelplanner")
public class TravelPlannerController {
    Logger logger = LoggerFactory.getLogger(TravelPlannerController.class);
    private final WeatherConsumerService weatherConsumerService;

    public TravelPlannerController(WeatherConsumerService weatherConsumerService) {
        this.weatherConsumerService = weatherConsumerService;
    }

    @GetMapping("/forecast")
    public WeatherForecastDto getForecasts(@RequestParam String city){
        logger.debug("Weather forecast request received for {}", city);
        WeatherForecastDto forecasts = weatherConsumerService.findForecasts(city);
        System.out.println("return data");
        System.out.println(forecasts);
        return forecasts;
    }

    @PostMapping("/create")
    public void createItinerary(){
        logger.debug("Create Itinerary forecast request received");
    }
}
