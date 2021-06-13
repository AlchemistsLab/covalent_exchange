/*
 * Created by Mehmet Ozdemir on 09.06.2021 18:45
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 18:37
 */

package com.likapalab.covalentexchange.api.pricing.tickers;

import com.google.gson.annotations.SerializedName;

public class TickersPagination {

	@SerializedName("page_number")
	private int pageNumber;

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("has_more")
	private boolean hasMore;

	@SerializedName("page_size")
	private int pageSize;

	public int getPageNumber(){
		return pageNumber;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public boolean isHasMore(){
		return hasMore;
	}

	public int getPageSize(){
		return pageSize;
	}
}