package com.test.service;

import com.test.model.Itinerary;
import java.util.List;

public interface ItineraryService {
    void createItinerary(Itinerary itinerary);

    List<Itinerary> getAllItineraries();
}
