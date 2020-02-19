package com.soft.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Out {
    private int out_id;
    private String out_ware;
    private String out_price;
    private String out_reason;
    private Date out_time;
    private String out_user;
    private int out_status;

    public Out() {
    }

    public Out(int out_id, String out_ware, String out_price, String out_reason, Date out_time, String out_user, int out_status) {
        this.out_id = out_id;
        this.out_ware = out_ware;
        this.out_price = out_price;
        this.out_reason = out_reason;
        this.out_time = out_time;
        this.out_user = out_user;
        this.out_status = out_status;
    }

    @Override
    public String toString() {
        return "Out{" +
                "out_id=" + out_id +
                ", out_ware='" + out_ware + '\'' +
                ", out_price='" + out_price + '\'' +
                ", out_reason='" + out_reason + '\'' +
                ", out_time=" + out_time +
                ", out_user='" + out_user + '\'' +
                ", out_status=" + out_status +
                '}';
    }

    public int getOut_id() {
        return out_id;
    }

    public void setOut_id(int out_id) {
        this.out_id = out_id;
    }

    public String getOut_ware() {
        return out_ware;
    }

    public void setOut_ware(String out_ware) {
        this.out_ware = out_ware;
    }

    public String getOut_price() {
        return out_price;
    }

    public void setOut_price(String out_price) {
        this.out_price = out_price;
    }

    public String getOut_reason() {
        return out_reason;
    }

    public void setOut_reason(String out_reason) {
        this.out_reason = out_reason;
    }

    public Date getOut_time() {
        return out_time;
    }

    public void setOut_time(Date out_time) {
        this.out_time = out_time;
    }

    public String getOut_user() {
        return out_user;
    }

    public void setOut_user(String out_user) {
        this.out_user = out_user;
    }

    public int getOut_status() {
        return out_status;
    }

    public void setOut_status(int out_status) {
        this.out_status = out_status;
    }
}
