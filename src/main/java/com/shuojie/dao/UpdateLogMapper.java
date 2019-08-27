/*
*
* UpdateLogMapper.java
* 
* @date 2019-08-26
*/
package com.shuojie.dao;

import com.shuojie.domain.UpdateLog;

public interface UpdateLogMapper {
    /**
     *
     * @mbggenerated 2019-08-26
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-08-26
     */
    int insert(UpdateLog record);

    /**
     *
     * @mbggenerated 2019-08-26
     */
    int insertSelective(UpdateLog record);

    /**
     *
     * @mbggenerated 2019-08-26
     */
    UpdateLog selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2019-08-26
     */
    int updateByPrimaryKeySelective(UpdateLog record);

    /**
     *
     * @mbggenerated 2019-08-26
     */
    int updateByPrimaryKey(UpdateLog record);
}