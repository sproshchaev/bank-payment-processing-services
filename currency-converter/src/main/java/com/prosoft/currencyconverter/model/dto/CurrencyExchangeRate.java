package com.prosoft.currencyconverter.model.dto;

public class CurrencyExchangeRate {
    private String responseDate;
    private String currencyLetterCodeFrom;
    private String currencyLetterCodeTo;
    private double rate;

    public CurrencyExchangeRate() {
    }

    public CurrencyExchangeRate(String responseDate, String currencyLetterCodeFrom, String currencyLetterCodeTo,
                                Double rate) {
        this.responseDate = responseDate;
        this.currencyLetterCodeFrom = currencyLetterCodeFrom;
        this.currencyLetterCodeTo = currencyLetterCodeTo;
        this.rate = rate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getCurrencyLetterCodeFrom() {
        return currencyLetterCodeFrom;
    }

    public void setCurrencyLetterCodeFrom(String currencyLetterCodeFrom) {
        this.currencyLetterCodeFrom = currencyLetterCodeFrom;
    }

    public String getCurrencyLetterCodeTo() {
        return currencyLetterCodeTo;
    }

    public void setCurrencyLetterCodeTo(String currencyLetterCodeTo) {
        this.currencyLetterCodeTo = currencyLetterCodeTo;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeRate{" +
                "responseDate='" + responseDate + '\'' +
                ", currencyLetterCodeFrom='" + currencyLetterCodeFrom + '\'' +
                ", currencyLetterCodeTo='" + currencyLetterCodeTo + '\'' +
                ", rate=" + rate +
                '}';
    }
}
