package com.sort.sortcore.api;

import com.sort.sortcore.data.TxnContent;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

public interface SortCoreServiceApi {
    List<TxnContent> queryTnxContent(@NonNull Map<String, Object> fields, String searchText, Integer page);
}