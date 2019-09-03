package com.shuojie.controller;

import com.shuojie.domain.Origin;
import com.shuojie.service.OriginService;
import com.shuojie.utils.vo.ReturnOrigin;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/origin")
public class OriginController {
    @Resource(name = "originServiceImpl")
    private OriginService originServer;
    //根据orName查询经纬度
    @RequestMapping(value = "/getByName",method = RequestMethod.POST)
    public ReturnOrigin getByName(@RequestBody Origin origin){
        ReturnOrigin oa = originServer.getByName(origin);
        return oa;
    }
    @RequestMapping(value = "/getOriginName",method = RequestMethod.GET)
    public ArrayList getOriginName(){
        return originServer.getOriginName();
    }
    @RequestMapping(value = "/getOriginLine",method = RequestMethod.GET)
    public List<Origin> getOriginLine(){
        return originServer.getOriginLine();
    }
}
