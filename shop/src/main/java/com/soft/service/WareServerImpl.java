package com.soft.service;


import com.soft.dao.WareMapper;
import com.soft.pojo.Ware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WareServerImpl implements WareServer{
    @Autowired
    private WareMapper wareMapper;
    @Override
    public List<Ware> selectWare() {
        return wareMapper.selectWare();
    }

    @Override
    public int addWare(Ware ware) {
        return wareMapper.addWare(ware);
    }

    @Override
    public Ware selectWarebyid(int id) {
        return wareMapper.selectWarebyid(id);
    }

    @Override
    public int updateWare(Ware ware) {
        return wareMapper.updateWare(ware);
    }

    @Override
    public int deleteWare(int id) {
        return wareMapper.deleteWare(id);
    }

    @Override
    public Ware selectWarebyname(String name) {
        return wareMapper.selectWarebyname(name);
    }
}
