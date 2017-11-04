package com.example.komsic.cryptoconverter.model;

/**
 * Created by komsic on 11/4/2017.
 */

public class Currency {
    private static CurrencyType cType;

    public enum CurrencyType{
        ETH(ItemResponse.getBTC().getETH(), ItemResponse.getETH().getETH()),
        USD(ItemResponse.getBTC().getUSD(), ItemResponse.getETH().getUSD()),
        CAD(ItemResponse.getBTC().getCAD(), ItemResponse.getETH().getCAD()),
        EUR(ItemResponse.getBTC().getEUR(), ItemResponse.getETH().getEUR()),
        GBP(ItemResponse.getBTC().getGBP(), ItemResponse.getETH().getGBP()),
        CNY(ItemResponse.getBTC().getCNY(), ItemResponse.getETH().getCNY()),
        CHF(ItemResponse.getBTC().getCHF(), ItemResponse.getETH().getCHF()),
        AUD(ItemResponse.getBTC().getAUD(), ItemResponse.getETH().getAUD()),
        JPY(ItemResponse.getBTC().getJPY(), ItemResponse.getETH().getJPY()),
        SEK(ItemResponse.getBTC().getSEK(), ItemResponse.getETH().getSEK()),
        MXN(ItemResponse.getBTC().getMXN(), ItemResponse.getETH().getMXN()),
        NZD(ItemResponse.getBTC().getNZD(), ItemResponse.getETH().getNZD()),
        SGD(ItemResponse.getBTC().getSGD(), ItemResponse.getETH().getSGD()),
        HKD(ItemResponse.getBTC().getHKD(), ItemResponse.getETH().getHKD()),
        NOK(ItemResponse.getBTC().getNOK(), ItemResponse.getETH().getNOK()),
        TRY(ItemResponse.getBTC().getTRY(), ItemResponse.getETH().getTRY()),
        RUB(ItemResponse.getBTC().getRUB(), ItemResponse.getETH().getRUB()),
        ZAR(ItemResponse.getBTC().getZAR(), ItemResponse.getETH().getZAR()),
        BRL(ItemResponse.getBTC().getBRL(), ItemResponse.getETH().getBRL()),
        MYR(ItemResponse.getBTC().getMYR(), ItemResponse.getETH().getMYR()),
        NGN(ItemResponse.getBTC().getNGN(), ItemResponse.getETH().getNGN());

        private double currencyBTCRate;
        private double currencyETHRate;

        CurrencyType(double btcRate, double ethRate){
            currencyBTCRate = btcRate;
            currencyETHRate = ethRate;
        }
    }

    public Currency(CurrencyType currencyType) {
        this.cType = currencyType;
    }

    public CurrencyType getCType()
    {
        return cType;
    }

    public static void onChangeRatesValue(){
        cType.currencyBTCRate = ItemResponse.getBTC().getRate(cType);
        cType.currencyETHRate = ItemResponse.getETH().getRate(cType);
    }

    private double convertToBTC(double amount){
        return amount * cType.currencyBTCRate;
    }

    private double convertToETH(double amount){
        return amount * cType.currencyETHRate;
    }
}
