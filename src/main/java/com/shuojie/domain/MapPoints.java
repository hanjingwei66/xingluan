package com.shuojie.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MapPoints {

    private Integer mid;//数字空间地图点id

    private String pointsName;//数字空间地图点名称（String时间）

    private BigDecimal maLongitude;//数字空间地图点经度

    private BigDecimal maLatitude;//数字空间地图点纬度

}