package com.shuojie.dao.mapsMapper;

import com.shuojie.domain.maps.MapPoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapPointsMapper {

    void insertMapPoints(MapPoints mapPoints);
}