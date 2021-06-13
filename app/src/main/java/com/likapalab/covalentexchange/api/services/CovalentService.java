/*
 * Created by Mehmet Ozdemir on 09.06.2021 18:45
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 18:39
 */

package com.likapalab.covalentexchange.api.services;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import com.likapalab.covalentexchange.R;
import com.likapalab.covalentexchange.models.interfaces.ICovalentServices;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovalentService {

    private static CovalentService instance;

    private static ICovalentServices covalentServices;

    public static void createInstance(Context context) {
        if (instance == null) {
            instance = new CovalentService(context);
        }
    }

    private CovalentService(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();
        Retrofit covalentServicesRetrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.covalent_services_base_url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        covalentServices = covalentServicesRetrofit.create(ICovalentServices.class);
    }

    public static ICovalentServices getCovalentServices() {
        return covalentServices;
    }
}