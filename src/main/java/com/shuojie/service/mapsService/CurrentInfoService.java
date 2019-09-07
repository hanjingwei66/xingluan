package com.shuojie.service.mapsService;

import com.shuojie.domain.maps.CurrentInfo;
import com.shuojie.utils.vo.Result;

public interface CurrentInfoService {

    //添加当前线路
    Result insertCurrentInfo(CurrentInfo current);

}
