package com.example.komsic.cryptoconverter;

import android.app.IntentService;
import android.content.Intent;

import com.example.komsic.cryptoconverter.data.CurrencyRepository;

public class SyncCurrencyService extends IntentService {
    private static final String TAG = "SyncCurrencyService";

    public SyncCurrencyService() {
        super("SyncCurrencyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        CurrencyRepository.getInstance(getApplication()).updateRates();
    }
}
