package com.soft.service;

import com.soft.pojo.Ware;

import java.util.List;

public interface WareServer {
    List<Ware> selectWare();
    int addWare(Ware ware);
    Ware selectWarebyid(int id);
    int updateWare(Ware ware);
    int deleteWare(int id);
    Ware selectWarebyname(String name);
}
