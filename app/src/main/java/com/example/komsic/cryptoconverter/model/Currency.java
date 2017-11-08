package com.example.komsic.cryptoconverter.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by komsic on 11/4/2017.
 */

public class Currency {
    private CurrencyType cType;

	public static final int CURRENT_CURRENCY = 0;
	public static final int BTC = 1;
	public static final int ETH = 2;
	
    private static final String JSON_CURRENCY_TYPE = "Currency Type";
    private static final String JSON_CURRENCY_TO_BTC_RATE = "Currency To BTC Rate";
    private static final String JSON_CURRENCY_TO_ETH_RATE = "Currency To ETH Rate";
	private static final String JSON_BTC_TO_ETH_RATE = "BTC To ETH Rate";

    public enum CurrencyType{
        ETH(), USD(), CAD(), EUR(), GBP(), CNY(),
        CHF(), AUD(), JPY(), SEK(), MXN(), NZD(),
        SGD(), HKD(), NOK(), TRY(), RUB(), ZAR(),
        BRL(), MYR(), NGN();

        private double currencyToBTCRate;
        private double currencyToETHRate;
		private double bTCToETHRate;
    }

    public Currency(CurrencyType currencyType) {
        this.cType = currencyType;
    }

    public Currency(JSONObject json) throws JSONException{
        String cTypeString = json.getString(JSON_CURRENCY_TYPE);
        cType = Currency.CurrencyType.valueOf(cTypeString);
        cType.currencyToBTCRate = json.getDouble(JSON_CURRENCY_TO_BTC_RATE);
        cType.currencyToETHRate = json.getDouble(JSON_CURRENCY_TO_ETH_RATE);
		cType.bTCToETHRate = json.getDouble(JSON_BTC_TO_ETH_RATE);
    }

    public JSONObject convertToJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_CURRENCY_TYPE, cType);
        jsonObject.put(JSON_CURRENCY_TO_BTC_RATE, cType.currencyToBTCRate);
        jsonObject.put(JSON_CURRENCY_TO_ETH_RATE, cType.currencyToETHRate);
		jsonObject.put(JSON_BTC_TO_ETH_RATE, cType.bTCToETHRate);

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
		cType.bTCToETHRate = itemResponse.getBTC().getETH();
    }

    public double getBTCRate()
    {
        return cType.currencyToBTCRate;
    }

    public double getETHRate()
    {
        return cType.currencyToETHRate;
    }

	public double getETHToBTCRate(){
		return cType.bTCToETHRate;
	}

    private double convertFromCurrencyToBTC(double amount){
        return amount * cType.currencyToBTCRate;
    }

    private double convertFromCurrencyToETH(double amount){
        return amount * cType.currencyToETHRate;
    }

	private double convertFromBTCToCurrency(double amount){
		double result = -1;
		if(cType.currencyToBTCRate > 0){
			result = amount / cType.currencyToBTCRate;
		}
		return result;
	}

	private double convertFromETHToCurrency(double amount){
		double result = -1;
		if(cType.currencyToETHRate > 0){
			result = amount / cType.currencyToETHRate;
		}
		return result;
	}
	
	private double convertFromBTCToETH(double amount){
		double result = -1;
		if(cType.bTCToETHRate > 0){
			result = amount / cType.bTCToETHRate;
		}
		return result;
	}
	
	private double convertFromETHToBTC(double amount){
		return amount * cType.bTCToETHRate;
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
				convertedResults[ETH] = convertFromCurrencyToBTC(amountTobeConverted);
		}
		return convertedResults;
	}
}
