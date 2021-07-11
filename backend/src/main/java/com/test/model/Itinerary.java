package com.test.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private LocalDateTime createdTime;

    @ManyToMany
    @JoinTable(
            name = "itinerary_wheather_data",
            joinColumns = @JoinColumn(name = "itinerary_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id"))
    private Set<WeatherRecord> whetherRecords;

    public Itinerary(String name) {
        this.name = name;
    }

    public Itinerary() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Set<WeatherRecord> getWhetherRecords() {
        return whetherRecords;
    }

    public void setWhetherRecords(Set<WeatherRecord> whetherRecords) {
        this.whetherRecords = whetherRecords;
    }
}
