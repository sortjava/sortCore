package com.sort.sortcore.repository;

import com.sort.sortcore.data.EMovieLanguage;
import com.sort.sortcore.data.MovieLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieLanguageRepository extends JpaRepository<MovieLanguage, Long> {
    Optional<MovieLanguage> findByMovieLanguage(EMovieLanguage eMovieLanguage);
}