/*
 * Created by Mehmet Ozdemir on 06.06.2021 17:13
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 06.06.2021 16:38
 */

package com.likapalab.covalentexchange;

import android.app.Application;

import com.likapalab.covalentexchange.api.services.CovalentService;

public class CovalentApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CovalentService.createInstance(this);
    }
}
