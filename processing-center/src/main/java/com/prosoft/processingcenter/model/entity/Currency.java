package com.prosoft.processingcenter.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "currency_digital_code")
    private String currencyDigitalCode;

    @Column(name = "currency_letter_code")
    private String currencyLetterCode;

    @Column(name = "currency_name")
    private String currencyName;

    public Currency() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyDigitalCode() {
        return currencyDigitalCode;
    }

    public void setCurrencyDigitalCode(String currencyDigitalCode) {
        this.currencyDigitalCode = currencyDigitalCode;
    }

    public String getCurrencyLetterCode() {
        return currencyLetterCode;
    }

    public void setCurrencyLetterCode(String currencyLetterCode) {
        this.currencyLetterCode = currencyLetterCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id == currency.id && currencyDigitalCode.equals(currency.currencyDigitalCode) && currencyLetterCode.equals(currency.currencyLetterCode) && currencyName.equals(currency.currencyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyDigitalCode, currencyLetterCode, currencyName);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyDigitalCode='" + currencyDigitalCode + '\'' +
                ", currencyLetterCode='" + currencyLetterCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                '}';
    }
}
