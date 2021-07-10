package com.test.service.impl;

import com.test.model.WeatherRecord;
import com.test.repository.WeatherRecordRepository;
import com.test.service.WeatherRecordService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WeatherRecordServiceImpl implements WeatherRecordService {
    private WeatherRecordRepository weatherRecordRepository;

    public WeatherRecordServiceImpl(WeatherRecordRepository weatherRecordRepository) {
        this.weatherRecordRepository = weatherRecordRepository;
    }

    public void createWeatherRecords(Collection<WeatherRecord> weatherRecords){
        weatherRecordRepository.saveAll(weatherRecords);
    }
}
