package com.shuojie.domain.maps;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrentLine {

    private Integer clid;

    private BigDecimal cuLiLongitude;

    private BigDecimal cuLiLatitude;
}