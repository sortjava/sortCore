package com.sort.sortcore.service;

import com.sort.sortcore.api.MainBannerServiceApi;
import com.sort.sortcore.api.SortCoreServiceApi;
import com.sort.sortcore.data.MainBannerContent;
import com.sort.sortcore.data.OmdbData;
import com.sort.sortcore.data.OmdbRequest;
import com.sort.sortcore.data.TxnContent;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MainBannerCacheService implements MainBannerServiceApi {
    private static final Logger log = LoggerFactory.getLogger(MainBannerCacheService.class);
    private final MeterRegistry meterRegistry;
    private final OmdbDataService omdbDataService;
    private final SortCoreServiceApi sortCoreServiceApi;
    private final CacheManager cacheManager;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final Map<String, Object> QUERY_MAP = Map.of("aud_priority", "1", "aud_active", "1");
    private static final Map<String, Object> MAIN_MOVIE_QUERY_MAP = Map.of("aud_priority", "1", "aud_active", "1", "txn_type", "movie");
    private static final Map<String, Object> MAIN_EVENT_QUERY_MAP = Map.of("aud_priority", "1", "aud_active", "1", "txn_type", "event");
    private static final Map<String, Object> RECOMMENDED_MOVIE_QUERY_MAP = Map.of("aud_priority", "1", "txn_type", "movie");
    private static final Map<String, Object> RECOMMENDED_EVENT_QUERY_MAP = Map.of("txn_genres", "Workshops", "txn_type", "event");
    private static final String CACHE_NAME = "main-banner";
    private static final String CACHE_KEY = "DATA";
    private final Counter cacheRefreshCounter;
    private final Counter cacheHitCounter;
    private final Counter cacheMissCounter;
    @Value("${sort.main-banner.cache.enabled}")
    private boolean cacheEnabled;

    @Autowired
    public MainBannerCacheService(MeterRegistry meterRegistry, OmdbDataService omdbDataService,
                                  SortCoreServiceApi sortCoreServiceApi, CacheManager cacheManager) {
        this.meterRegistry = meterRegistry;
        this.omdbDataService = omdbDataService;
        this.sortCoreServiceApi = sortCoreServiceApi;
        this.cacheManager = cacheManager;
        this.cacheRefreshCounter = meterRegistry.counter("sort.main-banner.cache.refresh", new String[0]);
        this.cacheHitCounter = meterRegistry.counter("sort.main-banner.cache.hit", new String[0]);
        this.cacheMissCounter = meterRegistry.counter("sort.main-banner.cache.miss", new String[0]);
    }

    @Override
    @Scheduled(fixedRateString = "${sort.main-banner.refresh}")
    public void populateMainBannerCache() {
        if (this.cacheEnabled) {
            Cache cache = this.cacheManager.getCache("main-banner");
            Optional.ofNullable(cache).map(Cache::invalidate).ifPresent((x) -> {
                log.info("Cache main-banner invalidated {}", x);
            });
            log.info("Populating {} cache at={}", "main-banner", SIMPLE_DATE_FORMAT.format(new Date()));
            this.getListMainBannerContent(QUERY_MAP, "").thenApply((data) -> {
                return Optional.ofNullable(cache).map((c) -> {
                    this.cacheRefreshCounter.increment();
                    return c.putIfAbsent("DATA", data);
                });
            });
        } else {
            //log.info("main-banner cache build status={}", this.cacheEnabled);
        }
    }

    @Override
    public CompletableFuture<List<MainBannerContent>> getMainBannerData(String searchText) {
        if (this.cacheEnabled) {
            Optional<List<MainBannerContent>> cachedData = Optional
                    .ofNullable(this.cacheManager.getCache("main-banner")).map((cacheManager) -> {
                        return cacheManager.get("DATA");
                    }).map((value) -> {
                        return (List) value.get();
                    });
            if (cachedData.isPresent()) {
                log.info("{} cache used with key={}", "main-banner", QUERY_MAP.toString());
                this.cacheHitCounter.increment();
                Objects.requireNonNull(cachedData);
                return CompletableFuture.supplyAsync(cachedData::get);
            }
            this.cacheMissCounter.increment();
        }
        return this.getListMainBannerContent(QUERY_MAP, searchText);
    }

    @Override
    public CompletableFuture<List<MainBannerContent>> getMainBannerMovieEventData(String txnType, String searchText) {
        if ("movie".equalsIgnoreCase(txnType)) {
            return this.getListMainBannerContent(MAIN_MOVIE_QUERY_MAP, searchText);
        } else {
            return this.getListMainBannerContent(MAIN_EVENT_QUERY_MAP, searchText);
        }
    }

    @Override
    public CompletableFuture<List<MainBannerContent>> getSearchData(String searchText) {
        return this.getListMainBannerContent(Map.of("txn_title", searchText, "txn_genres", searchText, "txn_cast", searchText, "txn_type", searchText), searchText);
    }

    @Override
    public CompletableFuture<List<MainBannerContent>> getRecommendedBannerMovieEventData(String txnType, String searchText) {
        if ("movie".equalsIgnoreCase(txnType)) {
            return this.getListMainBannerContent(RECOMMENDED_MOVIE_QUERY_MAP, searchText);
        } else {
            return this.getListMainBannerContent(RECOMMENDED_EVENT_QUERY_MAP, searchText);
        }
    }

    @Override
    public CompletableFuture<List<MainBannerContent>> getTxnTypeList(String txnType, String searchText) {
        return this.getListMainBannerContent(Map.of("aud_priority", "1", "aud_active", "1", "txn_type", txnType), searchText);
    }

    @Override
    public CompletableFuture<List<MainBannerContent>> getTxnDetailsFavouritesById(String Id, String searchText) {
        return this.getListMainBannerContent(Map.of("txn_id", Id), searchText);
    }

    @Override
    public List<TxnContent> getTxnDetailsById(String txnType, String Id, String searchText) {
        return this.sortCoreServiceApi.queryTnxContent(Map.of("txn_type", txnType, "txn_id", Id), searchText);
    }

    private CompletableFuture<List<MainBannerContent>> getListMainBannerContent(Map<String, Object> QUERY_TEXT, String searchText) {
        List<TxnContent> txnContents = this.sortCoreServiceApi.queryTnxContent(QUERY_TEXT, searchText);
        List<CompletableFuture<MainBannerContent>> completableFuturesOfMainBannerContent = txnContents.stream()
                .map(this::getMainBannerContent).map((mbData) -> {
                    return this.callOmdbService(mbData).thenApply((omdbData) -> {
                        return mbData;
                    });
                }).collect(Collectors.toList());
        return this.allOf(completableFuturesOfMainBannerContent);
    }

    private CompletableFuture<Optional<OmdbData>> callOmdbService(MainBannerContent mbData) {
        return this.omdbDataService
                .omdbData(OmdbRequest.builder().title(mbData.getTxn_title()).year(mbData.getYear()).build());
    }

    public <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futuresList) {
        CompletableFuture<Void> allFuturesResult = CompletableFuture
                .allOf(futuresList.toArray(new CompletableFuture[0]));
        return allFuturesResult.thenApply((v) -> {
            return (List) futuresList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        });
    }

    private MainBannerContent getMainBannerContent(TxnContent tx) {
        return MainBannerContent.builder().txn_id(tx.getTxnId()).txn_title(tx.getTxnTitle()).txn_type(tx.getTxnType()).txn_banner_image(tx.getTxnBannerImage())
                .txn_vertical_image(tx.getTxnVerticalImage()).build();
    }
}