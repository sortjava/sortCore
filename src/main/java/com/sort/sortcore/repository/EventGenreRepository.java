package com.sort.sortcore.repository;

import com.sort.sortcore.data.EEventGenre;
import com.sort.sortcore.data.EventGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventGenreRepository extends JpaRepository<EventGenre, Long> {
    Optional<EventGenre> findByEventGenre(EEventGenre eEventGenre);
}