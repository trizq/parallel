package com.soft.service;

import com.soft.pojo.Out;

import java.util.List;

public interface OutServer {
    List<Out> selectOut();
    List<Out> selectOutbystatus(int status);
    int addOut(Out out);
    Out selectOutbyname(String name,int status);
    Out selectOutbyid(int id);
    int updateOutbyname(Out out);
    int updateOutbyid(Out out);
    int deleteOut(int id);
}
