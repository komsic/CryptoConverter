package com.example.komsic.cryptoconverter.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.komsic.cryptoconverter.data.CurrencyRepository;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;

public class CurrencyDetailViewModel extends AndroidViewModel {

    private CurrencyRepository mRepository;

    public CurrencyDetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = CurrencyRepository.getInstance(application);
    }

    public double[] getConvertedAmount(CurrencyCard card, String selectedCurrencyToBeConverted,
                                       double amountTobeConverted) {
        return mRepository.getConvertedAmount(card, selectedCurrencyToBeConverted,
                amountTobeConverted);
    }

    public LiveData<CurrencyCard> getCurrencyCard(String currencyType) {
        return mRepository.getCurrencyCard(currencyType);
    }
}
