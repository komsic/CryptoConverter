package com.example.komsic.cryptoconverter.helper;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Util {

    public static String formatMoney(String currencyType, double money) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        formatter.setCurrency(Currency.getInstance(currencyType));

        return formatter.format(money);
    }
}
