package com.sort.sortcore.service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sort.sortcore.api.MockDataApi;
import com.sort.sortcore.data.MockData;

@Service
public class MockDataService implements MockDataApi {
	private static final Logger log = LoggerFactory.getLogger(MockDataService.class);

	@Override
	public CompletableFuture<MockData> findById(UUID id) {
		return CompletableFuture.supplyAsync(() -> {
			return MockData.builder().id(id).createTime(Instant.now()).build();
		});
	}

	@Override
	public CompletableFuture<List<MockData>> findAllById(UUID id) {
		return CompletableFuture.supplyAsync(() -> {
			return Collections.singletonList(MockData.builder().id(id).createTime(Instant.now()).build());
		});
	}
}