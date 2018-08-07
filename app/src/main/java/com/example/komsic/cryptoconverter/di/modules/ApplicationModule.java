package com.example.komsic.cryptoconverter.di.modules;

import android.app.Application;

import com.example.komsic.cryptoconverter.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return mApplication;
    }
}
