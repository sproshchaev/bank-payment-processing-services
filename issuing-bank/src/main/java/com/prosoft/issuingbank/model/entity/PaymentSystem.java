package com.prosoft.issuingbank.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_system")
public class PaymentSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "payment_system_name")
    private String paymentSystemName;

    @Column(name = "first_digit_bin")
    private String firstDigitBin;

    public PaymentSystem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaymentSystemName() {
        return paymentSystemName;
    }

    public void setPaymentSystemName(String paymentSystemName) {
        this.paymentSystemName = paymentSystemName;
    }

    public String getFirstDigitBin() {
        return firstDigitBin;
    }

    public void setFirstDigitBin(String firstDigitBin) {
        this.firstDigitBin = firstDigitBin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSystem that = (PaymentSystem) o;
        return id == that.id && paymentSystemName.equals(that.paymentSystemName) && firstDigitBin.equals(that.firstDigitBin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentSystemName, firstDigitBin);
    }

    @Override
    public String toString() {
        return "PaymentSystem{" +
                "id=" + id +
                ", paymentSystemName='" + paymentSystemName + '\'' +
                ", firstDigitBin='" + firstDigitBin + '\'' +
                '}';
    }
}
