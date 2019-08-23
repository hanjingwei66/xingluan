package com.shuojie.controller;


import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import com.shuojie.service.UserMerberService;
import com.shuojie.utils.vo.Result;
import com.shuojie.utils.vo.ReturnUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private IUserServer userServer;
    @Autowired
    private UserMerberService usermerberservice;

 @RequestMapping(value = "/findAll" ,method = RequestMethod.GET)
    public List<User> findAll(@RequestParam User user){
     return userServer.selectUser(user);
 }

 @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String Regist(@RequestBody String request){
     String pageUrl = "user/register";
     return pageUrl;
 }

 //注册
 @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@RequestBody User user){
     String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
     user.setPassword(md5Password);
     Result result =userServer.register(user);
     return result;
 }

  //登录
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String index(){
     return "login";
    }
    @RequestMapping(value = "success",method = RequestMethod.POST)
    public String successs(){
     return "success";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnUser login(@RequestBody User user){
        ReturnUser result = userServer.toLogin(user);
        return result;
 }
 //发送短信
    @RequestMapping(value = "/sendMsg",method = RequestMethod.GET)
    public Result sendMsg(@RequestParam String telephone){
        Result result =usermerberservice.sendMsg(telephone);
        return result;
    }
}
