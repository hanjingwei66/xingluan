package com.shuojie.serverImpl;

import com.shuojie.dao.CurrentMapper;
import com.shuojie.domain.Current;
import com.shuojie.service.CurrentServer;
import com.shuojie.utils.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("currentServerImpl")
public class CurrentServerImpl implements CurrentServer {

    @Resource
    private CurrentMapper currentMapper;
    private Result result;

    //添加当前线路
    @Override
    public Result insertCurrent(Current current) {
        currentMapper.insertCurrent(current);
        if (current != null){
             result = new Result(200,"insertCurrentSuccess","insertCurrent");
        }else {
             result = new Result(201,"insertCurrentError","insertCurrent");
        }
        return result;
    }
}
