package com.prosoft.processingcenter.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "sales_point")
public class SalesPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "pos_name")
    private String posName;

    @Column(name = "pos_address")
    private String posAddress;

    @Column(name = "pos_inn")
    private String posInn;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "acquiring_bank_id")
    private AcquiringBank acquiringBank;

    public SalesPoint() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getPosAddress() {
        return posAddress;
    }

    public void setPosAddress(String posAddress) {
        this.posAddress = posAddress;
    }

    public String getPosInn() {
        return posInn;
    }

    public void setPosInn(String posInn) {
        this.posInn = posInn;
    }

    public AcquiringBank getAcquiringBank() {
        return acquiringBank;
    }

    public void setAcquiringBank(AcquiringBank acquiringBank) {
        this.acquiringBank = acquiringBank;
    }
}
