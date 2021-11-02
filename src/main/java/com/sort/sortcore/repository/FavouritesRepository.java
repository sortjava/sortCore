package com.sort.sortcore.repository;

import com.sort.sortcore.data.Favourites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends CrudRepository<Favourites, Long> {
    Favourites findByUserIdAndItemTypeAndItemId(Long userID, String itemType, String itemId);

    Boolean existsByUserIdAndItemTypeAndItemId(Long userID, String itemType, String itemId);

    List<Favourites> findAllByUserIdAndItemType(Long userID, String itemType);
}