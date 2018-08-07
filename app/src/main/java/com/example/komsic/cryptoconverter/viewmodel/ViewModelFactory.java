package com.example.komsic.cryptoconverter.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.komsic.cryptoconverter.data.CurrencyRepository;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final CurrencyRepository mRepository;

    @Inject
    public ViewModelFactory(CurrencyRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CurrencyDetailViewModel.class)) {
            return (T) new CurrencyDetailViewModel(mRepository);
        } else if (modelClass.isAssignableFrom(CurrencyListViewModel.class)) {
            return (T) new CurrencyListViewModel(mRepository);
        } else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
