package com.test.service.impl;

import com.test.model.Itinerary;
import com.test.model.WeatherRecord;
import com.test.model.dto.ItineraryDto;
import com.test.repository.ItineraryRepository;
import com.test.repository.WeatherRecordRepository;
import com.test.service.ItineraryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItineraryServiceImpl implements ItineraryService {
    private ItineraryRepository itineraryRepository;
    private WeatherRecordRepository weatherRecordRepository;

    public ItineraryServiceImpl(ItineraryRepository itineraryRepository, WeatherRecordRepository weatherRecordRepository) {
        this.itineraryRepository = itineraryRepository;
        this.weatherRecordRepository = weatherRecordRepository;
    }

    public void createItinerary(ItineraryDto itineraryDto){
        Map<String, Set<Long>> weatherData = itineraryDto.getData();
        List<Long> recordIds = weatherData.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toList());
        Set<WeatherRecord> weatherRecords = weatherRecordRepository.findWeatherRecordByByIdList(recordIds);

        Itinerary newItinerary = new Itinerary(itineraryDto.getName());
        newItinerary.setWhetherRecords(weatherRecords);

        itineraryRepository.save(newItinerary);
    }

    @Override
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }
}
