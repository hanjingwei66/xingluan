package com.shuojie.serverImpl.sensorServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shuojie.dao.sensorMappers.SensorMapper;
import com.shuojie.domain.sensorModle.SensorTitle;
import com.shuojie.service.sensorService.SensorAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncServiceImpl implements SensorAsyncService {
    @Autowired
    private SensorMapper sensorMapper;

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(SensorTitle sensor) {
        sensorMapper.insert(sensor);
    }


}
