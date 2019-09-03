/*
*
* CurrentLine.java
* 
* @date 2019-08-28
*/
package com.shuojie.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrentLine {

    private Integer clid;

    private BigDecimal cuLiLongitude;

    private BigDecimal cuLiLatitude;
}