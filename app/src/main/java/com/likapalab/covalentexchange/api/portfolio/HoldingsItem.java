package com.likapalab.covalentexchange.api.portfolio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HoldingsItem implements Serializable {

	@SerializedName("high")
	private High high;

	@SerializedName("quote_rate")
	private double quoteRate;

	@SerializedName("low")
	private Low low;

	@SerializedName("close")
	private Close close;

	@SerializedName("open")
	private Open open;

	@SerializedName("timestamp")
	private String timestamp;

	public High getHigh(){
		return high;
	}

	public double getQuoteRate(){
		return quoteRate;
	}

	public Low getLow(){
		return low;
	}

	public Close getClose(){
		return close;
	}

	public Open getOpen(){
		return open;
	}

	public String getTimestamp(){
		return timestamp;
	}
}