/*
 * Created by Mehmet Ozdemir on 13.06.2021 16:18
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09.06.2021 23:58
 */

package com.likapalab.covalentexchange.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.likapalab.covalentexchange.R;


public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onBackPressed() {
        if (navController.getCurrentDestination().getId() == R.id.navigation_coin_list) {
            super.onBackPressed();
        } else {
            navController.popBackStack();
        }
    }
}