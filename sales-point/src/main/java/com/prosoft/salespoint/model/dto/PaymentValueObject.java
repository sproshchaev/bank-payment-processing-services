package com.prosoft.salespoint.model.dto;

/**
 * // todo del
 * make authorization - провести авторизацию новой покупки по банковской карте:
 * - terminal_id
 * - дата операции
 * - номер карты '4123450101654724'
 * - срок действия '2025-12-31'
 * - сумма операции '500.55'
 * - валюта операции 'RUB'
 * - код ответа
 * - код авторизации
 *
 * @return: "операция одобрена, код авторизации: 123456"
 */
public class PaymentValueObject {
    private String terminalId;
    private String transactionDate;
    private String cardNumber;
    private String expiryDate;
    private String sum;
    private String currencyLetterCode;
    private String errorCode;
    private String authorizationCode;

    public PaymentValueObject() {
    }

    public PaymentValueObject(String terminalId, String transactionDate, String cardNumber, String expiryDate,
                              String sum, String currencyLetterCode) {
        this.terminalId = terminalId;
        this.transactionDate = transactionDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.sum = sum;
        this.currencyLetterCode = currencyLetterCode;
    }

    public PaymentValueObject(String terminalId, String transactionDate, String cardNumber, String expiryDate,
                              String sum, String currencyLetterCode, String errorCode, String authorizationCode) {
        this.terminalId = terminalId;
        this.transactionDate = transactionDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.sum = sum;
        this.currencyLetterCode = currencyLetterCode;
        this.errorCode = errorCode;
        this.authorizationCode = authorizationCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCurrencyLetterCode() {
        return currencyLetterCode;
    }

    public void setCurrencyLetterCode(String currencyLetterCode) {
        this.currencyLetterCode = currencyLetterCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public String toString() {
        return "PaymentValueObject{" +
                "terminalId='" + terminalId + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", sum='" + sum + '\'' +
                ", currencyLetterCode='" + currencyLetterCode + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                '}';
    }
}
