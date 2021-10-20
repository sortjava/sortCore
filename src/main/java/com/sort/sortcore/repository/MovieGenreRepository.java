package com.sort.sortcore.repository;

import com.sort.sortcore.data.EMovieGenre;
import com.sort.sortcore.data.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    Optional<MovieGenre> findByMovieGenre(EMovieGenre eMovieGenre);
}