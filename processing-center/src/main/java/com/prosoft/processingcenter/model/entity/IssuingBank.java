package com.prosoft.processingcenter.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "issuing_bank")
public class IssuingBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "bic")
    private String bic;

    @Column(name = "bin")
    private String bin;

    @Column(name = "abbreviated_name")
    private String abbreviated_name;

    public IssuingBank() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getAbbreviated_name() {
        return abbreviated_name;
    }

    public void setAbbreviated_name(String abbreviated_name) {
        this.abbreviated_name = abbreviated_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuingBank that = (IssuingBank) o;
        return id == that.id && bic.equals(that.bic) && bin.equals(that.bin) && abbreviated_name.equals(that.abbreviated_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bic, bin, abbreviated_name);
    }

    @Override
    public String toString() {
        return "IssuingBankRepository{" +
                "id=" + id +
                ", bic='" + bic + '\'' +
                ", bin='" + bin + '\'' +
                ", abbreviated_name='" + abbreviated_name + '\'' +
                '}';
    }
}
