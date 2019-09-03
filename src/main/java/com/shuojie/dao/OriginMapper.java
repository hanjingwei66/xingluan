/*
*
* OriginMapper.java
* 
* @date 2019-08-28
*/
package com.shuojie.dao;


import com.shuojie.domain.Origin;
import com.shuojie.utils.vo.ReturnOrigin;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OriginMapper {

    //根据orName查询经纬度
    ArrayList<ReturnOrigin> getByName(Origin origin);

    //查询orName
    ArrayList getOriginName();

    //查询全部关联信息
    List<Origin> getOriginLine();
}