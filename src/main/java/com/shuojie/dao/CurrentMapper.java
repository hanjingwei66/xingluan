package com.shuojie.dao;

import com.shuojie.domain.Current;
import com.shuojie.utils.vo.Result;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurrentMapper {

    //当前线路存储 line_name,clid,current_date
    void insertCurrent(Current current);

}