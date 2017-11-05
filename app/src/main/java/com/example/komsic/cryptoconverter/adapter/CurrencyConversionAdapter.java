package com.example.komsic.cryptoconverter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.activity.CurrencyConverterActivity;
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

        final String currencyName = currency.getCType().name();
        final String btcToCurrentCurrencyRate = String.valueOf(currency.getBTCRate());
        final String ethToCurrentCurrencyRate = String.valueOf(currency.getETHRate());



//        if (holder.btcCurrentCurrencyTV != null) {
//            holder.btcCurrentCurrencyTV.setText(currencyName);
//        }
//        if (holder.ethCurrentCurrencyTV != null) {
//            holder.ethCurrentCurrencyTV.setText(currencyName);
//        }
//        if (holder.ethCurrentCurrencyRateTV != null) {
//            holder.ethCurrentCurrencyRateTV.setText(ethToCurrentCurrencyRate);
//        }
//        if (holder.btcCurrentCurrencyRateTV != null) {
//            holder.btcCurrentCurrencyRateTV.setText(ethToCurrentCurrencyRate);
//        }
        holder.btcCurrentCurrencyName.setText(currencyName);
        holder.ethCurrentCurrencyName.setText(currencyName);
        holder.btcCurrentCurrencyNameRate.setText(btcToCurrentCurrencyRate);
        holder.ethCurrentCurrencyNameRate.setText(ethToCurrentCurrencyRate);
        Toast.makeText(mContext, currencyName + " " + btcToCurrentCurrencyRate + " "
                + ethToCurrentCurrencyRate, Toast.LENGTH_SHORT).show();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CurrencyConverterActivity.class);
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

        TextView btcCurrentCurrencyName;
        TextView ethCurrentCurrencyName;
        TextView btcCurrentCurrencyNameRate;
        TextView ethCurrentCurrencyNameRate;

        public CardViewHolder(View itemView) {
            super(itemView);
            btcCurrentCurrencyName = (TextView) itemView.findViewById(R.id
                    .btc_to_current_currency_tv);
            ethCurrentCurrencyName = (TextView) itemView.findViewById(R.id
                    .eth_to_current_currency_tv);
            btcCurrentCurrencyNameRate = (TextView) itemView.findViewById(R.id
                    .btc_to_current_currency_rate_tv);
            ethCurrentCurrencyNameRate = (TextView) itemView.findViewById(R.id
                    .eth_to_current_currency_rate_tv);
        }
    }
}
