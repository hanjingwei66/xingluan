package com.shuojie.dao.mapsMapper;

import com.shuojie.domain.maps.MapPoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapPointsMapper {

    //添加地图初始点
    void insertMapPoints(MapPoints mapPoints);
}