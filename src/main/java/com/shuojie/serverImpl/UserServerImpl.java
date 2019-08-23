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

    @Autowired
    private RedisService redisService;
    /****
     * 注入配置文件数据
     */
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;


    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }

    //注册
    @Override
    public Result register(User user) {
        try {
            Result res = new Result(200, "注册成功");
            if (true) {
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
}
