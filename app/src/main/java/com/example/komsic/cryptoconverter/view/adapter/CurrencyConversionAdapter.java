package com.example.komsic.cryptoconverter.view.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.helper.Util;
import com.example.komsic.cryptoconverter.view.ui.CurrencyConverterActivity;

import java.util.List;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyConversionAdapter extends RecyclerView.Adapter<CurrencyConversionAdapter.CardViewHolder> {

    private List<CurrencyCard> mCurrenciesList;

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {

        final CurrencyCard currencyCard = mCurrenciesList.get(position);


        final String currencyType = currencyCard.currencyType;
        final double btcToCurrentCurrencyRate = (currencyCard.btcRate);
        final double ethToCurrentCurrencyRate = (currencyCard.ethRate);

        holder.btcCurrentCurrencyNameRate.setText(Util.formatMoney(currencyType,
                btcToCurrentCurrencyRate));
        holder.ethCurrentCurrencyNameRate.setText(Util.formatMoney(currencyType,
                ethToCurrentCurrencyRate));

        Context context = holder.btcCurrentCurrencyNameRate.getContext();
        int imageId = context.getResources().getIdentifier(currencyCard.imageAsset, "drawable",
                context.getPackageName());
        holder.logoImage.setImageResource(imageId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), CurrencyConverterActivity.class);
                intent.putExtra("currencyName", currencyType);
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,
                parent, false);
        return new CardViewHolder(itemView);
    }

    public CurrencyCard getCard(int position) {
        return mCurrenciesList.get(position);
    }

    @Override
    public int getItemCount() {
        return mCurrenciesList == null ? 0 : mCurrenciesList.size();
    }

    public void setCurrenciesList(List<CurrencyCard> cards) {
        mCurrenciesList = cards;
        notifyDataSetChanged();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView btcCurrentCurrencyNameRate;
        TextView ethCurrentCurrencyNameRate;
        ImageView logoImage;

        CardViewHolder(View itemView) {
            super(itemView);

            btcCurrentCurrencyNameRate = itemView.findViewById(R.id
                    .btc_to_current_currency_rate_tv);
            ethCurrentCurrencyNameRate = itemView.findViewById(R.id
                    .eth_to_current_currency_rate_tv);
            logoImage = itemView.findViewById(R.id.logo);
        }
    }
}
