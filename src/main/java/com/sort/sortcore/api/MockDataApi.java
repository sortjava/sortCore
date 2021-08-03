package com.sort.sortcore.api;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.sort.sortcore.data.MockData;

public interface MockDataApi {
	CompletableFuture<MockData> findById(UUID id);

	CompletableFuture<List<MockData>> findAllById(UUID id);
}