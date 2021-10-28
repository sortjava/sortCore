package com.sort.sortcore.repository;

import com.sort.sortcore.data.EMovieLanguage;
import com.sort.sortcore.data.MovieLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieLanguageRepository extends JpaRepository<MovieLanguage, Long> {
    Optional<MovieLanguage> findByMovieLanguage(EMovieLanguage eMovieLanguage);

    @Query(value = "SELECT * FROM movielanguage m1 INNER JOIN users_movielanguage m2 ON m1.id = m2.movielanguage_id WHERE m2.user_id = ?1", nativeQuery = true)
    Set<MovieLanguage> findAllByUserId(Long userId);
}