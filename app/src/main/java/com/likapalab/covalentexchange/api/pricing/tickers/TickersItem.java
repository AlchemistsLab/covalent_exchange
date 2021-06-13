/*
 * Created by Mehmet Ozdemir on 09.06.2021 18:45
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 18:37
 */

package com.likapalab.covalentexchange.api.pricing.tickers;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TickersItem implements Serializable {

    @SerializedName("quote_rate")
    private Object quoteRate;

    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("rank")
    private int rank;

    @SerializedName("contract_name")
    private String contractName;

    @SerializedName("supports_erc")
    private Object supportsErc;

    @SerializedName("contract_address")
    private String contractAddress;

    @SerializedName("contract_decimals")
    private int contractDecimals;

    @SerializedName("contract_ticker_symbol")
    private String contractTickerSymbol;

    public Object getQuoteRate() {
        return quoteRate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public int getRank() {
        return rank;
    }

    public String getContractName() {
        return contractName;
    }

    public Object getSupportsErc() {
        return supportsErc;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public int getContractDecimals() {
        return contractDecimals;
    }

    public String getContractTickerSymbol() {
        return contractTickerSymbol;
    }
}