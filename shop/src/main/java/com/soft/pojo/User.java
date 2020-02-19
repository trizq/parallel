package com.soft.pojo;

public class User {
    private int user_id;
    private String user_account;
    private String user_pwd;
    private String user_name;
    private int user_limit;

    public User() {
    }

    public User(int user_id, String user_account, String user_pwd, String user_name, int user_limit) {
        this.user_id = user_id;
        this.user_account = user_account;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_limit = user_limit;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_limit() {
        return user_limit;
    }

    public void setUser_limit(int user_limit) {
        this.user_limit = user_limit;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_account='" + user_account + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_limit=" + user_limit +
                '}';
    }
}
