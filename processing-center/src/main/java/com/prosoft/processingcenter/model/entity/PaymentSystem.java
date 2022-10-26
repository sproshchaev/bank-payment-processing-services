package com.prosoft.processingcenter.model.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSystem that = (PaymentSystem) o;
        return id == that.id && paymentSystemName.equals(that.paymentSystemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentSystemName);
    }

    @Override
    public String toString() {
        return "PaymentSystemRepository{" +
                "id=" + id +
                ", paymentSystemName='" + paymentSystemName + '\'' +
                '}';
    }
}
