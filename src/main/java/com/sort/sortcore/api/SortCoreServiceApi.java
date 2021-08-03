package com.sort.sortcore.api;

import java.util.List;
import java.util.Map;

import com.sort.sortcore.data.TxnContent;

import lombok.NonNull;

public interface SortCoreServiceApi {
	List<TxnContent> queryTnxContent(@NonNull Map<String, Object> fields);
}