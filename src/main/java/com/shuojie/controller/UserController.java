package com.shuojie.controller;

import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private IUserServer userServer;
 @RequestMapping("/findAll")
    public List<User> findAll(User user){
     return userServer.selectUser(user);
 }
}
