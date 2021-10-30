package com.sort.sortcore.data;

import javax.persistence.*;

@Entity
@Table(name = "moviegenre")
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EMovieGenre movieGenre;

    private String movieGenreImage;

    public MovieGenre() {
    }

    public MovieGenre(EMovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EMovieGenre getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(EMovieGenre movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieGenreImage() {
        return movieGenreImage;
    }

    public void setMovieGenreImage(String movieGenreImage) {
        this.movieGenreImage = movieGenreImage;
    }

    @Override
    public String toString() {
        return movieGenre.toString();
    }
}