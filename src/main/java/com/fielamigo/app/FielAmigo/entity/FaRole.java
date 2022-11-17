package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaRole {
    private Integer roleId;
    private String name;
    private String description;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaRole() {
    }

    public FaRole(Integer roleId, String name, String description, Integer status, String txHost, String txUser,
            Date txDate) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaRole [roleId=" + roleId + ", name=" + name + ", description=" + description + ", status=" + status
                + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }

}
