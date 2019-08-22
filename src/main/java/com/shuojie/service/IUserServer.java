package com.shuojie.service;

import com.shuojie.domain.Result;
import com.shuojie.domain.User;

import java.util.List;

public interface IUserServer {
     List<User> selectUser(User user);

     //注册
     void register(User user);

     //登录
      Result toLogin(User user);
}
