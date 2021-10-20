package com.sort.sortcore.data;

import javax.persistence.*;

@Entity
@Table(name = "eventcities")
public class EventCities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EEventCities eventCities;

    public EventCities() {
    }

    public EventCities(EEventCities eventCities) {
        this.eventCities = eventCities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EEventCities getEventCities() {
        return eventCities;
    }

    public void setEventCities(EEventCities eventCities) {
        this.eventCities = eventCities;
    }
}