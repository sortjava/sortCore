package com.sort.sortcore.service.impl;

import com.sort.sortcore.data.SortedData;
import com.sort.sortcore.repository.SortDataRepository;
import com.sort.sortcore.service.SortDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortDataServiceImpl implements SortDataService {
    SortDataRepository sortDataRepository;

    public SortDataServiceImpl(SortDataRepository sortDataRepository) {
        this.sortDataRepository = sortDataRepository;
    }

    @Override
    public List<SortedData> getSortedData() {
        List<SortedData> sortedData = new ArrayList<>();
        sortDataRepository.findAll().forEach(sortedData::add);
        return sortedData;
    }

    @Override
    public SortedData getSortedDataById(Long id) {
        return sortDataRepository.findById(id).get();
    }

    @Override
    public SortedData insert(SortedData sortedData) {
        return sortDataRepository.save(sortedData);
    }

    @Override
    public void updateSortedData(Long id, SortedData sortedData) {
        SortedData sortedDataFromDb = sortDataRepository.findById(id).get();
        sortDataRepository.save(sortedDataFromDb);
    }

    @Override
    public void deleteSortedData(Long sortedDataId) {
        sortDataRepository.deleteById(sortedDataId);
    }
}





