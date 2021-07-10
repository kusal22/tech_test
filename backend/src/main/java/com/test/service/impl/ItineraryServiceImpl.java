package com.test.service.impl;

import com.test.model.Itinerary;
import com.test.repository.ItineraryRepository;
import com.test.service.ItineraryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {
    private ItineraryRepository itineraryRepository;

    public ItineraryServiceImpl(ItineraryRepository itineraryRepository) {
        this.itineraryRepository = itineraryRepository;
    }

    @Override
    public void createItinerary(Itinerary itinerary){
        itineraryRepository.save(itinerary);
    }

    @Override
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }
}
