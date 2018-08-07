package com.example.komsic.cryptoconverter.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.komsic.cryptoconverter.data.CurrencyRepository;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;

public class CurrencyDetailViewModel extends ViewModel {

    CurrencyRepository mRepository;

    public CurrencyDetailViewModel(CurrencyRepository repository) {
        mRepository = repository;
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
