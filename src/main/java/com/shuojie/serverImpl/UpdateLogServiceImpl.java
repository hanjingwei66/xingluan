package com.shuojie.serverImpl;

import com.shuojie.dao.UpdateLogMapper;
import com.shuojie.domain.UpdateLog;
import com.shuojie.service.UpdateLogService;
import com.shuojie.utils.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("updateLogServiceImpl")
public class UpdateLogServiceImpl implements UpdateLogService {

    @Resource
    private UpdateLogMapper updateLogMapper;

//    private ReturnUpdateLog returnUpdateLog;

    @Override
    public Result getUpdateLog() {
        UpdateLog updateLog = updateLogMapper.getUpdateLog();
        if (updateLog != null){
            Result result = new Result<UpdateLog>(200, "getUpdateLogSuccess", "api_getUpdateLog", updateLog);
            return  result;
        }else {
            Result result = new Result<UpdateLog>(201,"getUpdateLogError","api_getUpdateLog");
            return result;
        }
    }
}
