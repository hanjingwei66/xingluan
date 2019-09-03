package com.shuojie.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OriginLine {

    private Integer olid;

    private BigDecimal liLongitude;

    private BigDecimal liLatitude;

    private Integer orid;

}