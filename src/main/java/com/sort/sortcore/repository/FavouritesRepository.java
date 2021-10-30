package com.sort.sortcore.repository;

import com.sort.sortcore.data.Favourites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends CrudRepository<Favourites, Long> {
    Favourites findByUserIdAndItemId(Long userID, String itemId);

    List<Favourites> findAllByUserId(Long userID);
}