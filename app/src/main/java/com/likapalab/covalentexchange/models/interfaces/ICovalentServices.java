/*
 * Created by Mehmet Ozdemir on 09.06.2021 18:45
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 18:45
 */

package com.likapalab.covalentexchange.models.interfaces;

import com.likapalab.covalentexchange.api.portfolio.PortfolioResponse;
import com.likapalab.covalentexchange.api.pricing.tickers.TickersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICovalentServices {

    @GET("/v1/pricing/tickers/")
    Call<TickersResponse> getTickersApiRequest(@Query("tickers") String coins, @Query("key") String apiKey);

    @GET("/v1/{chain_id}/address/{address}/portfolio_v2/")
    Call<PortfolioResponse> getPortfolioHistoryApiRequest(@Path("chain_id") String chainId, @Path("address") String address, @Query("key") String apiKey);

}
