package com.example.komsic.cryptoconverter.data.db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

import com.example.komsic.cryptoconverter.data.db.util.BooleanConverter;

@Entity(tableName = "currency_table")
@TypeConverters(BooleanConverter.class)
public class CurrencyCard {

    @PrimaryKey
    @NonNull
    public String currencyType;

    @NonNull
    public String currencyName;

    public double btcRate;

    public double ethRate;

    @NonNull
    public String imageAsset;

    public boolean selectedStatus;

    public CurrencyCard(@NonNull String currencyType, @NonNull String currencyName, double btcRate,
                        double ethRate, @NonNull String imageAsset, boolean selectedStatus) {
        this.currencyType = currencyType;
        this.currencyName = currencyName;
        this.btcRate = btcRate;
        this.ethRate = ethRate;
        this.imageAsset = imageAsset;
        this.selectedStatus = selectedStatus;
    }

    @Override
    public String toString() {
        return currencyType + " | " + currencyName + " | " + btcRate + " | " + ethRate + " | "
                + imageAsset + " | " + selectedStatus;
    }
}
