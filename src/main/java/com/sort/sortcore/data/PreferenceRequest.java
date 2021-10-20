package com.sort.sortcore.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class PreferenceRequest {
    @NotBlank
    @Size(max = 60)
    private String email;
    private String provider;
    private Set<String> movieGenres = new HashSet<>();
    private Set<String> movieLanguages = new HashSet<>();
    private Set<String> eventGenre = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Set<String> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<String> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public Set<String> getMovieLanguages() {
        return movieLanguages;
    }

    public void setMovieLanguages(Set<String> movieLanguages) {
        this.movieLanguages = movieLanguages;
    }

    public Set<String> getEventGenre() {
        return eventGenre;
    }

    public void setEventGenre(Set<String> eventGenre) {
        this.eventGenre = eventGenre;
    }
}