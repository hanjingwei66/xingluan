package com.shuojie.service.sensorService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shuojie.domain.sensorModle.SensorTitle;
import com.shuojie.utils.vo.Result;

import java.util.List;

public interface SensorService {
    Result selectPage(Page<SensorTitle> page, String startTime, String endTime);
}
