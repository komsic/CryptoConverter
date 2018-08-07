package com.example.komsic.cryptoconverter;

import android.app.Application;

import com.example.komsic.cryptoconverter.data.CurrencyRepository;
import com.example.komsic.cryptoconverter.di.components.ApplicationComponent;
import com.example.komsic.cryptoconverter.di.components.DaggerApplicationComponent;
import com.example.komsic.cryptoconverter.di.modules.ApplicationModule;
import com.example.komsic.cryptoconverter.di.modules.RoomModule;

import javax.inject.Inject;

public class CurrencyApplication extends Application {

    @Inject
    CurrencyRepository mCurrencyRepository;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        getApplicationComponent().inject(this);
        super.onCreate();
    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .roomModule(new RoomModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
}
