package com.sort.sortcore.data;

import javax.persistence.*;

@Entity
@Table(name = "movielanguage")
public class MovieLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EMovieLanguage movieLanguage;

    public MovieLanguage() {
    }

    public MovieLanguage(EMovieLanguage movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EMovieLanguage getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(EMovieLanguage movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    @Override
    public String toString() {
        return movieLanguage.toString();
    }
}