package com.sort.sortcore.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.sort.sortcore.data.MainBannerContent;
import com.sort.sortcore.data.TxnContent;

public interface MainBannerServiceApi {
	CompletableFuture<List<MainBannerContent>> getMainBannerData();
	CompletableFuture<List<MainBannerContent>> getTxnTypeList(String txnType);
	List<TxnContent> getTxnDetailsById(String txnType, String id);
	void populateMainBannerCache();
}