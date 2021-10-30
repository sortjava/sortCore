package com.sort.sortcore.api;

import com.sort.sortcore.data.MainBannerContent;
import com.sort.sortcore.data.TxnContent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MainBannerServiceApi {
    CompletableFuture<List<MainBannerContent>> getMainBannerData();

    CompletableFuture<List<MainBannerContent>> getMainBannerMovieEventData(String txnType);

    CompletableFuture<List<MainBannerContent>> getRecommendedBannerMovieEventData(String txnType);

    CompletableFuture<List<MainBannerContent>> getTxnTypeList(String txnType);

    CompletableFuture<List<MainBannerContent>> getTxnDetailsFavouritesById(String Id);

    List<TxnContent> getTxnDetailsById(String txnType, String id);

    void populateMainBannerCache();
}