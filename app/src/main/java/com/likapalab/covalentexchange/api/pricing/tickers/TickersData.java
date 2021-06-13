/*
 * Created by Mehmet Ozdemir on 09.06.2021 18:45
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 18:37
 */

package com.likapalab.covalentexchange.api.pricing.tickers;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TickersData {

	@SerializedName("pagination")
	private TickersPagination tickersPagination;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("items")
	private List<TickersItem> items;

	public TickersPagination getTickersPagination(){
		return tickersPagination;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public List<TickersItem> getItems(){
		return items;
	}
}