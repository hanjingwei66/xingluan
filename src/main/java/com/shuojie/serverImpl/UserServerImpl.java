package com.shuojie.serverImpl;

import com.shuojie.dao.UserMapper;
import com.shuojie.domain.Result;
import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userServiceImpl")
public class UserServerImpl implements IUserServer {

    @Resource
    private UserMapper userMapper;

    private Result result;

    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }

    //注册
    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public Result toLogin(User user) {
        User login = userMapper.toLogin(user);
        if (login != null){
            result = new Result(200,"登录成功");
        }else {
            result = new Result(201,"用户或密码错误");
        }
        return result;
    }

    //登录
 /*   public Result toLogin(User user){
        User login = userMapper.toLogin(user);
        if (login != null){
            result = new Result(200,"登录成功");
        }else {
            result = new Result(201,"用户或密码错误");
        }
        return result;
    }*/
/*    @Override
    public Integer toLogin(String mobile, String password) {
        User user = new User();
        user.setPassword(password);
        user.setMobile(mobile);
        Integer userLogin = userMapper.toLogin(user);
        return userLogin;
    }*/

}
