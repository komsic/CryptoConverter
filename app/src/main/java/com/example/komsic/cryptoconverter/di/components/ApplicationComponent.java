package com.example.komsic.cryptoconverter.di.components;

import com.example.komsic.cryptoconverter.CurrencyApplication;
import com.example.komsic.cryptoconverter.di.ApplicationScope;
import com.example.komsic.cryptoconverter.di.modules.ApplicationModule;
import com.example.komsic.cryptoconverter.di.modules.RoomModule;
import com.example.komsic.cryptoconverter.di.view.ViewComponent;
import com.example.komsic.cryptoconverter.di.view.ViewModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(CurrencyApplication currencyApplication);

    ViewComponent newViewComponent(ViewModule viewModule);
}
