package com.example.komsic.cryptoconverter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.example.komsic.cryptoconverter.model.Currency.CurrencyType.GBP;

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

    private double getETH() {
        return eTH;
    }

    private double getUSD() {
        return uSD;
    }

    private double getCAD() {
        return cAD;
    }

    private double getEUR() {
        return eUR;
    }

    private double getGBP() {
        return gBP;
    }

    private double getCNY() {
        return cNY;
    }

    private double getCHF() {
        return cHF;
    }

    private double getAUD() {
        return aUD;
    }

    private double getJPY() {
        return jPY;
    }

    private double getSEK() {
        return sEK;
    }

    private double getMXN() {
        return mXN;
    }

    private double getNZD() {
        return nZD;
    }

    private double getSGD() {
        return sGD;
    }

    private double getHKD() {
        return hKD;
    }

    private double getNOK() {
        return nOK;
    }

    private double getTRY() {
        return tRY;
    }

    private double getRUB() {
        return rUB;
    }

    private double getZAR() {
        return zAR;
    }

    private double getBRL() {
        return bRL;
    }

    private double getMYR() {
        return mYR;
    }

    private double getNGN() {
        return nGN;
    }

    public void seteTH(double eTH) {
        this.eTH = eTH;
    }

    public void setuSD(double uSD) {
        this.uSD = uSD;
    }

    public void setcAD(double cAD) {
        this.cAD = cAD;
    }

    public void seteUR(double eUR) {
        this.eUR = eUR;
    }

    public void setgBP(double gBP) {
        this.gBP = gBP;
    }

    public void setcNY(double cNY) {
        this.cNY = cNY;
    }

    public void setcHF(double cHF) {
        this.cHF = cHF;
    }

    public void setaUD(double aUD) {
        this.aUD = aUD;
    }

    public void setjPY(double jPY) {
        this.jPY = jPY;
    }

    public void setsEK(double sEK) {
        this.sEK = sEK;
    }

    public void setXN(double XN) {
        mXN = XN;
    }

    public void setnZD(double nZD) {
        this.nZD = nZD;
    }

    public void setsGD(double sGD) {
        this.sGD = sGD;
    }

    public void sethKD(double hKD) {
        this.hKD = hKD;
    }

    public void setnOK(double nOK) {
        this.nOK = nOK;
    }

    public void settRY(double tRY) {
        this.tRY = tRY;
    }

    public void setrUB(double rUB) {
        this.rUB = rUB;
    }

    public void setzAR(double zAR) {
        this.zAR = zAR;
    }

    public void setbRL(double bRL) {
        this.bRL = bRL;
    }

    public void setYR(double YR) {
        mYR = YR;
    }

    public void setnGN(double nGN) {
        this.nGN = nGN;
    }

    public double getRate(Currency.CurrencyType cType) {
        double amount;
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

    public void initialize(Currency.CurrencyType cType, double d) {
        switch(cType){
            case ETH:
                seteTH(d);
                break;
            case USD:
                setuSD(d);
                break;
            case CAD:
                setcAD(d);
                break;
            case EUR:
                seteUR(d);
                break;
            case GBP:
                setgBP(d); 
				break;
            case CNY: 
				setcNY(d);
                break;
            case CHF:
                setcHF(d);
                break;
            case AUD:
                setaUD(d);
                break;
            case JPY:
                setjPY(d);
                break;
            case SEK:
                setsEK(d);
                break;
            case MXN:
                setXN(d);
                break;
            case NZD:
                setnZD(d);
                break;
            case SGD:
                setsGD(d);
                break;
            case HKD:
                sethKD(d);
                break;
            case NOK:
                setnOK(d);
                break;
            case TRY:
                settRY(d);
                break;
            case RUB:
                setrUB(d);
                break;
            case ZAR:
                setzAR(d);
                break;
            case BRL:
                setbRL(d);
                break;
            case MYR:
                setYR(d);
                break;
            case NGN:
                setnGN(d);
                break;
        }
    }
}
