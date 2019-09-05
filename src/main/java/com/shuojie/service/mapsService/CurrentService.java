package com.shuojie.service.mapsService;

import com.shuojie.domain.maps.Current;
import com.shuojie.utils.vo.Result;

public interface CurrentService {

    //添加当前线路
    Result insertCurrent(Current current);

}
