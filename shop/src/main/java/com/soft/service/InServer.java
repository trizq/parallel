package com.soft.service;

import com.soft.pojo.In;
import com.soft.pojo.Out;

import java.util.List;

public interface InServer {
    List<In> selectIn();
    In selectInbyname(String name, int status);
    int addIn(In in);
    int updateInbyname(In in);
    In selectInbyid(int id);
    int updateInbyid(In in);
    int deleteIn(int id);
    List<In> selectInbystatus(int status);
}
