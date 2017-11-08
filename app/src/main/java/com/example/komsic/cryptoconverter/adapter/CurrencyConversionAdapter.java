package com.example.komsic.cryptoconverter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.activity.CurrencyConverterActivity;
import com.example.komsic.cryptoconverter.helper.JSONSerializer;
import com.example.komsic.cryptoconverter.model.Currency;
import com.example.komsic.cryptoconverter.model.ItemResponse;

import java.util.ArrayList;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyConversionAdapter extends RecyclerView.Adapter<CurrencyConversionAdapter
        .CardViewHolder>{

    private ArrayList<Currency> mList = new ArrayList<>();
    private Context mContext;
    private ItemResponse mItemResponse;
    private JSONSerializer mSerializer;

    public CurrencyConversionAdapter(Context context) {
        mContext = context;
        mItemResponse = new ItemResponse();
        mSerializer = new JSONSerializer("CurrencyList.json", mContext);
        try {
            mList = mSerializer.load();
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show();
			if(mList.size() > 0){
				// TODO 2nd edit
				for (int i = 0; i < mList.size(); i++){
					Currency c = mList.get(i);
					mItemResponse.getBTC().initialize(c.getCType(), c.getCurrencyToBTCRate());
					mItemResponse.getETH().initialize(c.getCType(), c.getCurrencyToETHRate());
					mItemResponse.getBTC().initialize(c.getCType(), c.getETHToBTCRate());
					notifyDataSetChanged();
				}
			}
        } catch (Exception e) {
            mList = new ArrayList<>();
            Log.e("Error Loading Currency:", "", e);
        }
    }

    public void setItemResponse(ItemResponse itemResponse) {
        mItemResponse = itemResponse;
        notifyDataSetChanged();
    }

    public void saveCurrencyCardsToMemory() {
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
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        Currency currency = mList.get(position);
        currency.onChangeRatesValue(mItemResponse);

        final String currencyName = currency.getCType().name();
        final String btcToCurrentCurrencyRate = String.valueOf(currency.getCurrencyToBTCRate());
        final String ethToCurrentCurrencyRate = String.valueOf(currency.getCurrencyToETHRate());

        holder.btcCurrentCurrencyName.setText(currencyName);
        holder.ethCurrentCurrencyName.setText(currencyName);
        holder.btcCurrentCurrencyNameRate.setText(btcToCurrentCurrencyRate);
        holder.ethCurrentCurrencyNameRate.setText(ethToCurrentCurrencyRate);

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

        holder.deleteCurrencyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCurrencyCard(holder.getAdapterPosition());
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

    private void removeCurrencyCard(int position) throws IndexOutOfBoundsException{
        try {
            mList.remove(position);
            notifyItemRemoved(position);
        } catch (Exception e) {
            Log.e("List is Empty: ", "", e);
        }
    }

    public void removeAllCurrencyCards() throws IndexOutOfBoundsException{
        try {
            mList.clear();
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("List is Empty: ", "", e);
        }
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView btcCurrentCurrencyName;
        TextView ethCurrentCurrencyName;
        TextView btcCurrentCurrencyNameRate;
        TextView ethCurrentCurrencyNameRate;
        ImageView deleteCurrencyCard;

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
            deleteCurrencyCard = (ImageView) itemView.findViewById(R.id.delete_img);
        }
    }
}
