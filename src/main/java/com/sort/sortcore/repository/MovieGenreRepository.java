package com.sort.sortcore.repository;

import com.sort.sortcore.data.EMovieGenre;
import com.sort.sortcore.data.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    Optional<MovieGenre> findByMovieGenre(EMovieGenre eMovieGenre);

    @Query(value = "SELECT * FROM moviegenre", nativeQuery = true)
    List<MovieGenre> findAllByIdGreaterThan(Long userID);

    @Query(value = "SELECT * FROM moviegenre m1 INNER JOIN users_moviegenres m2 ON m1.id = m2.moviegenres_id WHERE m2.user_id = ?1", nativeQuery = true)
    Set<MovieGenre> findAllByUserId(Long userId);
}