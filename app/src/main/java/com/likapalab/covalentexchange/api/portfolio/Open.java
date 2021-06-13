package com.likapalab.covalentexchange.api.portfolio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Open implements Serializable {

	@SerializedName("quote")
	private double quote;

	@SerializedName("balance")
	private String balance;

	public double getQuote(){
		return quote;
	}

	public String getBalance(){
		return balance;
	}
}