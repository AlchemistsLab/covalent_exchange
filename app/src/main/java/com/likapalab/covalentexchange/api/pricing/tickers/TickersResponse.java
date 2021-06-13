/*
 * Created by Mehmet Ozdemir on 09.06.2021 18:45
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 18:37
 */

package com.likapalab.covalentexchange.api.pricing.tickers;

import com.google.gson.annotations.SerializedName;

public class TickersResponse {

	@SerializedName("error_message")
	private Object errorMessage;

	@SerializedName("data")
	private TickersData tickersData;

	@SerializedName("error_code")
	private Object errorCode;

	@SerializedName("error")
	private boolean error;

	public Object getErrorMessage(){
		return errorMessage;
	}

	public TickersData getTickersData(){
		return tickersData;
	}

	public Object getErrorCode(){
		return errorCode;
	}

	public boolean isError(){
		return error;
	}
}