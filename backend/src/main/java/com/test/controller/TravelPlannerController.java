package com.test.controller;

import com.test.model.Itinerary;
import com.test.model.WeatherRecord;
import com.test.model.dto.ItineraryDto;
import com.test.model.dto.WeatherForecastDto;
import com.test.service.ItineraryService;
import com.test.service.WeatherConsumerException;
import com.test.service.WeatherConsumerService;
import com.test.util.HelperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/travelplanner")
public class TravelPlannerController {
    Logger logger = LoggerFactory.getLogger(TravelPlannerController.class);
    private final WeatherConsumerService weatherConsumerService;
    private final ItineraryService itineraryService;

    public TravelPlannerController(WeatherConsumerService weatherConsumerService, ItineraryService itineraryService) {
        this.weatherConsumerService = weatherConsumerService;
        this.itineraryService = itineraryService;
    }

    @GetMapping("/forecast")
    public WeatherForecastDto getForecasts(@RequestParam String city){
        logger.info("Weather forecast request received for {}", city);
        List<WeatherRecord> weatherRecords = weatherConsumerService.findForecasts(city);
        return HelperUtils.mapToWeatherForecast(weatherRecords);
    }

    @GetMapping("/itineraries")
    public List<ItineraryDto> getItineraries(){
        logger.info("Get all Itineraries request received");
        List<Itinerary> allItineraries = itineraryService.getAllItineraries();
        return HelperUtils.mapToItineraryDtos(allItineraries);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createItinerary(@RequestBody ItineraryDto newItineraryDto){
        logger.debug("Create Itinerary forecast request received: Not implemented");
        itineraryService.createItinerary(newItineraryDto);
        return ResponseEntity.ok("Itinerary created");
    }

    @ExceptionHandler({ WeatherConsumerException.class})
    public ResponseEntity<Object> handleException(WeatherConsumerException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
