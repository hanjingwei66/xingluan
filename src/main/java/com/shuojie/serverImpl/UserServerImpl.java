package com.shuojie.serverImpl;

import com.shuojie.dao.UserMapper;
import com.shuojie.domain.User;
import com.shuojie.service.IUserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userServiceImpl")
public class UserServerImpl implements IUserServer {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }
}
