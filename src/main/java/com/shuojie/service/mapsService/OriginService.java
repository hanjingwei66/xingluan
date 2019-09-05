package com.shuojie.service.mapsService;

import com.shuojie.domain.maps.Origin;
import com.shuojie.utils.vo.ReturnOrigin;

import java.util.ArrayList;
import java.util.List;

public interface OriginService {

    //根据orName查询经纬度
    ReturnOrigin getByName(Origin origin);

    ArrayList getOriginName();

    List<Origin> getOriginLine();
}
