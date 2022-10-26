package com.prosoft.processingcenter.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "merchant_category_code")
public class MerchantCategoryCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "mcc")
    private String mcc;

    @Column(name = "mcc_name")
    private String mccName;

    public MerchantCategoryCode() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMccName() {
        return mccName;
    }

    public void setMccName(String mccName) {
        this.mccName = mccName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantCategoryCode that = (MerchantCategoryCode) o;
        return id == that.id && mcc.equals(that.mcc) && mccName.equals(that.mccName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mcc, mccName);
    }

    @Override
    public String toString() {
        return "MerchantCategoryCodeRepository{" +
                "id=" + id +
                ", mcc='" + mcc + '\'' +
                ", mccName='" + mccName + '\'' +
                '}';
    }
}
