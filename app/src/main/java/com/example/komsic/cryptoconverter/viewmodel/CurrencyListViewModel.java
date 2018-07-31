package com.example.komsic.cryptoconverter.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.komsic.cryptoconverter.data.CurrencyRepository;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.data.db.CurrencyCardSubset;

import java.util.List;

public class CurrencyListViewModel extends AndroidViewModel {
    private static final String TAG = "CurrencyListViewModel";

    private CurrencyRepository mRepository;
    private LiveData<List<CurrencyCard>> mSelectedCurrencies;
    private LiveData<List<CurrencyCardSubset>> mUnselectedCurrencies;

    public CurrencyListViewModel(@NonNull Application application) {
        super(application);

        mRepository = CurrencyRepository.getInstance(application);
        mSelectedCurrencies = mRepository.getSelectedCard();
        mUnselectedCurrencies = mRepository.getUnselectedCard();
    }

    public LiveData<List<CurrencyCard>> getSelectedCurrencies() {
        return mSelectedCurrencies;
    }

    public LiveData<List<CurrencyCardSubset>> getUnselectedCurrencies() {
        return mUnselectedCurrencies;
    }

    public MutableLiveData<Boolean> getRemoteDataFetchingStatus() {
        return CurrencyRepository.getRemoteDataFetchingStatus();
    }

    public void updateRates() {
        mRepository.updateRates();
    }

    public void updateSelectedStatus(String currencyType, boolean selectedStatus) {
        mRepository.updateSelectedStatus(currencyType, selectedStatus);
    }
}
