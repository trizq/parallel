package com.soft.dao;


import com.soft.pojo.In;
import com.soft.pojo.Out;
import org.apache.ibatis.annotations.Mapper;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InMapper {
    List<In> selectIn();
    In selectInbyname(String name, int status);
    int addIn(In in);
    int updateInbyname(In in);
    In selectInbyid(int id);
    int updateInbyid(In in);
    int deleteIn(int id);
    List<In> selectInbystatus(int status);

}
