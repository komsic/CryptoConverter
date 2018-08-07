package com.example.komsic.cryptoconverter.di.view;

import android.app.AlarmManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.komsic.cryptoconverter.view.adapter.CurrencyConversionAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private final AppCompatActivity mActivity;

    public ViewModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    Context context() {
        return mActivity;
    }

    @Provides
    AppCompatActivity activity() {
        return mActivity;
    }

    @Provides
    LinearLayoutManager linearLayoutManager() {
        return new LinearLayoutManager(mActivity);
    }

    @Provides
    AlarmManager alarmManager() {
        return (AlarmManager) mActivity.getSystemService(Context.ALARM_SERVICE);
    }

    @Provides
    CurrencyConversionAdapter provideCurrencyConversionAdapter() {
        return new CurrencyConversionAdapter();
    }
}
