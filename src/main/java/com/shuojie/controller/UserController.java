package com.shuojie.controller;

import com.shuojie.domain.Result;
import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private IUserServer userServer;
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
    public String register(@RequestBody User user){
     userServer.register(user);
     return "user/regSucess";
 }

  //登录
    @RequestMapping("/index")
    public String index(){
     return "login";
    }
    @RequestMapping("success")
    public String successs(){
     return "success";
    }
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public Result login(@RequestBody  User user){
        Result result = userServer.toLogin(user);
//        if(result.getCode() == 200)
//            model.addAttribute("user",user);
        return result;
    }

}
