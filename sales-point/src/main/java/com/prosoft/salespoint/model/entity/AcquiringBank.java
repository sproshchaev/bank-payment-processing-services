package com.prosoft.salespoint.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "acquiring_bank")
public class AcquiringBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "bic")
    private String bic;

    @Column(name = "abbreviated_name")
    private String abbreviatedName;

    public AcquiringBank() {
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

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcquiringBank that = (AcquiringBank) o;
        return id == that.id && bic.equals(that.bic) && abbreviatedName.equals(that.abbreviatedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bic, abbreviatedName);
    }

    @Override
    public String toString() {
        return "AcquiringBank{" +
                "id=" + id +
                ", bic='" + bic + '\'' +
                ", abbreviatedName='" + abbreviatedName + '\'' +
                '}';
    }
}
