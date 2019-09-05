package com.shuojie.dao.mapsMapper;

import com.shuojie.domain.maps.Current;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurrentMapper {

    //当前线路存储 line_name,clid,current_date
    void insertCurrent(Current current);

}