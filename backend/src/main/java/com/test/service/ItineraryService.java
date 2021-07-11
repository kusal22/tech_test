package com.test.service;

import com.test.model.Itinerary;
import com.test.model.dto.ItineraryDto;

import java.util.List;

public interface ItineraryService {
    void createItinerary(ItineraryDto itinerary);

    List<Itinerary> getAllItineraries();
}
