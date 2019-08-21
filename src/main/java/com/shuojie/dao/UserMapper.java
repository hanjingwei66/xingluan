/*
*
* UserMapper.java
* 
* @date 2019-08-17
*/
package com.shuojie.dao;

import com.shuojie.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<User> selectUser(User user);

    /**
     * 根据用户查询用户信息
     */
    public Map<String,Object> queryInfoByUsername(String username);

    /**
     *插入一条数据
     */
    public void insertData(Map<String,String> data);
}