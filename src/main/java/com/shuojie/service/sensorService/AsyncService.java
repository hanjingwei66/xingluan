package com.shuojie.service.sensorService;

import com.shuojie.domain.sensorModle.SensorTitle;

public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(SensorTitle sensor);
}
