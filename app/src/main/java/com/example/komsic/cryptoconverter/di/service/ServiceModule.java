package com.example.komsic.cryptoconverter.di.service;

import android.app.IntentService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    private final IntentService mService;

    public ServiceModule(IntentService service) {
        mService = service;
    }

    @Provides
    IntentService service() {
        return mService;
    }
}
