package com.xinlan.model;

public class User {
    public static final int STATE_NORMAL = 1;
    public static final int STATE_REMOVED = 2;

    private long id;
    private String uid;
    private String account;
    private int age = -1;
    private int state = STATE_NORMAL;
    private String extra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}//end class
