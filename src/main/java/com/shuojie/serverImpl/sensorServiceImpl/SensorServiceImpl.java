package com.shuojie.serverImpl.sensorServiceImpl;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shuojie.dao.sensorMappers.SensorMapper;
import com.shuojie.domain.sensorModle.SensorTitle;
import com.shuojie.service.sensorService.SensorService;
import com.shuojie.utils.timeUtil.TimeFormateUtil;
import com.shuojie.utils.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SensorServiceImpl implements SensorService {
    @Autowired
    private SensorMapper sensorMapper;
    private Result result;
    @Override
    public Result selectPage(Page<SensorTitle> page, String startTime,String endTime) {
        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            Long startStamp = TimeFormateUtil.timeToStamp(startTime);
            Long endStamp = TimeFormateUtil.timeToStamp(endTime);
            QueryWrapper<SensorTitle> queryWrapper = new QueryWrapper<>();
            queryWrapper.between("time", startStamp, endStamp);
            IPage<SensorTitle> iPage = sensorMapper.selectPage(page, queryWrapper);
            List<SensorTitle> sensorTitleList = iPage.getRecords();
            result = new Result(200, "SUCCESS", "sensor_findLog_bytime", sensorTitleList);
        }
        return result;
    }


}
