package com.example.komsic.cryptoconverter.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.komsic.cryptoconverter.CurrencyApplication;
import com.example.komsic.cryptoconverter.data.CurrencyRepository;
import com.example.komsic.cryptoconverter.di.service.ServiceComponent;
import com.example.komsic.cryptoconverter.di.service.ServiceModule;

import javax.inject.Inject;

public class SyncCurrencyService extends IntentService {
    private static final String TAG = "SyncCurrencyService";

    @Inject
    CurrencyRepository mRepository;

    public SyncCurrencyService() {
        super("SyncCurrencyService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getServiceComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (mRepository != null) {
            mRepository.updateRates();
            Log.e(TAG, "onHandleIntent: is not null");
        } else {
            Log.e(TAG, "onHandleIntent: is null");
        }
    }

    private ServiceComponent getServiceComponent() {
        return ((CurrencyApplication) getApplication())
                .getApplicationComponent()
                .newServiceComponent(new ServiceModule(this));
    }
}

