/*
*
* Origin.java
* 
* @date 2019-08-28
*/
package com.shuojie.domain;


import lombok.Data;

import java.util.List;
@Data
public class Origin {

    private Integer orid;

    private String orName;

    private List<OriginLine> originAndLine;

}