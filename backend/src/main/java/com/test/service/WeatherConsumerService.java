package com.test.service;

import org.springframework.stereotype.Service;

@Service
public interface WeatherConsumerService {
    public void findForecasts (String city);
}
