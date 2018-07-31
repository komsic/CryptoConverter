package com.example.komsic.cryptoconverter.data;

import com.example.komsic.cryptoconverter.data.db.CurrencyCard;

import java.math.BigDecimal;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyConverter {
    public static final int CURRENT_CURRENCY = 0;
    public static final int BTC = 1;
    public static final int ETH = 2;

    private CurrencyCard mCard;

    public CurrencyConverter(CurrencyCard card) {
        mCard = card;
    }

    public double[] convert(String cType, double amountTobeConverted) {
        double[] convertedResults = {0, 0, 0};
        switch (cType) {
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

    private double convertFromBTCToCurrency(double amount) {
        return roundUp((amount * mCard.btcRate), 2);
    }

    private double convertFromBTCToETH(double amount) {
        return roundUp((convertFromBTCToCurrency(amount) / mCard.ethRate), 2);
    }

    private double convertFromETHToCurrency(double amount) {
        return roundUp((amount * mCard.ethRate), 2);
    }

    private double convertFromETHToBTC(double amount) {
        double result = -1;
        if (mCard.ethRate > 0) {
            result = convertFromETHToCurrency(amount) / mCard.btcRate;
        }
        return roundUp(result, 4);
    }

    private double convertFromCurrencyToBTC(double amount) {
        double result = -1;
        if (mCard.btcRate > 0) {
            result = amount / mCard.btcRate;
        }
        return roundUp(result, 7);
    }

    private double convertFromCurrencyToETH(double amount) {
        double result = -1;
        if (mCard.ethRate > 0) {
            result = amount / mCard.ethRate;
        }
        return roundUp(result, 4);
    }

    private static double roundUp(double amount, int decimalPlace) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(amount));
        bigDecimal = bigDecimal.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }
}
