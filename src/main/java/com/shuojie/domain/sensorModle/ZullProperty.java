package com.shuojie.domain.sensorModle;

import lombok.Data;

import java.util.List;
@Data
public class ZullProperty {
    private Integer jiedianid;
    private Integer time;
    private Integer Voltage1;//电压
    private Integer Voltage2;
    private Integer Voltage3;
    private Integer Voltage4;
    private Double xSupersonic;//激光x轴坐标
    private Double ySupersonic;//激光y轴坐标
    private Double Distance;//传感器距离（单位cm）
    private List Acceleration;//加速度xyz
    private List AngularVelocity;//角速度xyz
    private List Angular;     //角度
    private double hight;     //高度

}
