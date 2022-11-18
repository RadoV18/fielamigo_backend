package com.fielamigo.app.FielAmigo.entity;

public class FaImage {
    private Integer imageId;
    private String url;
    private Integer status;
    private String txHost;
    private String txUser;
    private String txDate;

    public FaImage() {
    }

    public FaImage(Integer imageId, String url, Integer status, String txHost, String txUser, String txDate) {
        this.imageId = imageId;
        this.url = url;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "FaImage [imageId=" + imageId + ", url=" + url + ", status=" + status + ", txHost=" + txHost
                + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }

}
