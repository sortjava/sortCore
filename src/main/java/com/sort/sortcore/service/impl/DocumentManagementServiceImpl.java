package com.sort.sortcore.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.sort.sortcore.config.S3ApplicationProperties;
import com.sort.sortcore.controller.MainBannerController;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentManagementServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(MainBannerController.class);

	@Autowired
	private AmazonS3 amazonS3Client;

	@Autowired
	private S3ApplicationProperties applicationProperties;

	public S3Object downloadFileFromS3bucket(String fileName, String bucketName) {
		S3Object object = amazonS3Client.getObject(bucketName, fileName);
		return object;
	}

	public String movieObjectForPaging(S3Object data, Integer page) {
		try {
			JSONArray movieCollectionsOutput = new JSONArray();

			final String FIRST_NODE_AUD_JSON_GENRES = "aud_json_genres";
			final String FIRST_NODE_GENRES_DETAILS = "genres_details";
			String movieCollections = new String(data.getObjectContent().readAllBytes());
			JSONArray movieCollectionsObject = new JSONArray(movieCollections);
			movieCollectionsObject.forEach(movieCollectionObject -> {
				JSONObject movieListByGenreOutput = new JSONObject();
				JSONObject movieListByGenre = (JSONObject) movieCollectionObject;
				String genre = movieListByGenre.get(FIRST_NODE_AUD_JSON_GENRES).toString();
				String movies = movieListByGenre.get(FIRST_NODE_GENRES_DETAILS).toString();
				JSONArray movieOutput = new JSONArray();
				JSONArray movieObject = new JSONArray(movies);
				movieObject.toList().stream().skip(page*5).limit(5).forEach(movie -> {
					movieOutput.put(movie);
				});
				movieListByGenreOutput.put(FIRST_NODE_AUD_JSON_GENRES, genre);
				movieListByGenreOutput.put(FIRST_NODE_GENRES_DETAILS, movieOutput);
				movieCollectionsOutput.put(movieListByGenreOutput);
			});
			return movieCollectionsOutput.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}
}