package com.sort.sortcore.api;

import com.sort.sortcore.data.MainBannerContent;
import com.sort.sortcore.data.TxnContent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MainBannerServiceApi {
    CompletableFuture<List<MainBannerContent>> getMainBannerData(String searchText, Integer page);

    CompletableFuture<List<MainBannerContent>> getMainBannerMovieEventData(String txnType, String searchText, Integer page);
    
    CompletableFuture<List<MainBannerContent>> getSearchData(String searchText, String searchType, Integer page);

    CompletableFuture<List<MainBannerContent>> getRecommendedBannerMovieEventData(String txnType, String searchText, Integer page);

    CompletableFuture<List<MainBannerContent>> getTxnTypeList(String txnType, String searchText, Integer page);

    CompletableFuture<List<MainBannerContent>> getTxnDetailsFavouritesById(String Id, String searchText);

    List<TxnContent> getTxnDetailsById(String txnType, String id, String searchText);

    void populateMainBannerCache();
}