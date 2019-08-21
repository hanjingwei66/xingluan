/*
*
* RoleMapper.java
* 
* @date 2019-08-17
*/
package com.shuojie.dao;

import com.shuojie.domain.Role;
import com.shuojie.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {


    /**
     *
     * @mbggenerated 2019-08-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insert(Role record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insertSelective(Role record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    Role selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int updateByPrimaryKey(Role record);
}