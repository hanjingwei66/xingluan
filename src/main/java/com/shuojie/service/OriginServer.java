package com.shuojie.service;

import com.shuojie.domain.Origin;
import com.shuojie.utils.vo.ReturnOrigin;

import java.util.ArrayList;
import java.util.List;

public interface OriginServer {

    //根据orName查询经纬度
    ReturnOrigin getByName(Origin origin);

    ArrayList getOriginName();

    List<Origin> getOriginLine();
}
