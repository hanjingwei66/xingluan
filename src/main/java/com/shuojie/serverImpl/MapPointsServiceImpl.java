package com.shuojie.serverImpl;

import com.shuojie.dao.MapPointsMapper;
import com.shuojie.domain.MapPoints;
import com.shuojie.service.MapPointsService;
import com.shuojie.utils.vo.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service("mapPointsServiceImpl")
public class MapPointsServiceImpl implements MapPointsService {
    @Resource
    private MapPointsMapper mapPointsMapper;

    private Result result;


    @Override
    public Result insertMapPoints(MapPoints mapPoints) {
        try {
            mapPointsMapper.insertMapPoints(mapPoints);
            BigDecimal maLatitude = mapPoints.getMaLatitude();
            BigDecimal maLongitude = mapPoints.getMaLongitude();
            String pointsName = mapPoints.getPointsName();
            if (!maLatitude.equals(null)
                    && !maLongitude.equals(null)
                    && !StringUtil.isNullOrEmpty(pointsName)){
               result = new Result(200,"insertMapPointsSuccess","insertMapPoints");
           }
        } catch (Exception e) {
            result = new Result(201,"insertMapPointsError","insertMapPoints");
        }

       return result;
    }
}
