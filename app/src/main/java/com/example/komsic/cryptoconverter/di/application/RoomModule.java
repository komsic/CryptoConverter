package com.example.komsic.cryptoconverter.di.application;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.komsic.cryptoconverter.data.db.CurrencyCardDao;
import com.example.komsic.cryptoconverter.data.db.CurrencyDb;
import com.example.komsic.cryptoconverter.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private final CurrencyDb mDb;

    public RoomModule(final Application application) {
        mDb = Room.databaseBuilder(application,
                CurrencyDb.class, "currency_database")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        new CurrencyDb.PopulateDatabaseAsync(mDb, application).execute();
                    }
                })
                .build();
    }

    @Provides
    @ApplicationScope
    CurrencyCardDao providesCurrencyRepository() {
        return mDb.currencyDao();
    }
}
