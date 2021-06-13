package com.likapalab.covalentexchange.api.portfolio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PortfolioItem implements Serializable {

    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("contract_name")
    private String contractName;

    @SerializedName("supports_erc")
    private Object supportsErc;

    @SerializedName("holdings")
    private List<HoldingsItem> holdings;

    @SerializedName("contract_address")
    private String contractAddress;

    @SerializedName("contract_decimals")
    private int contractDecimals;

    @SerializedName("contract_ticker_symbol")
    private String contractTickerSymbol;

    private int rank;

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getContractName() {
        return contractName;
    }

    public Object getSupportsErc() {
        return supportsErc;
    }

    public List<HoldingsItem> getHoldings() {
        return holdings;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}