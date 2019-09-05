package com.shuojie.domain.maps;

import lombok.Data;

@Data
public class Current {

   // private Integer cuid;//当前点id

    private String lineName;//当前点name

    private Integer clid;//外键经纬度id

    private String currentDate;//切换时间

}