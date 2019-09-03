package com.shuojie.service;

import com.shuojie.domain.Current;
import com.shuojie.utils.vo.Result;

public interface CurrentServer {

    //添加当前线路
    Result insertCurrent(Current current);

}
