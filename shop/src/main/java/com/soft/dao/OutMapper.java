package com.soft.dao;

import com.soft.pojo.Out;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OutMapper {
    List<Out> selectOut();
    List<Out> selectOutbystatus(int status);
    int addOut(Out out);
    Out selectOutbyname(String name,int status);
    Out selectOutbyid(int id);
    int updateOutbyname(Out out);
    int updateOutbyid(Out out);
    int deleteOut(int id);
}
