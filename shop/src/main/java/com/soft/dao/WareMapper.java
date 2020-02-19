package com.soft.dao;

import com.soft.pojo.Ware;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WareMapper {
    List<Ware> selectWare();
    int addWare(Ware ware);
    Ware selectWarebyid(int id);
    int updateWare(Ware ware);
    int deleteWare(int id);
    Ware selectWarebyname(String name);
}


