/*
*
* PermissionMapper.java
* 
* @date 2019-08-17
*/
package com.shuojie.dao;

import com.shuojie.domain.Permission;

public interface PermissionMapper {
    /**
     *
     * @mbggenerated 2019-08-17
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insert(Permission record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int insertSelective(Permission record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    Permission selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     *
     * @mbggenerated 2019-08-17
     */
    int updateByPrimaryKey(Permission record);
}