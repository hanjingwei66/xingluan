package com.shuojie.service;

import com.shuojie.domain.User;
import com.shuojie.utils.vo.Result;
import com.shuojie.utils.vo.ReturnUser;

import java.util.List;

public interface IUserServer {
     List<User> selectUser(User user);

     //注册
     Result register(User user);

     //登录
     ReturnUser toLogin(User user);

     //忘记密码
     //根据手机号查询
//     Result getUserByMobile(User user);

     Result updateUserPassworld(User user);
}
