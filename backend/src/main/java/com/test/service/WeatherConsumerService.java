package com.test.service;

import com.test.model.WeatherRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherConsumerService {
    public List<WeatherRecord> findForecasts (String city);
}
