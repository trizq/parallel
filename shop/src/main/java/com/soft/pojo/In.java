package com.soft.pojo;

import java.util.Date;

public class In {
    private int in_id;
    private String in_ware;
    private String in_price;
    private String in_reason;
    private Date in_time;
    private String in_user;
    private int in_status;

    public In() {
    }

    public In(int in_id, String in_ware, String in_price, String in_reason, Date in_time, String in_user, int in_status) {
        this.in_id = in_id;
        this.in_ware = in_ware;
        this.in_price = in_price;
        this.in_reason = in_reason;
        this.in_time = in_time;
        this.in_user = in_user;
        this.in_status = in_status;
    }

    @Override
    public String toString() {
        return "In{" +
                "in_id=" + in_id +
                ", in_ware='" + in_ware + '\'' +
                ", in_price='" + in_price + '\'' +
                ", in_reason='" + in_reason + '\'' +
                ", in_time=" + in_time +
                ", in_user='" + in_user + '\'' +
                ", in_status=" + in_status +
                '}';
    }

    public int getIn_id() {
        return in_id;
    }

    public void setIn_id(int in_id) {
        this.in_id = in_id;
    }

    public String getIn_ware() {
        return in_ware;
    }

    public void setIn_ware(String in_ware) {
        this.in_ware = in_ware;
    }

    public String getIn_price() {
        return in_price;
    }

    public void setIn_price(String in_price) {
        this.in_price = in_price;
    }

    public String getIn_reason() {
        return in_reason;
    }

    public void setIn_reason(String in_reason) {
        this.in_reason = in_reason;
    }

    public Date getIn_time() {
        return in_time;
    }

    public void setIn_time(Date in_time) {
        this.in_time = in_time;
    }

    public String getIn_user() {
        return in_user;
    }

    public void setIn_user(String in_user) {
        this.in_user = in_user;
    }

    public int getIn_status() {
        return in_status;
    }

    public void setIn_status(int in_status) {
        this.in_status = in_status;
    }
}
