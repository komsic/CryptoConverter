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
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.helper.JSONSerializer;
import com.example.komsic.cryptoconverter.data.service.model.Currency;
import com.example.komsic.cryptoconverter.data.service.model.ItemResponse;
import com.example.komsic.cryptoconverter.helper.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyConversionAdapter extends RecyclerView.Adapter<CurrencyConversionAdapter
        .CardViewHolder> {

    public interface OnItemClicked {
        void onItemClicked(CurrencyCard card);
    }

    private ArrayList<Currency> mList = new ArrayList<>();
    private List<CurrencyCard> mCurrenciesList;
    private Context mContext;
    private ItemResponse mItemResponse;
    private JSONSerializer mSerializer;
    private OnItemClicked mClicked;

    public CurrencyConversionAdapter(Context context) {
        mContext = context;
        mClicked = (OnItemClicked) context;
        mItemResponse = new ItemResponse();
        mSerializer = new JSONSerializer("CurrencyList.json", mContext);
        try {
            mList = mSerializer.load();

            if (mList.size() > 0) {
                // TODO 2nd edit
                for (int i = 0; i < mList.size(); i++) {
                    Currency c = mList.get(i);
                    mItemResponse.getBTC().initialize(c.getCType(), c.getCurrencyToBTCRate());
                    mItemResponse.getETH().initialize(c.getCType(), c.getCurrencyToETHRate());
                    mItemResponse.getBTC().initialize(Currency.CurrencyType.ETH, c.getETHToBTCRate());
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

    public void setCurrenciesList(List<CurrencyCard> cards) {
        mCurrenciesList = cards;
        notifyDataSetChanged();
    }

    public ItemResponse getItemResponse() {
        return mItemResponse;
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
                currencyCard.selectedStatus = !currencyCard.selectedStatus;
                mClicked.onItemClicked(currencyCard);
            }
        });

//        if (currency.isCardAvailableForConversion()) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, CurrencyConverterActivity.class);
//                    intent.putExtra("currencyName", currencyName);
//                    mContext.startActivity(intent);
//                }
//            });
//        } else {
//            Toast.makeText(mContext, mContext.getString(R.string.enable_conversion), Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public int getItemCount() {
        return mCurrenciesList == null ? 0 : mCurrenciesList.size();
    }

    public void addCurrencyCard(Currency currency) {
        boolean cardAlreadyExist = false;

        for (int i = 0; i < mList.size(); i++) {
            if (currency.getCType() == mList.get(i).getCType()) {
                cardAlreadyExist = true;
                break;
            }
        }

        if (cardAlreadyExist != false) {
            Toast.makeText(mContext, mContext.getString(R.string.card_exist), Toast.LENGTH_SHORT).show();

        } else {
            mList.add(currency);
            notifyItemInserted(mList.size() - 1);
        }
    }

    private void removeCurrencyCard(int position) throws IndexOutOfBoundsException {
        try {
            mList.remove(position);
            notifyItemRemoved(position);
        } catch (Exception e) {
            Log.e("List is Empty: ", "", e);
        }
    }

    public void removeAllCurrencyCards() throws IndexOutOfBoundsException {
        try {
            mList.clear();
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("List is Empty: ", "", e);
        }
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView btcCurrentCurrencyNameRate;
        TextView ethCurrentCurrencyNameRate;
        ImageView logoImage;

        CardViewHolder(View itemView) {
            super(itemView);

            btcCurrentCurrencyNameRate = (TextView) itemView.findViewById(R.id
                    .btc_to_current_currency_rate_tv);
            ethCurrentCurrencyNameRate = (TextView) itemView.findViewById(R.id
                    .eth_to_current_currency_rate_tv);
            logoImage = (ImageView) itemView.findViewById(R.id.logo);
        }
    }
}
