package com.shuojie.serverImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuojie.dao.UserMapper;
import com.shuojie.domain.User;
import com.shuojie.domain.UserFirm;
import com.shuojie.service.IUserService;
import com.shuojie.service.RedisService;
import com.shuojie.utils.vo.Result;
import com.shuojie.utils.vo.ReturnUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Resource
    private UserMapper userMapper;

     private ReturnUser result;

     private Result baseResult;

     private UserFirm userFirm;
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
            String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(md5Password);
            String code = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + user.getMobile());
            Result res = new Result(200, "registerSuccess","api_register");
            if (user.getYzm().equals(code)) {
                userMapper.insert(user);
                return res;
            } else {
                res.setCode(401);
                res.setMessage("yzmError");
                res.setCommand("register");
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(201, "registerError","api_register");
        }
    }
    //登录
    @Override//待定mp
    public ReturnUser toLogin(User user) {
        try {
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        User login = userMapper.toLogin(user);
        if (login != null&& login.getLoginFlag() <= 3){
            ReturnUser result =new ReturnUser(200,"loginSuccess","api_login");
            result.setId(login.getId());
            result.setMobile(login.getMobile());
            result.setFirmId(login.getFirmId());
            result.setUsername(login.getUsername());
            result.setIdNumber(login.getIdNumber());
            result.setPosition(login.getPosition());
            result.setUserFirm(login.getUserFirm());
            //result.setFirmUserId(login.getFirmUserId());
            userMapper.updateReset(login.getMobile());//重置登陆状态
            return result;
        }else {
            result = new ReturnUser(201,"passwordError","api_login");
            userMapper.updateStuse(user);
            Integer logflag =userMapper.selectBytelphone(user.getMobile());
            this.result.setLoginFlag(logflag);
        }
        return result;
        }catch (Exception e) {
            return result;
            }
    }

    //忘记密码
    //根据手机号查用户信息
    @Override
    public Result updateUserPassworld(User newUser) {
        User oldUser = userMapper.getUserByMobile(newUser);//老的用户
        String code = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + newUser.getMobile());
        String md5Password = DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes());
        if(newUser.getYzm().equals(code)){
            oldUser.setPassword(md5Password);//用户数据库里的密码
            userMapper.updateUserPassworld(oldUser);
            userMapper.updateReset(newUser.getMobile());//重置
            baseResult = new Result(200,"updateSuccess","api_updatePassword");
            return baseResult;
        }else {
            baseResult = new Result(201,"updateError","api_updatePassword");
        }
        return baseResult;
    }

    //修改密码
    @Override
    public Result xiugaiUserPassworld(User newUser) {
        //1通过id获取修改前的对象（数据库里的）
        User oldUser = userMapper.xiugaiGetUserByid(newUser);
        //2把前台传来的原密码加密
        String md5OldPassword = DigestUtils.md5DigestAsHex(newUser.getOldPassword().getBytes());
        //获取验证码
        String code = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + newUser.getMobile());
        //判断验证码是否相等
        if (newUser.getYzm().equals(code)){
            //加密过后的俩个原密码是否相同
            if (oldUser.getPassword().equals(md5OldPassword)){
                //新密码加密
                String md5Password = DigestUtils.md5DigestAsHex(newUser.getPassword().getBytes());
                //赋值
                newUser.setPassword(md5Password);
                userMapper.xiugaiUserPassworld(newUser);
                baseResult = new Result(200,"xiugaiSuccess","api_xiugaiPassword");
            }else {
                baseResult = new Result(201,"xiugaiSuccess","api_xiugaiPassword");
            }
        }else {
            baseResult = new Result(401,"yzmError","api_xiugaiPassword");
        }
        return baseResult;
    }

    //查询用户信息
    public ReturnUser findUserById(User user){
        ReturnUser res= new ReturnUser(200,"查询成功","");
        User user1 = userMapper.findUserById(user);
        res.setUsername(user1.getUsername());
        res.setMobile(user1.getMobile());
        res.setFirmId(user1.getFirmId());
        res.setSex(user1.getSex());
        return res;
    }

}
