package com.sort.sortcore.repository;

import com.sort.sortcore.data.SortedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortDataRepository extends CrudRepository<SortedData, Long> {
}
