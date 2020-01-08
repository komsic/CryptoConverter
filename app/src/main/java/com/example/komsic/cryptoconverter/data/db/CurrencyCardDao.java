package com.example.komsic.cryptoconverter.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

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

    @Query("SELECT * FROM currency_table WHERE currencyType = :currencyType")
    LiveData<CurrencyCard> getCardByType(String currencyType);

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
