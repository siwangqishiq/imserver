package com.xinlan.model;

public class Account {
    private String token;
    private long uid;
    private int sex;
    private String account;
    private String avatar;

    public static Account genFromUser(String token ,User user){
        Account account = new Account();
        account.setToken(token);
        account.setAccount(user.getAccount());
        account.setSex(user.getSex());
        account.setUid(user.getUid());
        account.setAvatar(user.getAvatar());
        return account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
