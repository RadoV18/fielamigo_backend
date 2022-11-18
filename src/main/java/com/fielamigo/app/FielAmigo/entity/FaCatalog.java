package com.fielamigo.app.FielAmigo.entity;

public class FaCatalog {
    private Integer catalogId;
    private Integer catalogTypeId;
    private String name;
    private Integer status;
    private String txHost;
    private String txUser;
    private String txDate;

    public FaCatalog() {
    }

    public FaCatalog(Integer catalogId, Integer catalogTypeId, String name, Integer status, String txHost,
            String txUser, String txDate) {
        this.catalogId = catalogId;
        this.catalogTypeId = catalogTypeId;
        this.name = name;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Integer getCatalogTypeId() {
        return catalogTypeId;
    }

    public void setCatalogTypeId(Integer catalogTypeId) {
        this.catalogTypeId = catalogTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaCatalog [catalogId=" + catalogId + ", catalogTypeId=" + catalogTypeId + ", name=" + name + ", status="
                + status + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }
}
