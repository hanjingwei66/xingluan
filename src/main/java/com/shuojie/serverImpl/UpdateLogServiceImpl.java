package com.shuojie.serverImpl;

import com.shuojie.dao.UpdateLogMapper;
import com.shuojie.domain.UpdateLog;
import com.shuojie.service.UpdateLogService;
import com.shuojie.utils.vo.ReturnUpdateLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("updateLogServiceImpl")
public class UpdateLogServiceImpl implements UpdateLogService {

    @Resource
    private UpdateLogMapper updateLogMapper;

//    private ReturnUpdateLog returnUpdateLog;

    @Override
    public ReturnUpdateLog getUpdateLog() {
        UpdateLog updateLog = updateLogMapper.getUpdateLog();
        if (updateLog != null){
            ReturnUpdateLog returnUpdateLog = new ReturnUpdateLog(200,"getUpdateLogSuccess","api_getUpdateLog");
            returnUpdateLog.setUpdateName(updateLog.getUpdateName());
            returnUpdateLog.setUpdateText(updateLog.getUpdateText());
            return returnUpdateLog;
        }else {
            ReturnUpdateLog returnUpdateLog = new ReturnUpdateLog(201,"getUpdateLogError","api_getUpdateLog");
            return returnUpdateLog;
        }
    }
}
