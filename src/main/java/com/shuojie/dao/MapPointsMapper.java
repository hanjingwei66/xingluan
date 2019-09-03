package com.shuojie.dao;

import com.shuojie.domain.MapPoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapPointsMapper {

    void insertMapPoints(MapPoints mapPoints);
}