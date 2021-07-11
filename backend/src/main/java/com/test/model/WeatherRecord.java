package com.test.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class WeatherRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String city;
    private String country;
    private double temperature;
    private String description;
    private LocalDateTime dateTime;
    @ManyToMany(mappedBy = "whetherRecords")
    Set<Itinerary> itineraries;

    public WeatherRecord() {
    }

    public WeatherRecord(String city, String country, double temperature, String description, LocalDateTime dateTime) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.description = description;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(Set<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
}
