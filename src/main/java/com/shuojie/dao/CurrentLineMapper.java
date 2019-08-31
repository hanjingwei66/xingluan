/*
*
* CurrentLineMapper.java
* 
* @date 2019-08-28
*/
package com.shuojie.dao;

import com.shuojie.domain.CurrentLine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurrentLineMapper {

    /**
     *
     * 添加经纬度
     */
    void insertCurrentLine(CurrentLine currentLine);


}