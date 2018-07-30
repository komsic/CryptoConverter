package com.example.komsic.cryptoconverter.data.db;

import android.arch.persistence.room.ColumnInfo;

// A POJO used to return a subset of CurrencyCard
public class CurrencyCardSubset {

    @ColumnInfo
    public String currencyType;

    @ColumnInfo
    public String currencyName;
}
