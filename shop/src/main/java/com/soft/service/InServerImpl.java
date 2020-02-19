package com.soft.service;

import com.soft.dao.InMapper;
import com.soft.pojo.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InServerImpl implements InServer{
    @Autowired
    private InMapper inMapper;

    @Override
    public List<In> selectIn() {
        return inMapper.selectIn();
    }

    @Override
    public In selectInbyname(String name, int status) {
        return inMapper.selectInbyname(name,status);
    }

    @Override
    public int addIn(In in) {
        return inMapper.addIn(in);
    }

    @Override
    public int updateInbyname(In in) {
        return inMapper.updateInbyname(in);
    }

    @Override
    public In selectInbyid(int id) {
        return inMapper.selectInbyid(id);
    }

    @Override
    public int updateInbyid(In in) {
        return inMapper.updateInbyid(in);
    }

    @Override
    public int deleteIn(int id) {
        return inMapper.deleteIn(id);
    }

    @Override
    public List<In> selectInbystatus(int status) {
        return inMapper.selectInbystatus(status);
    }
}
