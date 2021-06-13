/*
 * Created by Mehmet Ozdemir on 06.06.2021 17:13
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 03.06.2021 23:41
 */

package com.likapalab.covalentexchange.ui.list;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.likapalab.covalentexchange.api.portfolio.PortfolioResponse;
import com.likapalab.covalentexchange.api.pricing.tickers.TickersItem;
import com.likapalab.covalentexchange.api.pricing.tickers.TickersResponse;
import com.likapalab.covalentexchange.api.services.CovalentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceListViewModel extends ViewModel {

    private static final String TAG = BalanceListViewModel.class.getName();

    private MutableLiveData<PortfolioResponse> portfolioResponse;
    private MutableLiveData<Boolean> onProgress;

    public BalanceListViewModel() {
        onProgress = new MutableLiveData<>(false);
        portfolioResponse = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getOnProgress() {
        return onProgress;
    }

    public void getPortfolioHistory(String walletAddress, String apiKey) {
        onProgress.postValue(true);
        CovalentService.getCovalentServices().getPortfolioHistoryApiRequest("1", walletAddress, apiKey).enqueue(new Callback<PortfolioResponse>() {
            @Override
            public void onResponse(Call<PortfolioResponse> call, Response<PortfolioResponse> response) {
                onProgress.postValue(false);
                prepareCoinListRequest(response.body(), apiKey);
            }

            @Override
            public void onFailure(Call<PortfolioResponse> call, Throwable t) {
                onProgress.postValue(false);
            }
        });
    }

    private void prepareCoinListRequest(PortfolioResponse portfolioResponse, String apiKey) {
        if (portfolioResponse.getItems().size() > 0) {
            StringBuilder portfolioCoins = new StringBuilder(portfolioResponse.getItems().get(0).getContractTickerSymbol());
            for (int i = 1; i < portfolioResponse.getItems().size(); i++) {
                portfolioCoins.append(",").append(portfolioResponse.getItems().get(i).getContractTickerSymbol());
            }
            getCoinList(portfolioResponse, portfolioCoins.toString(), apiKey);
        } else {
            Log.d(TAG, "No balance");
        }
    }

    private void getCoinList(PortfolioResponse tempPortfolioResponse, String coins, String apiKey) {
        onProgress.postValue(true);
        CovalentService.getCovalentServices().getTickersApiRequest(coins, apiKey).enqueue(new Callback<TickersResponse>() {
            @Override
            public void onResponse(Call<TickersResponse> call, Response<TickersResponse> response) {
                onProgress.postValue(false);
                for (int i = 0; i < tempPortfolioResponse.getItems().size(); i++) {
                    tempPortfolioResponse.getItems().get(i).setRank(response.body().getTickersData().getItems().get(i).getRank());
                }
                portfolioResponse.postValue(tempPortfolioResponse);
            }

            @Override
            public void onFailure(Call<TickersResponse> call, Throwable t) {
                onProgress.postValue(false);
            }
        });
    }

    public MutableLiveData<PortfolioResponse> getPortfolioResponse() {
        return portfolioResponse;
    }
}