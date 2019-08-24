/*
*
* UserMapper.java
* 
* @date 2019-08-17
*/
package com.shuojie.dao;

import com.shuojie.domain.User;
import com.shuojie.utils.vo.Result;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectUser(User user);

    //注册
    void register(User user);

    //登录
    User toLogin(User user);

    //忘记密码
    //根据手机号查询
    User getUserByMobile(User user);

    //修改密码
   void updateUserPassworld(User newUser);
}