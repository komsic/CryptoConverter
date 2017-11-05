package com.example.komsic.cryptoconverter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.activity.CurrencyConverterActivity;
import com.example.komsic.cryptoconverter.helper.JSONSerializer;
import com.example.komsic.cryptoconverter.model.Currency;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyConversionAdapter extends RecyclerView.Adapter<CurrencyConversionAdapter
        .CardViewHolder>{

    private ArrayList<Currency> mList = new ArrayList<>();
    private Context mContext;
    private JSONSerializer mSerializer;

    public CurrencyConversionAdapter(Context context) {
        mContext = context;

        mSerializer = new JSONSerializer("CurrencyList.json", mContext);
        try {
            mList = mSerializer.load();
        } catch (Exception e) {
            mList = new ArrayList<>();
            Log.e("Error Loading Currency:", "", e);
        }
    }

    public void saveCurrencyCards() {
        try {
            mSerializer.save(mList);
        } catch (Exception e) {
            Log.e("Error Saving Currency: ", "", e);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,
                parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Currency.CurrencyType.onChangeRatesValue();
        Currency currency = mList.get(position);

        final String currencyName = currency.getCType().name();
        final String btcToCurrentCurrencyRate = String.valueOf(currency.getBTCRate());
        final String ethToCurrentCurrencyRate = String.valueOf(currency.getETHRate());

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

    public void addCurrencyCard(Currency currency){
        mList.add(currency);
        notifyItemInserted(mList.size() - 1);
    }

    public void removeCurrencyCard(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllCurrencyCards(int position) {
        mList.clear();
        notifyDataSetChanged();
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
