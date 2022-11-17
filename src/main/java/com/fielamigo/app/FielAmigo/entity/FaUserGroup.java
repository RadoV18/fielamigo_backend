package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaUserGroup {
    private Integer userGroupId;
    private Integer userId;
    private Integer groupId;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaUserGroup() {
    }

    public FaUserGroup(Integer userGroupId, Integer userId, Integer groupId, Integer status, String txHost,
            String txUser, Date txDate) {
        this.userGroupId = userGroupId;
        this.userId = userId;
        this.groupId = groupId;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
        return "FaUserGroup [userGroupId=" + userGroupId + ", userId=" + userId + ", groupId=" + groupId + ", status="
                + status + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }
}
