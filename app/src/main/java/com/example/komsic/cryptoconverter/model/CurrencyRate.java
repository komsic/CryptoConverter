package com.example.komsic.cryptoconverter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by komsic on 11/4/2017.
 */

public class CurrencyRate {

    @SerializedName("ETH")
    @Expose
    private double eTH;

    @SerializedName("USD")
    @Expose
    private double uSD;

    @SerializedName("CAD")
    @Expose
    private double cAD;

    @SerializedName("EUR")
    @Expose
    private double eUR;

    @SerializedName("GBP")
    @Expose
    private double gBP;

    @SerializedName("CNY")
    @Expose
    private double cNY;

    @SerializedName("CHF")
    @Expose
    private double cHF;

    @SerializedName("AUD")
    @Expose
    private double aUD;

    @SerializedName("JPY")
    @Expose
    private double jPY;

    @SerializedName("SEK")
    @Expose
    private double sEK;

    @SerializedName("MXN")
    @Expose
    private double mXN;

    @SerializedName("NZD")
    @Expose
    private double nZD;

    @SerializedName("SGD")
    @Expose
    private double sGD;

    @SerializedName("HKD")
    @Expose
    private double hKD;

    @SerializedName("NOK")
    @Expose
    private double nOK;

    @SerializedName("TRY")
    @Expose
    private double tRY;

    @SerializedName("RUB")
    @Expose
    private double rUB;

    @SerializedName("ZAR")
    @Expose
    private double zAR;

    @SerializedName("BRL")
    @Expose
    private double bRL;

    @SerializedName("MYR")
    @Expose
    private double mYR;

    @SerializedName("NGN")
    @Expose
    private double nGN;

    public CurrencyRate(double eTH, double uSD, double cAD, double eUR, double gBP, double cNY,
                        double cHF, double aUD, double jPY, double sEK, double XN, double nZD,
                        double sGD, double hKD, double nOK, double tRY, double rUB, double zAR,
                        double bRL, double YR, double nGN) {
        this.eTH = eTH;
        this.uSD = uSD;
        this.cAD = cAD;
        this.eUR = eUR;
        this.gBP = gBP;
        this.cNY = cNY;
        this.cHF = cHF;
        this.aUD = aUD;
        this.jPY = jPY;
        this.sEK = sEK;
        mXN = XN;
        this.nZD = nZD;
        this.sGD = sGD;
        this.hKD = hKD;
        this.nOK = nOK;
        this.tRY = tRY;
        this.rUB = rUB;
        this.zAR = zAR;
        this.bRL = bRL;
        mYR = YR;
        this.nGN = nGN;
    }

    public double getETH() {
        return eTH;
    }

    public double getUSD() {
        return uSD;
    }

    public double getCAD() {
        return cAD;
    }

    public double getEUR() {
        return eUR;
    }

    public double getGBP() {
        return gBP;
    }

    public double getCNY() {
        return cNY;
    }

    public double getCHF() {
        return cHF;
    }

    public double getAUD() {
        return aUD;
    }

    public double getJPY() {
        return jPY;
    }

    public double getSEK() {
        return sEK;
    }

    public double getMXN() {
        return mXN;
    }

    public double getNZD() {
        return nZD;
    }

    public double getSGD() {
        return sGD;
    }

    public double getHKD() {
        return hKD;
    }

    public double getNOK() {
        return nOK;
    }

    public double getTRY() {
        return tRY;
    }

    public double getRUB() {
        return rUB;
    }

    public double getZAR() {
        return zAR;
    }

    public double getBRL() {
        return bRL;
    }

    public double getMYR() {
        return mYR;
    }

    public double getNGN() {
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
