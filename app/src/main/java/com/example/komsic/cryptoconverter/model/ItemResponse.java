package com.example.komsic.cryptoconverter.model;

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

    public CurrencyRate getBTC() {
        return bTC;
    }

    public  CurrencyRate getETH() {
        return eTH;
    }

//	TODO 3rd edit refactoring
//	public void initializeItemResponse(Currency c, double d){ 
//		mItemResponse.getBTC().initialize(c.getCType(), c.getBTCRate());
//		mItemResponse.getETH().initialize(c.getCType(), c.getBTCRate());
//	}
}
