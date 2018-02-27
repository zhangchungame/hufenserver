package com.dandinglong.hufenserver.dto;

import javax.persistence.Id;
import java.util.Date;

public class HfToutiaoAccount {
    @Id
    private int id;
    private String toutiaoAccount;
    private String firstArticleId;
    private Date lastLoginTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToutiaoAccount() {
        return toutiaoAccount;
    }

    public void setToutiaoAccount(String toutiaoAccount) {
        this.toutiaoAccount = toutiaoAccount;
    }

    public String getFirstArticleId() {
        return firstArticleId;
    }

    public void setFirstArticleId(String firstArticleId) {
        this.firstArticleId = firstArticleId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}