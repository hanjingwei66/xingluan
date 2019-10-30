package com.shuojie.service.sensorService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shuojie.domain.sensorModle.SensorTitle;

import java.util.List;

public interface SensorAsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(SensorTitle sensor);

}
