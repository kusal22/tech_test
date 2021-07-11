package com.test.model.dto;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ItineraryDto {
    private long id;
    private String name;
    private Map<String, Set<Long>> data;

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

    public Map<String, Set<Long>> getData() {
        return data;
    }

    public void setData(Map<String, Set<Long>> data) {
        this.data = data;
    }

    public ItineraryDto(long id, Map<String, Set<Long>> data) {
        this.id = id;
        this.data = data;
    }

    public ItineraryDto() {
    }

    @Override
    public String toString() {
        return "ItineraryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItineraryDto that = (ItineraryDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
