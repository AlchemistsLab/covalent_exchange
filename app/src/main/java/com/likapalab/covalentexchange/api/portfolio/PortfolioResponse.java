package com.likapalab.covalentexchange.api.portfolio;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PortfolioResponse {

	@SerializedName("address")
	private String address;

	@SerializedName("pagination")
	private Object pagination;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("chain_id")
	private int chainId;

	@SerializedName("next_update_at")
	private String nextUpdateAt;

	@SerializedName("items")
	private List<PortfolioItem> items;

	@SerializedName("quote_currency")
	private String quoteCurrency;

	public String getAddress(){
		return address;
	}

	public Object getPagination(){
		return pagination;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getChainId(){
		return chainId;
	}

	public String getNextUpdateAt(){
		return nextUpdateAt;
	}

	public List<PortfolioItem> getItems(){
		return items;
	}

	public String getQuoteCurrency(){
		return quoteCurrency;
	}
}