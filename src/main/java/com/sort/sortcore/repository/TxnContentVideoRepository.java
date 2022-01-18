package com.sort.sortcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sort.sortcore.data.TxnContentVideo;

@Repository
public interface TxnContentVideoRepository extends JpaRepository<TxnContentVideo, Long> {

	List<TxnContentVideo> findByTxnId(String itemId);
}