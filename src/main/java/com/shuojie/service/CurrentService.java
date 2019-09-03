package com.shuojie.service;

import com.shuojie.domain.Current;
import com.shuojie.utils.vo.Result;

public interface CurrentService {

    //添加当前线路
    Result insertCurrent(Current current);

}
