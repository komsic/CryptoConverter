package com.example.komsic.cryptoconverter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by komsic on 11/4/2017.
 */

public class ItemResponse {

    @SerializedName("BTC")
    @Expose
    private static CurrencyRate bTC;

    @SerializedName("ETH")
    @Expose
    private static CurrencyRate eTH;

    public static CurrencyRate getBTC() {
        return bTC;
    }

    public static CurrencyRate getETH() {
        return eTH;
    }

    public static CurrencyRate setBTC() {
        ItemResponse.bTC = new CurrencyRate();
        return ItemResponse.bTC;
    }

    public static CurrencyRate setETH() {
        ItemResponse.eTH = new CurrencyRate();
        return  ItemResponse.eTH;
    }
}
