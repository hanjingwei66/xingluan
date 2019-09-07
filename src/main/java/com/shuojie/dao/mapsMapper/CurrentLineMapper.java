package com.shuojie.dao.mapsMapper;

import com.shuojie.domain.maps.CurrentLine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurrentLineMapper {

    //添加经纬度
    void insertCurrentLine(CurrentLine currentLine);
}