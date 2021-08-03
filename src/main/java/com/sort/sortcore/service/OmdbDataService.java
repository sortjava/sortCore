package com.sort.sortcore.service;

import java.lang.invoke.SerializedLambda;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sort.sortcore.data.OmdbData;
import com.sort.sortcore.data.OmdbRequest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.NonNull;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

@Service
public class OmdbDataService {
	private static final Logger log = LoggerFactory.getLogger(OmdbDataService.class);
	@Value("${sort.omdbapi.apikey}")
	private String apikey;
	@Value("${sort.omdbapi.url}")
	private String url;
	@Autowired
	@Qualifier("omdbDataServicePool")
	private ExecutorService executorService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private OkHttpClient okHttpClient;
	@Autowired
	private MeterRegistry meterRegistry;
	private Counter omdbServiceTotalCounter;
	private Counter omdbServiceMissingDataCounter;

	@PostConstruct
	private void initMetrics() {
		if (this.meterRegistry != null) {
			this.omdbServiceTotalCounter = this.meterRegistry.counter("sort.omdb.total", new String[0]);
			this.omdbServiceMissingDataCounter = this.meterRegistry.counter("sort.omdb.data-missing", new String[0]);
		}

	}

	public CompletableFuture<Optional<OmdbData>> omdbData(@NonNull OmdbRequest omdbRequest) {
		if (omdbRequest == null) {
			throw new NullPointerException("omdbRequest is marked non-null but is null");
		} else {
			return CompletableFuture.supplyAsync(() -> {
				return this.getUri(omdbRequest);
			}, this.executorService).thenApply(this::getOmdbData).thenApply((tuple) -> {
				OmdbRequest request = tuple._1;
				return ((Try) tuple._2).onSuccess((omdbData) -> {
					//log.info("OmdbDataService url={} and data={}", request.getUri(), omdbData);
				}).onFailure((ex) -> {
					this.omdbServiceMissingDataCounter.increment();
					/*log.error("OmdbDataService url={} and exception={}", request.getUri(),
							((Throwable) ex).getLocalizedMessage());*/
				}).toJavaOptional();
			});
		}
	}

	private Tuple2<OmdbRequest, Try<OmdbData>> getOmdbData(@NonNull OmdbRequest omdbRequest) {
		if (omdbRequest == null) {
			throw new NullPointerException("omdbRequest is marked non-null but is null");
		} else {
			Request request = (new Builder()).url(omdbRequest.getUri()).build();
			Call call = this.okHttpClient.newCall(request);
			Try<OmdbData> omdbData = Try.of(() -> {
				this.omdbServiceTotalCounter.increment();
				Response response = call.execute();
				return this.objectMapper.readValue(Objects.requireNonNull(response.body()).byteStream(),
						OmdbData.class);
			}).filterTry((t) -> {
				return !"False".equals(t.response);
			}, (value) -> {
				return new RuntimeException("Missing data for title=" + omdbRequest.getTitle());
			});
			return new Tuple2(omdbRequest, omdbData);
		}
	}

	private OmdbRequest getUri(OmdbRequest request) {
		try {
			URIBuilder uriBuilder = new URIBuilder(this.url);
			uriBuilder.addParameter("t", request.getTitle());
			uriBuilder.addParameter("y", request.getYear());
			uriBuilder.addParameter("apikey", this.apikey);
			return request.toBuilder().uri(uriBuilder.build().toASCIIString()).build();
		} catch (URISyntaxException var3) {
			throw new RuntimeException(var3);
		}
	}

	// $FF: synthetic method
	private static Object deserializeLambda(SerializedLambda lambda) {
		String var1 = lambda.getImplMethodName();
		byte var2 = -1;
		switch (var1.hashCode()) {
		case -249406472:
			if (var1.equals("lambda$getOmdbData$11be4cb6$1")) {
				var2 = 0;
			}
			break;
		case 483113398:
			if (var1.equals("lambda$getOmdbData$68a2a2ff$1")) {
				var2 = 1;
			}
		}

		switch (var2) {
		case 0:
			if (lambda.getImplMethodKind() == 6
					&& lambda.getFunctionalInterfaceClass().equals("io/vavr/CheckedFunction1")
					&& lambda.getFunctionalInterfaceMethodName().equals("apply")
					&& lambda.getFunctionalInterfaceMethodSignature().equals("(java/lang/Object;)java/lang/Object;")
					&& lambda.getImplClass().equals("com/sort/elasticsearch/service/OmdbDataService")
					&& lambda.getImplMethodSignature().equals(
							"(com/sort/sortcore/data/OmdbRequest;com/sort/sortcore/data/OmdbData;)java/lang/Throwable;")) {
				/*
				 * return (value) -> { return new RuntimeException("Missing data for title=" +
				 * omdbRequest.getTitle()); };
				 */
			}
			break;
		case 1:
			if (lambda.getImplMethodKind() == 7
					&& lambda.getFunctionalInterfaceClass().equals("io/vavr/CheckedFunction0")
					&& lambda.getFunctionalInterfaceMethodName().equals("apply")
					&& lambda.getFunctionalInterfaceMethodSignature().equals("()java/lang/Object;")
					&& lambda.getImplClass().equals("com/sort/elasticsearch/service/OmdbDataService")
					&& lambda.getImplMethodSignature().equals("(okhttp3/Call;)com/sort/sortcore/data/OmdbData;")) {
				OmdbDataService var10000 = (OmdbDataService) lambda.getCapturedArg(0);
				/*
				 * return () -> { this.omdbServiceTotalCounter.increment(); Response response =
				 * call.execute(); return (OmdbData) this.objectMapper.readValue(
				 * ((ResponseBody) Objects.requireNonNull(response.body())).byteStream(),
				 * OmdbData.class); };
				 */
			}
		}

		throw new IllegalArgumentException("Invalid lambda deserialization");
	}
}