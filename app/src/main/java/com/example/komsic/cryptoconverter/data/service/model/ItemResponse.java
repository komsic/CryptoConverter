package com.example.komsic.cryptoconverter.data.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by komsic on 11/4/2017.
 */

public class ItemResponse {

    @SerializedName("BTC")
    @Expose
    private CurrencyRate bTC;

    @SerializedName("ETH")
    @Expose
    private CurrencyRate eTH;

    public ItemResponse() {
        bTC = new CurrencyRate();
        eTH = new CurrencyRate();
    }

    public ItemResponse(CurrencyRate bTC, CurrencyRate eTH) {
        this.bTC = bTC;
        this.eTH = eTH;
    }

    public CurrencyRate getBTC() {
        return bTC;
    }

    public double getBTC(String currencyType) {
        return bTC.getRate(currencyType);
    }

    public double getETH(String currencyType) {
        return eTH.getRate(currencyType);
    }

    public  CurrencyRate getETH() {
        return eTH;
    }
}
