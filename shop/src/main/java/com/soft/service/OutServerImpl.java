package com.soft.service;

import com.soft.dao.OutMapper;
import com.soft.pojo.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OutServerImpl implements OutServer{
    @Autowired
    private OutMapper outMapper;
    @Override
    public List<Out> selectOut() {
        return outMapper.selectOut();
    }

    @Override
    public List<Out> selectOutbystatus(int status) {
        return outMapper.selectOutbystatus(status);
    }

    @Override
    public int addOut(Out out) {
        return outMapper.addOut(out);
    }

    @Override
    public Out selectOutbyname(String name,int status) {
        return outMapper.selectOutbyname(name,status);
    }

    @Override
    public Out selectOutbyid(int id) {
        return outMapper.selectOutbyid(id);
    }

    @Override
    public int updateOutbyname(Out out ) {
        return outMapper.updateOutbyname(out);
    }

    @Override
    public int updateOutbyid(Out out) {
        return outMapper.updateOutbyid(out);
    }

    @Override
    public int deleteOut(int id) {
        return outMapper.deleteOut(id);
    }
}
