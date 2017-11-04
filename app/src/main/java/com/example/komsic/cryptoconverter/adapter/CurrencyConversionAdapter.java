package com.example.komsic.cryptoconverter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.model.Currency;

import java.util.ArrayList;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyConversionAdapter extends RecyclerView.Adapter<CurrencyConversionAdapter
        .CardViewHolder>{

    private ArrayList<Currency> mList = new ArrayList<>();
    private Context mContext;

    public CurrencyConversionAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,
                parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Currency currency = mList.get(position);

        final String currencyName = currency.getCType().toString();
        final String btcToCurrentCurrencyRate = String.valueOf(currency.getBTCRate());
        final String ethToCurrentCurrencyRate = String.valueOf(currency.getETHRate());

        holder.btcCurrentCurrencyTV.setText(currencyName);
        holder.ethCurrentCurrencyTV.setText(currencyName);
        holder.btcCurrentCurrencyRateTV.setText(btcToCurrentCurrencyRate);
        holder.ethCurrentCurrencyRateTV.setText(ethToCurrentCurrencyRate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CurrencyConversionAdapter.class);
                intent.putExtra("currencyName", currencyName);
                intent.putExtra("btcToCurrentCurrencyRate", btcToCurrentCurrencyRate);
                intent.putExtra("ethToCurrentCurrencyRate", ethToCurrentCurrencyRate);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItem(Currency currency){
        mList.add(currency);
        notifyItemInserted(mList.size() - 1);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView btcCurrentCurrencyTV;
        TextView ethCurrentCurrencyTV;
        TextView btcCurrentCurrencyRateTV;
        TextView ethCurrentCurrencyRateTV;

        public CardViewHolder(View itemView) {
            super(itemView);
            btcCurrentCurrencyTV = (TextView) itemView.findViewById(R.id.btc_currency_result_tv);
            ethCurrentCurrencyTV = (TextView) itemView.findViewById(R.id.eth_currency_result_tv);
            btcCurrentCurrencyRateTV = (TextView) itemView.findViewById(R.id
                    .btc_to_current_currency_rate_tv);
            ethCurrentCurrencyRateTV = (TextView) itemView.findViewById(R.id
                    .eth_to_current_currency_rate_tv);
        }
    }
}
