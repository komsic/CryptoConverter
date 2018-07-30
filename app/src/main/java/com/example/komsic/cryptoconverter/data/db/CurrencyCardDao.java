package com.example.komsic.cryptoconverter.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.example.komsic.cryptoconverter.data.db.util.BooleanConverter;

import java.util.List;

@Dao
@TypeConverters({BooleanConverter.class})
public interface CurrencyCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(CurrencyCard card);

    @Query("SELECT * FROM currency_table WHERE selectedStatus = 1")
    LiveData<List<CurrencyCard>> getSelectedCards();

    @Query("SELECT currencyName, currencyType FROM currency_table WHERE selectedStatus = 0")
    LiveData<List<CurrencyCardSubset>> getUnselectedCards();

    @Query("SELECT * FROM currency_table")
    List<CurrencyCard> getAllCards();

    @Query("UPDATE currency_table " +
            "SET btcRate = :btcRate," +
            "ethRate = :ethRate " +
            "WHERE currencyType = :currencyType")
    void updateRates(String currencyType, double btcRate, double ethRate);

    @Query("UPDATE currency_table " +
            "SET selectedStatus = :selectedStatus " +
            "WHERE currencyType = :currencyType")
    void updateSelectedStatus(String currencyType, boolean selectedStatus);
}
