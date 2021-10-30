package com.sort.sortcore.data;

import javax.persistence.*;

@Entity
@Table(name = "eventgenre")
public class EventGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EEventGenre eventGenre;

    private String eventGenreImage;

    public EventGenre() {
    }

    public EventGenre(EEventGenre eventGenre) {
        this.eventGenre = eventGenre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EEventGenre getEventGenre() {
        return eventGenre;
    }

    public void setEventGenre(EEventGenre eventGenre) {
        this.eventGenre = eventGenre;
    }

    public String getEventGenreImage() {
        return eventGenreImage;
    }

    public void setEventGenreImage(String eventGenreImage) {
        this.eventGenreImage = eventGenreImage;
    }

    @Override
    public String toString() {
        return eventGenre.toString();
    }
}