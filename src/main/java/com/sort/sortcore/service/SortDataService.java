package com.sort.sortcore.service;

import com.sort.sortcore.data.SortedData;
import java.util.List;

public interface SortDataService {
    List<SortedData> getSortedData();
    SortedData getSortedDataById(Long id);
    SortedData insert(SortedData sortedData);
    void updateSortedData(Long id, SortedData sortedData);
    void deleteSortedData(Long sortedDataId);
}





