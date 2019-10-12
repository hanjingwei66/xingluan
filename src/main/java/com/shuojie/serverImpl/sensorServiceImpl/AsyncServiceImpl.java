package com.shuojie.serverImpl.sensorServiceImpl;

import com.shuojie.dao.sensorMappers.SensorMapper;
import com.shuojie.domain.sensorModle.SensorTitle;
import com.shuojie.service.sensorService.AsyncService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private SensorMapper sensorMapper;

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(SensorTitle sensor) {
        sensorMapper.insert(sensor);
    }
}
