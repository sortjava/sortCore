package com.sort.sortcore.repository;

import com.sort.sortcore.data.TrueFan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrueFanRepository extends CrudRepository<TrueFan, Long> {
    TrueFan findByUserIdAndItemTypeAndItemId(Long userID, String itemType, String itemId);

    Boolean existsByUserIdAndItemTypeAndItemId(Long userID, String itemType, String itemId);

    List<TrueFan> findAllByUserIdAndItemType(Long userID, String itemType);
} 