package com.example.komsic.cryptoconverter.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by komsic on 11/4/2017.
 */

public class Currency {
    private static CurrencyType cType;

    private static final String JSON_CURRENCY_TYPE = "Currency Time";
    private static final String JSON_BTC_RATE = "BTC Rate";
    private static final String JSON_ETH_RATE = "ETH Rate";

    public enum CurrencyType{
        ETH(0.0, 0.0), USD(0.0, 0.0), CAD(0.0, 0.0), EUR(0.0, 0.0), GBP(0.0, 0.0), CNY(0.0, 0.0),
        CHF(0.0, 0.0), AUD(0.0, 0.0), JPY(0.0, 0.0), SEK(0.0, 0.0), MXN(0.0, 0.0), NZD(0.0, 0.0),
        SGD(0.0, 0.0), HKD(0.0, 0.0), NOK(0.0, 0.0), TRY(0.0, 0.0), RUB(0.0, 0.0), ZAR(0.0, 0.0),
        BRL(0.0, 0.0), MYR(0.0, 0.0), NGN(0.0, 0.0);

        private double currencyToBTCRate;
        private double currencyToETHRate;

        CurrencyType(double currencyToBTCRate, double currencyToETHRate) {
            this.currencyToBTCRate = currencyToBTCRate;
            this.currencyToETHRate = currencyToETHRate;
        }

        CurrencyType() {
        }



        public static void onChangeRatesValue(){
            cType.currencyToBTCRate = ItemResponse.getBTC().getRate(cType);
            cType.currencyToETHRate = ItemResponse.getETH().getRate(cType);
        }
    }

    public Currency() {

    }

    public Currency(CurrencyType currencyType) {
        this.cType = currencyType;
    }

    public Currency(JSONObject json) throws JSONException{
        String cTypeString = json.getString(JSON_CURRENCY_TYPE);
        cType = Currency.CurrencyType.valueOf(cTypeString);
        cType.currencyToBTCRate = json.getDouble(JSON_BTC_RATE);
        cType.currencyToETHRate = json.getDouble(JSON_ETH_RATE);
    }

    public JSONObject convertToJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_CURRENCY_TYPE, cType);
        jsonObject.put(JSON_BTC_RATE, cType.currencyToBTCRate);
        jsonObject.put(JSON_ETH_RATE, cType.currencyToETHRate);

        return jsonObject;
    }

    public CurrencyType getCType()
    {
        return cType;
    }

    public double getBTCRate()
    {
        return cType.currencyToBTCRate;
    }

    public double getETHRate()
    {
        return cType.currencyToETHRate;
    }

    private double convertToBTC(double amount){
        return amount * cType.currencyToBTCRate;
    }

    private double convertToETH(double amount){
        return amount * cType.currencyToETHRate;
    }
}
