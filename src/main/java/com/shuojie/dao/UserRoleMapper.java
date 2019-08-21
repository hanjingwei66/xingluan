/*
*
* UserRoleMapper.java
* 
* @date 2019-08-17
*/
package com.shuojie.dao;

import com.shuojie.domain.UserRoleKey;

public interface UserRoleMapper {
    /**
     *
     * @mbggenerated 2019-08-17
     */
    int deleteByPrimaryKey(UserRoleKey key);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insert(UserRoleKey record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insertSelective(UserRoleKey record);
}