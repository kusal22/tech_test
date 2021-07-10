package com.test.service;

import com.test.model.dto.WeatherForecastDto;
import org.springframework.stereotype.Service;

@Service
public interface WeatherConsumerService {
    public WeatherForecastDto findForecasts (String city);
}
