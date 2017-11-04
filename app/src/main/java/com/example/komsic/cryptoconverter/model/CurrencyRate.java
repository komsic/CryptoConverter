package com.example.komsic.cryptoconverter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyRate {

    @SerializedName("ETH")
    @Expose
    private Double eTH;

    @SerializedName("USD")
    @Expose
    private Double uSD;

    @SerializedName("CAD")
    @Expose
    private Double cAD;

    @SerializedName("EUR")
    @Expose
    private Double eUR;

    @SerializedName("GBP")
    @Expose
    private Double gBP;

    @SerializedName("CNY")
    @Expose
    private Double cNY;

    @SerializedName("CHF")
    @Expose
    private Double cHF;

    @SerializedName("AUD")
    @Expose
    private Double aUD;

    @SerializedName("JPY")
    @Expose
    private Double jPY;

    @SerializedName("SEK")
    @Expose
    private Double sEK;

    @SerializedName("MXN")
    @Expose
    private Double mXN;

    @SerializedName("NZD")
    @Expose
    private Double nZD;

    @SerializedName("SGD")
    @Expose
    private Double sGD;

    @SerializedName("HKD")
    @Expose
    private Double hKD;

    @SerializedName("NOK")
    @Expose
    private Double nOK;

    @SerializedName("TRY")
    @Expose
    private Double tRY;

    @SerializedName("RUB")
    @Expose
    private Double rUB;

    @SerializedName("ZAR")
    @Expose
    private Double zAR;

    @SerializedName("BRL")
    @Expose
    private Double bRL;

    @SerializedName("MYR")
    @Expose
    private Double mYR;

    @SerializedName("NGN")
    @Expose
    private Double nGN;

    public Double getETH() {
        return eTH;
    }

    public Double getUSD() {
        return uSD;
    }

    public Double getCAD() {
        return cAD;
    }

    public Double getEUR() {
        return eUR;
    }

    public Double getGBP() {
        return gBP;
    }

    public Double getCNY() {
        return cNY;
    }

    public Double getCHF() {
        return cHF;
    }

    public Double getAUD() {
        return aUD;
    }

    public Double getJPY() {
        return jPY;
    }

    public Double getSEK() {
        return sEK;
    }

    public Double getMXN() {
        return mXN;
    }

    public Double getNZD() {
        return nZD;
    }

    public Double getSGD() {
        return sGD;
    }

    public Double getHKD() {
        return hKD;
    }

    public Double getNOK() {
        return nOK;
    }

    public Double getTRY() {
        return tRY;
    }

    public Double getRUB() {
        return rUB;
    }

    public Double getZAR() {
        return zAR;
    }

    public Double getBRL() {
        return bRL;
    }

    public Double getMYR() {
        return mYR;
    }

    public Double getNGN() {
        return nGN;
    }

    public double getRate(Currency.CurrencyType cType) {
        double amount = 0;
        switch(cType){
            case ETH:
                amount = getETH();
                break;
            case USD:
                amount = getUSD();
                break;
            case CAD:
                amount = getCAD();
                break;
            case EUR:
                amount = getEUR();
                break;
            case GBP:
                amount = getGBP();
                break;
            case CNY:
                amount = getCNY();
                break;
            case CHF:
                amount = getCHF();
                break;
            case AUD:
                amount = getAUD();
                break;
            case JPY:
                amount = getJPY();
                break;
            case SEK:
                amount = getSEK();
                break;
            case MXN:
                amount = getMXN();
                break;
            case NZD:
                amount = getNZD();
                break;
            case SGD:
                amount = getSGD();
                break;
            case HKD:
                amount = getHKD();
                break;
            case NOK:
                amount = getNOK();
                break;
            case TRY:
                amount = getTRY();
                break;
            case RUB:
                amount = getRUB();
                break;
            case ZAR:
                amount = getZAR();
                break;
            case BRL:
                amount = getBRL();
                break;
            case MYR:
                amount = getMYR();
                break;
            case NGN:
                amount = getNGN();
                break;
            default:
                amount = -1;
                break;
        }

        return amount;
    }
}
