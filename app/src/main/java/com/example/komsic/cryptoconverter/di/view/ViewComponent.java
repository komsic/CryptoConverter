package com.example.komsic.cryptoconverter.di.view;

import com.example.komsic.cryptoconverter.view.ui.CurrencyConverterActivity;
import com.example.komsic.cryptoconverter.view.ui.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ViewModule.class})
public interface ViewComponent {

    void inject(MainActivity mainActivity);

    void inject(CurrencyConverterActivity currencyConverterActivity);
}
