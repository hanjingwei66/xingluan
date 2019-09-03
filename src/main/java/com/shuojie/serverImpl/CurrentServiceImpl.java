package com.shuojie.serverImpl;

import com.shuojie.dao.CurrentMapper;
import com.shuojie.domain.Current;
import com.shuojie.service.CurrentService;
import com.shuojie.utils.vo.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("currentServiceImpl")
public class CurrentServiceImpl implements CurrentService {

    @Resource
    private CurrentMapper currentMapper;
    private Result result;

    //添加当前线路
    @Override
    public Result insertCurrent(Current current) {
        currentMapper.insertCurrent(current);
        String lineName = current.getLineName();
        Integer clid = current.getClid();
        String currentDate = current.getCurrentDate();
        if (!StringUtil.isNullOrEmpty(lineName)
                && clid !=null
                && !StringUtil.isNullOrEmpty(currentDate) ){
             result = new Result(200,"insertCurrentSuccess","insertCurrent");
        }else {
             result = new Result(201,"insertCurrentError","insertCurrent");
        }
        return result;
    }
}
