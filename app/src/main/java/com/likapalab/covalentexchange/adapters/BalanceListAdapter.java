/*
 * Created by Mehmet Ozdemir on 29.08.2020 15:52
 * Copyright (c) 2020 . All rights reserved.
 *  Last modified 28.08.2020 18:36
 */

package com.likapalab.covalentexchange.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.likapalab.covalentexchange.Constants;
import com.likapalab.covalentexchange.R;
import com.likapalab.covalentexchange.api.portfolio.PortfolioItem;
import com.likapalab.covalentexchange.api.pricing.tickers.TickersItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BalanceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Class Variables
    private List<PortfolioItem> dataSet;
    private List<PortfolioItem> balanceList;
    private View noStockView;

    public BalanceListAdapter(List<PortfolioItem> balanceList, View noStockView) {
        this.dataSet = balanceList;
        this.balanceList = new ArrayList<>();
        this.balanceList.addAll(balanceList);
        this.noStockView = noStockView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template_balance, parent, false);
        return new BalanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BalanceViewHolder balanceViewHolder = (BalanceViewHolder) holder;

        PortfolioItem portfolioItem = balanceList.get(position);
        if (portfolioItem != null) {
            balanceViewHolder.codeTextView.setText(portfolioItem.getContractTickerSymbol());
            balanceViewHolder.nameTextView.setText(portfolioItem.getContractName());
            balanceViewHolder.rankTextView.setText(String.valueOf(portfolioItem.getRank()));
            Picasso.get().load(portfolioItem.getLogoUrl()).into(balanceViewHolder.iconImageView);

            balanceViewHolder.itemView.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.INTENT_PARAMETER_BALANCE, portfolioItem);
                Navigation.findNavController(view).navigate(R.id.action_navigation_coin_list_to_navigation_price_history, bundle);
            });
        }
    }

    @Override
    public int getItemCount() {
        return balanceList.size();
    }

    public class BalanceViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView codeTextView;
        TextView nameTextView;
        TextView rankTextView;

        public BalanceViewHolder(View view) {
            super(view);
            iconImageView = view.findViewById(R.id.image_view_icon);
            codeTextView = view.findViewById(R.id.text_view_code);
            nameTextView = view.findViewById(R.id.text_view_name);
            rankTextView = view.findViewById(R.id.text_view_rank);
        }
    }

    public void filter(String searchText) {
        searchText = searchText.toUpperCase(Locale.US);
        balanceList.clear();
        if (searchText.length() == 0) {
            balanceList.addAll(dataSet);
        } else {
            for (PortfolioItem balance : dataSet) {
                if (balance.getContractTickerSymbol().contains(searchText) || balance.getContractName().contains(searchText)) {
                    balanceList.add(balance);
                }
            }
        }
        notifyDataSetChanged();
        if (balanceList.size() > 0) {
            noStockView.setVisibility(View.GONE);
        } else {
            noStockView.setVisibility(View.VISIBLE);
        }
    }

    public void notifyDataSetChanged(List<PortfolioItem> balanceList) {
        this.dataSet = balanceList;
        this.balanceList = new ArrayList<>();
        this.balanceList.addAll(balanceList);
        notifyDataSetChanged();
        if (balanceList.size() > 0) {
            noStockView.setVisibility(View.GONE);
        } else {
            noStockView.setVisibility(View.VISIBLE);
        }
    }

    public List<PortfolioItem> getBalanceList() {
        return balanceList;
    }

    public void removeFromAll(TickersItem coin) {
        dataSet.remove(coin);
        balanceList.remove(coin);
    }
}
