package com.prosoft.salespoint.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "terminal")
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "terminal_id")
    private String terminalId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "mcc_id")
    private MerchantCategoryCode mcc;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_id")
    private SalesPoint pos;

    public Terminal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public MerchantCategoryCode getMcc() {
        return mcc;
    }

    public void setMcc(MerchantCategoryCode mcc) {
        this.mcc = mcc;
    }

    public SalesPoint getPos() {
        return pos;
    }

    public void setPos(SalesPoint pos) {
        this.pos = pos;
    }
}
