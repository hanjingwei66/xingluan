/*
*
* RolePermissionMapper.java
* 
* @date 2019-08-17
*/
package com.shuojie.dao;

import com.shuojie.domain.RolePermissionKey;

public interface RolePermissionMapper {
    /**
     *
     * @mbggenerated 2019-08-17
     */
    int deleteByPrimaryKey(RolePermissionKey key);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insert(RolePermissionKey record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insertSelective(RolePermissionKey record);
}