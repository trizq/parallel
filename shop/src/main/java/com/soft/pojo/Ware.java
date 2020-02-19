package com.soft.pojo;

import java.util.Date;

public class Ware {
    private int ware_id;
    private String ware_name;
    private String ware_in;
    private String ware_out;
    private String ware_num;
    private Date ware_date;

    public Ware() {
    }

    public Ware(int ware_id, String ware_name, String ware_in, String ware_out, String ware_num, Date ware_date) {
        this.ware_id = ware_id;
        this.ware_name = ware_name;
        this.ware_in = ware_in;
        this.ware_out = ware_out;
        this.ware_num = ware_num;
        this.ware_date = ware_date;
    }

    @Override
    public String toString() {
        return "Ware{" +
                "ware_id=" + ware_id +
                ", ware_name='" + ware_name + '\'' +
                ", ware_in='" + ware_in + '\'' +
                ", ware_out='" + ware_out + '\'' +
                ", ware_num='" + ware_num + '\'' +
                ", ware_date=" + ware_date +
                '}';
    }

    public int getWare_id() {
        return ware_id;
    }

    public void setWare_id(int ware_id) {
        this.ware_id = ware_id;
    }

    public String getWare_name() {
        return ware_name;
    }

    public void setWare_name(String ware_name) {
        this.ware_name = ware_name;
    }

    public String getWare_in() {
        return ware_in;
    }

    public void setWare_in(String ware_in) {
        this.ware_in = ware_in;
    }

    public String getWare_out() {
        return ware_out;
    }

    public void setWare_out(String ware_out) {
        this.ware_out = ware_out;
    }

    public String getWare_num() {
        return ware_num;
    }

    public void setWare_num(String ware_num) {
        this.ware_num = ware_num;
    }

    public Date getWare_date() {
        return ware_date;
    }

    public void setWare_date(Date ware_date) {
        this.ware_date = ware_date;
    }
}
