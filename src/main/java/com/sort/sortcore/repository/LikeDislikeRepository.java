package com.sort.sortcore.repository;

import com.sort.sortcore.data.LikeDislike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDislikeRepository extends CrudRepository<LikeDislike, Long> {
    LikeDislike findByUserIdAndItemTypeAndItemId(Long userID, String itemType, String itemId);

    Boolean existsByUserIdAndItemTypeAndItemId(Long userID, String itemType, String itemId);

    List<LikeDislike> findAllByUserIdAndItemType(Long userID, String itemType);
}