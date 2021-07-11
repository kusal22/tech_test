package com.test.repository;

import com.test.model.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {

    List<WeatherRecord> findAllByCity(String city);

    @Query(value = "SELECT r FROM WeatherRecord r WHERE r.id IN :ids")
    Set<WeatherRecord> findWeatherRecordByByIdList(@Param("ids") Collection<Long> ids);

}
