/*
 * Created by Mehmet Ozdemir on 06.06.2021 17:13
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 03.06.2021 22:29
 */

package com.likapalab.covalentexchange.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.likapalab.covalentexchange.Constants;
import com.likapalab.covalentexchange.R;
import com.likapalab.covalentexchange.activities.ChartActivity;
import com.likapalab.covalentexchange.adapters.PriceHistoryListAdapter;
import com.likapalab.covalentexchange.api.portfolio.PortfolioItem;
import com.squareup.picasso.Picasso;

public class PriceHistoryFragment extends Fragment {

    //Class Variables
    private PortfolioItem balance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        balance = (PortfolioItem) getArguments().getSerializable(Constants.INTENT_PARAMETER_BALANCE);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_price_history, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        view.findViewById(R.id.image_view_back).setOnClickListener(view1 -> Navigation.findNavController(view).popBackStack());

        view.findViewById(R.id.button_view_on_chart).setOnClickListener(view12 -> {
            Intent chartIntent = new Intent(getActivity(), ChartActivity.class);
            chartIntent.putExtra(Constants.INTENT_PARAMETER_BALANCE, balance);
            startActivity(chartIntent);
        });

        initCoin(view);
    }

    private void initCoin(View view) {
        TextView codeTextView = view.findViewById(R.id.text_view_code);
        TextView nameTextView = view.findViewById(R.id.text_view_name);
        TextView rankTextView = view.findViewById(R.id.text_view_rank);
        ImageView iconImageView = view.findViewById(R.id.image_view_icon);

        codeTextView.setText(balance.getContractTickerSymbol());
        nameTextView.setText(balance.getContractName());
        rankTextView.setText(String.valueOf(balance.getRank()));

        Picasso.get().load(balance.getLogoUrl()).into(iconImageView);

        RecyclerView priceHistoryRecyclerView = view.findViewById(R.id.recycler_view_history);
        PriceHistoryListAdapter priceHistoryListAdapter = new PriceHistoryListAdapter(balance.getHoldings());
        priceHistoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        priceHistoryRecyclerView.setAdapter(priceHistoryListAdapter);
    }
}