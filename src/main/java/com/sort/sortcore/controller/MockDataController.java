package com.sort.sortcore.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sort.sortcore.api.MockDataApi;
import com.sort.sortcore.data.MockData;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class MockDataController {
	private static final Logger log = LoggerFactory.getLogger(MockDataController.class);
	@Autowired
	MockDataApi mockDataApi;

	@GetMapping({"/"})
	public String getHealth() {
		return "Your Entertainment, SIMPLIFIED !";
	}

	@ApiOperation(value = "Mock Find", hidden = true)
	@RequestMapping(value = { "mock/find/{id}" }, method = { RequestMethod.GET })
	public CompletableFuture<MockData> findById(@PathVariable("id") UUID id) {
		log.info("Find by id invoked for id={}", id);
		return this.mockDataApi.findById(id);
	}

	@ApiOperation(value = "Mock Find All", hidden = true)
	@RequestMapping(value = { "mock/findAll/{id}" }, method = { RequestMethod.GET })
	public CompletableFuture<List<MockData>> findAllById(@PathVariable("id") UUID id) {
		log.info("Find all by id invoked for id={}", id);
		return this.mockDataApi.findAllById(id);
	}
}