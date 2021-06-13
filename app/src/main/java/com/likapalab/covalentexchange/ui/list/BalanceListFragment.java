/*
 * Created by Mehmet Ozdemir on 06.06.2021 17:13
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 03.06.2021 22:29
 */

package com.likapalab.covalentexchange.ui.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.likapalab.covalentexchange.R;
import com.likapalab.covalentexchange.adapters.BalanceListAdapter;

import java.util.ArrayList;

public class BalanceListFragment extends Fragment {

    private BalanceListViewModel balanceListViewModel;

    //Widgets
    private View progressView;
    private View progressBarView;
    private RecyclerView balanceListRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        balanceListViewModel =
                ViewModelProviders.of(this).get(BalanceListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_balance_list, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        progressView = view.findViewById(R.id.view_progress);
        progressBarView = view.findViewById(R.id.view_progress_bar);
        TextView enterWalletAddressTextView = view.findViewById(R.id.text_view_enter_wallet_address);
        EditText walletAddressEditText = view.findViewById(R.id.edit_text_wallet_address);

        view.findViewById(R.id.button_go).setOnClickListener(view1 -> {
            hideKeyboard();
            if (walletAddressEditText.getText().toString().equals("")) {
                Toast.makeText(getActivity(), R.string.toast_text_enter_wallet_address, Toast.LENGTH_LONG).show();
            } else {
                if (isValidAddress(walletAddressEditText.getText().toString())) {
                    balanceListViewModel.getPortfolioHistory(walletAddressEditText.getText().toString(), getString(R.string.covalent_services_api_key));
                } else {
                    Toast.makeText(getActivity(), R.string.toast_text_wallet_address_error, Toast.LENGTH_LONG).show();
                }
            }
        });

        balanceListViewModel.getOnProgress().observe(getViewLifecycleOwner(), aBoolean -> onProgress(aBoolean));

        balanceListRecyclerView = view.findViewById(R.id.recycler_view_balance_list);
        BalanceListAdapter balanceListAdapter = new BalanceListAdapter(new ArrayList<>(), enterWalletAddressTextView);
        balanceListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        balanceListRecyclerView.setAdapter(balanceListAdapter);

        balanceListViewModel.getPortfolioResponse().observe(getViewLifecycleOwner(), portfolioResponse -> balanceListAdapter.notifyDataSetChanged(portfolioResponse.getItems()));
    }

    private void onProgress(boolean isVisible) {
        if (isVisible) {
            progressView.setVisibility(View.VISIBLE);
            progressBarView.setVisibility(View.VISIBLE);
        } else {
            progressView.setVisibility(View.GONE);
            progressBarView.setVisibility(View.GONE);
        }
    }

    private boolean isValidAddress(String walletAddress) {
        String regex = "^0x[0-9a-f]{40}$";
        if (walletAddress.toLowerCase().matches(regex)) {
            return true;
        }
        return false;
    }

    public void hideKeyboard() {
        Activity activity = getActivity();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}