package com.example.komsic.cryptoconverter.data.service;

import androidx.annotation.NonNull;
import android.util.Log;

import com.example.komsic.cryptoconverter.data.service.model.CurrencyRate;
import com.example.komsic.cryptoconverter.data.service.model.ItemResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRemote {
    private static final String TAG = "CurrencyRemote";

    private RestApiService mApiService;
    private ItemResponse mItemResponse;
    private OnDataLoaded mDataLoaded;

    @Inject
    public CurrencyRemote(RestApiService restApiService) {
        mApiService = restApiService;
    }

    public void setDataLoaded(OnDataLoaded dataLoaded) {
        mDataLoaded = dataLoaded;
    }

    public void fetchData() {
//        RestApiService mApiService = RestApiClient.getClient().create(RestApiService.class);

        Call<ItemResponse> itemResponseCall = mApiService.getItemResponse();
        itemResponseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemResponse> call,
                                   @NonNull Response<ItemResponse> response) {
                if (response.isSuccessful()) {
                    mItemResponse = response.body();
                    mDataLoaded.onDataLoadedSuccess(mItemResponse);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: fail to load data \n" + t.getMessage());
                mDataLoaded.onDataLoadedFailure(false);
            }
        });
    }

    public ItemResponse fetchFakeData() {

        CurrencyRate btc = new CurrencyRate(1, 2, 3, 4,
                5, 6, 7, 8, 9, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19,
                20, 21);

        CurrencyRate eth = new CurrencyRate(11, 12, 13, 14,
                15, 16, 17, 18, 19, 110, 111, 112,
                113, 114, 115, 116, 117, 118, 119,
                120, 121);

        return new ItemResponse(btc, eth);
    }

    public interface OnDataLoaded {
        void onDataLoadedSuccess(ItemResponse itemResponse);

        void onDataLoadedFailure(boolean status);
    }
}
