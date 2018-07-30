package com.example.komsic.cryptoconverter.data.service.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * Created by komsic on 11/4/2017.
 */

public class Currency {
	public static final int CURRENT_CURRENCY = 0;
	public static final int BTC = 1;
	public static final int ETH = 2;
    private static final String JSON_CURRENCY_TYPE = "Currency Type";
    private static final String JSON_CURRENCY_TO_BTC_RATE = "Currency To BTC Rate";
    private static final String JSON_CURRENCY_TO_ETH_RATE = "Currency To ETH Rate";
	private static final String JSON_BTC_TO_ETH_RATE = "BTC To ETH Rate";
    private CurrencyType cType;

    public Currency(CurrencyType currencyType) {
        this.cType = currencyType;
    }

    public Currency(JSONObject json) throws JSONException{
        String cTypeString = json.getString(JSON_CURRENCY_TYPE);
        cType = Currency.CurrencyType.valueOf(cTypeString);
        cType.currencyToBTCRate = json.getDouble(JSON_CURRENCY_TO_BTC_RATE);
        cType.currencyToETHRate = json.getDouble(JSON_CURRENCY_TO_ETH_RATE);
        cType.eTHToBTCRate = json.getDouble(JSON_BTC_TO_ETH_RATE);
    }

    private static double roundUp(double amount, int decimalPlace) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(amount));
        bigDecimal = bigDecimal.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public JSONObject convertToJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_CURRENCY_TYPE, cType);
        jsonObject.put(JSON_CURRENCY_TO_BTC_RATE, cType.currencyToBTCRate);
        jsonObject.put(JSON_CURRENCY_TO_ETH_RATE, cType.currencyToETHRate);
        jsonObject.put(JSON_BTC_TO_ETH_RATE, cType.eTHToBTCRate);

        return jsonObject;
    }

    public CurrencyType getCType()
    {
        return cType;
    }

    //This is to update the cType rates
    public void onChangeRatesValue(ItemResponse itemResponse){
        cType.currencyToBTCRate = itemResponse.getBTC().getRate(cType);
        cType.currencyToETHRate = itemResponse.getETH().getRate(cType);
        cType.eTHToBTCRate = itemResponse.getBTC().getETH();
    }

    public boolean isCardAvailableForConversion() {
        boolean isAvailable = true;

        if (getCurrencyToBTCRate() == 0
                || getCurrencyToETHRate() == 0
                || getETHToBTCRate() == 0) {
            isAvailable = false;
        }

        return isAvailable;
    }

    public double getCurrencyToBTCRate()
    {
        return cType.currencyToBTCRate;
    }

    public double getCurrencyToETHRate()
    {
        return cType.currencyToETHRate;
    }

	public double getETHToBTCRate(){
        return cType.eTHToBTCRate;
    }

    private double convertFromCurrencyToBTC(double amount){
		double result = -1;
		if(cType.currencyToBTCRate > 0){
			result = amount / cType.currencyToBTCRate;
		}
		return roundUp(result, 7);
    }

    private double convertFromCurrencyToETH(double amount){
		double result = -1;
		if(cType.currencyToETHRate > 0){
			result = amount / cType.currencyToETHRate;
		}
		return roundUp(result, 4);
    }

	private double convertFromBTCToCurrency(double amount){
		return roundUp((amount * cType.currencyToBTCRate), 2);
	}

	private double convertFromETHToCurrency(double amount){
		return roundUp((amount * cType.currencyToETHRate), 2);
	}
	
	private double convertFromBTCToETH(double amount){
        return roundUp((amount * cType.eTHToBTCRate), 2);
    }
	
	private double convertFromETHToBTC(double amount){
		double result = -1;
        if (cType.eTHToBTCRate > 0) {
            result = amount / cType.eTHToBTCRate;
        }
		return roundUp(result, 4);
	}

	public double[] convert(String cType, double amountTobeConverted){
		double[] convertedResults = {0, 0, 0};
		switch(cType){
			case "BTC":
				convertedResults[CURRENT_CURRENCY] = convertFromBTCToCurrency(amountTobeConverted);
				convertedResults[BTC] = amountTobeConverted;
				convertedResults[ETH] = convertFromBTCToETH(amountTobeConverted);
				break;
			case "ETH":
				convertedResults[CURRENT_CURRENCY] = convertFromETHToCurrency(amountTobeConverted);
				convertedResults[BTC] = convertFromETHToBTC(amountTobeConverted);
				convertedResults[ETH] = amountTobeConverted;
				break;
			default:
				convertedResults[CURRENT_CURRENCY] = amountTobeConverted;
				convertedResults[BTC] = convertFromCurrencyToBTC(amountTobeConverted);
				convertedResults[ETH] = convertFromCurrencyToETH(amountTobeConverted);
		}
		return convertedResults;
	}

    public enum CurrencyType {
        ETH(), USD(), CAD(), EUR(), GBP(), CNY(),
        CHF(), AUD(), JPY(), SEK(), MXN(), NZD(),
        SGD(), HKD(), NOK(), TRY(), RUB(), ZAR(),
        BRL(), MYR(), NGN();

        private double currencyToBTCRate;
        private double currencyToETHRate;
        private double eTHToBTCRate;
    }
}
