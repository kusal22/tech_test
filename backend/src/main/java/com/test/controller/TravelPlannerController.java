package com.test.controller;

import com.test.model.dto.WeatherForecastDto;
import com.test.service.WeatherConsumerException;
import com.test.service.WeatherConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> createItinerary(){
        logger.debug("Create Itinerary forecast request received: Not implemented");
        return ResponseEntity.ok("Not Implemented");
    }

    @ExceptionHandler({ WeatherConsumerException.class})
    public ResponseEntity<Object> handleException(WeatherConsumerException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
