package com.example.komsic.cryptoconverter.di.application;

import com.example.komsic.cryptoconverter.CurrencyApplication;
import com.example.komsic.cryptoconverter.di.ApplicationScope;
import com.example.komsic.cryptoconverter.di.service.ServiceComponent;
import com.example.komsic.cryptoconverter.di.service.ServiceModule;
import com.example.komsic.cryptoconverter.di.view.ViewComponent;
import com.example.komsic.cryptoconverter.di.view.ViewModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, RoomModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(CurrencyApplication currencyApplication);

    ViewComponent newViewComponent(ViewModule viewModule);

    ServiceComponent newServiceComponent(ServiceModule serviceModule);
}
