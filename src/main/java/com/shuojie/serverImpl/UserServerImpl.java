package com.shuojie.serverImpl;

import com.shuojie.dao.UserMapper;
import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import com.shuojie.utils.vo.Result;
import com.shuojie.service.RedisService;
import com.shuojie.utils.vo.ReturnUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("userServiceImpl")
public class UserServerImpl implements IUserServer {

    @Resource
    private UserMapper userMapper;

     private ReturnUser result;

     private Result baseResult;
    @Autowired
    private RedisService redisService;
    /****
     * 注入配置文件数据
     */
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }

    //注册
    @Override
    public Result register(User user) {
        try {
            String code = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + user.getMobile());
            Result res = new Result(200, "注册成功");
            if (user.getYzm().equals(code)) {
                userMapper.register(user);
                return res;
            } else {
                res.setCode(401);
                res.setMessage("注册失败");
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(201, "手机号已被注册！");
        }
    }

    //登录
    @Override
    public ReturnUser toLogin(User user) {
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        User login = userMapper.toLogin(user);
        if (login != null){
            ReturnUser result =new ReturnUser(200,"登录成功");
            result.setId(login.getId());
            result.setMobile(login.getMobile());
            result.setFirmId(login.getFirmId());
            result.setUsername(login.getUsername());
            result.setIdNumber(login.getIdNumber());
            result.setAffiliationFirm(login.getAffiliationFirm());
            result.setPosition(login.getPosition());
            result.setAreaname(login.getAreaname());
            return result;
        }else {
            result = new ReturnUser(201,"帐号或密码输入错误！");
        }
        return result;
    }

    //忘记密码
    //根据手机号查用户信息
    @Override
    public Result updateUserPassworld(User newUser) {
        User oldUser = userMapper.getUserByMobile(newUser);//老的用户
        String code = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + newUser.getMobile());
        String md5Password = DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes());
        if(newUser.getYzm().equals(code)){
//            newUser.getPassword();//前台传过来
            oldUser.setPassword(md5Password);//用户数据库里的密码
              userMapper.updateUserPassworld(oldUser);
            baseResult = new Result(200,"修改成功！");
            return baseResult;
        }else {
            baseResult = new Result(201,"修改失败！");
        }
        return baseResult;
    }

    //查询用户信息
    public ReturnUser findUserById(User user){
        ReturnUser res= new ReturnUser(200,"查询成功");
        User user1 = userMapper.findUserById(user);
       res.setUsername(user1.getUsername());
        res.setMobile(user1.getMobile());
        return res;
    }



}
