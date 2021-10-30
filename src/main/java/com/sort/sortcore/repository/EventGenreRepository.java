package com.sort.sortcore.repository;

import com.sort.sortcore.data.EEventGenre;
import com.sort.sortcore.data.EventGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventGenreRepository extends JpaRepository<EventGenre, Long> {
    Optional<EventGenre> findByEventGenre(EEventGenre eEventGenre);

    @Query(value = "SELECT * FROM eventgenre", nativeQuery = true)
    List<EventGenre> findAllByIdGreaterThan(Long userID);

    @Query(value = "SELECT * FROM eventgenre m1 INNER JOIN users_eventgenres m2 ON m1.id = m2.eventgenres_id WHERE m2.user_id = ?1", nativeQuery = true)
    Set<EventGenre> findAllByUserId(Long userId);
}