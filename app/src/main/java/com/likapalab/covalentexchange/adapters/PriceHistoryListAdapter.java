/*
 * Created by Mehmet Ozdemir on 29.08.2020 15:52
 * Copyright (c) 2020 . All rights reserved.
 *  Last modified 28.08.2020 18:36
 */

package com.likapalab.covalentexchange.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.likapalab.covalentexchange.R;
import com.likapalab.covalentexchange.api.portfolio.HoldingsItem;

import java.util.List;

public class PriceHistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Class Variables
    private List<HoldingsItem> priceHistoryList;

    public PriceHistoryListAdapter(List<HoldingsItem> priceHistoryList) {
        this.priceHistoryList = priceHistoryList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template_history, parent, false);
        return new PriceHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PriceHistoryViewHolder priceHistoryViewHolder = (PriceHistoryViewHolder) holder;

        HoldingsItem pricesItem = priceHistoryList.get(position);
        if (pricesItem != null) {
            priceHistoryViewHolder.dateTextView.setText(pricesItem.getTimestamp().substring(0, 10));
            priceHistoryViewHolder.valueTextView.setText("$ " + pricesItem.getClose().getQuote());
        }
    }

    @Override
    public int getItemCount() {
        return priceHistoryList.size();
    }

    public class PriceHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView valueTextView;

        public PriceHistoryViewHolder(View view) {
            super(view);
            dateTextView = view.findViewById(R.id.text_view_date);
            valueTextView = view.findViewById(R.id.text_view_value);
        }
    }

    public void notifyDataSetChanged(List<HoldingsItem> priceHistoryList) {
        this.priceHistoryList = priceHistoryList;
        notifyDataSetChanged();
    }

    public void removeItem(HoldingsItem pricesItem) {
        priceHistoryList.remove(pricesItem);
    }

    public List<HoldingsItem> getPriceHistoryList() {
        return priceHistoryList;
    }
}
