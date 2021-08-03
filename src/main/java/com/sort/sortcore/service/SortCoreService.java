package com.sort.sortcore.service;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sort.sortcore.api.SortCoreServiceApi;
import com.sort.sortcore.data.TxnContent;

import io.vavr.control.Try;
import lombok.NonNull;

@Service
public class SortCoreService implements SortCoreServiceApi {
	private static final Logger log = LoggerFactory.getLogger(SortCoreService.class);
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("${sort.sortcore.tx.index}")
	private String txIndex;

	@Override
	public List<TxnContent> queryTnxContent(@NonNull Map<String, Object> fields) {
		if (fields == null) {
			throw new NullPointerException("fields is marked non-null but is null");
		} else {
			BoolQueryBuilder boolQuery = new BoolQueryBuilder();
			Iterator var3 = fields.entrySet().iterator();

			while (var3.hasNext()) {
				Entry<String, Object> entry = (Entry) var3.next();
				boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
			}

			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(boolQuery);
			searchSourceBuilder.size(5000);
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.indices(new String[] { this.txIndex });
			searchRequest.source(searchSourceBuilder);
			return Try.of(() -> {
				return this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
			}).map((searchResponse) -> {
				return this.getSearchHits(fields, searchResponse);
			}).onFailure((e) -> {
				log.error("restHighLevelClient error index={}, query={}", new Object[] { this.txIndex, fields, e });
			}).getOrElse(List::of);
		}
	}

	private List<TxnContent> getSearchHits(Map<String, Object> fields, SearchResponse searchResponse) {
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		if (searchHits.length == 0) {
			log.debug("queryTnxContent query={}, result empty", fields);
			return List.of();
		} else {
			log.info("queryTnxContent query={} size={}", fields, searchHits.length);
			return Arrays.stream(searchHits).map((hit) -> {
				String source = hit.getSourceAsString();
				return Try.of(() -> {
					return this.objectMapper.readValue(source, TxnContent.class);
				}).onFailure((e) -> {
					log.error("queryTnxContent data parsing error query={}", fields, e);
				}).toJavaOptional();
			}).flatMap(Optional::stream).collect(Collectors.toList());
		}
	}

	// $FF: synthetic method
	private static Object deserializeLambda(SerializedLambda lambda) {
		String var1 = lambda.getImplMethodName();
		byte var2 = -1;
		switch (var1.hashCode()) {
		case -549284257:
			if (var1.equals("lambda$queryTnxContent$78e8c6d1$1")) {
				var2 = 1;
			}
			break;
		case 609100350:
			if (var1.equals("lambda$getSearchHits$f0e743d7$1")) {
				var2 = 0;
			}
		}

		SortCoreService var10000;
		switch (var2) {
		case 0:
			if (lambda.getImplMethodKind() == 7
					&& lambda.getFunctionalInterfaceClass().equals("io/vavr/CheckedFunction0")
					&& lambda.getFunctionalInterfaceMethodName().equals("apply")
					&& lambda.getFunctionalInterfaceMethodSignature().equals("()java/lang/Object;")
					&& lambda.getImplClass().equals("com/sort/elasticsearch/service/SortCoreService")
					&& lambda.getImplMethodSignature()
							.equals("(java/lang/String;)com/sort/sortcore/data/TxnContent;")) {
				var10000 = (SortCoreService) lambda.getCapturedArg(0);
				/*
				 * return () -> { return (TxnContent) this.objectMapper.readValue(source,
				 * TxnContent.class); };
				 */
			}
			break;
		case 1:
			if (lambda.getImplMethodKind() == 7
					&& lambda.getFunctionalInterfaceClass().equals("io/vavr/CheckedFunction0")
					&& lambda.getFunctionalInterfaceMethodName().equals("apply")
					&& lambda.getFunctionalInterfaceMethodSignature().equals("()java/lang/Object;")
					&& lambda.getImplClass().equals("com/sort/elasticsearch/service/SortCoreService")
					&& lambda.getImplMethodSignature().equals(
							"(org/sortcore/action/search/SearchRequest;)org/sortcore/action/search/SearchResponse;")) {
				var10000 = (SortCoreService) lambda.getCapturedArg(0);
				/*
				 * return () -> { return this.restHighLevelClient.search(searchRequest,
				 * RequestOptions.DEFAULT); };
				 */
			}
		}

		throw new IllegalArgumentException("Invalid lambda deserialization");
	}
}