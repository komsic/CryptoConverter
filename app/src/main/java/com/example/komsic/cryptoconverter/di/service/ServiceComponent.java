package com.example.komsic.cryptoconverter.di.service;

import com.example.komsic.cryptoconverter.service.SyncCurrencyService;

import dagger.Subcomponent;

@Subcomponent(modules = {ServiceModule.class})
public interface ServiceComponent {

    void inject(SyncCurrencyService syncCurrencyService);
}
