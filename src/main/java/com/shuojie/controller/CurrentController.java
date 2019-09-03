package com.shuojie.controller;

import com.shuojie.domain.Current;
import com.shuojie.service.CurrentService;
import com.shuojie.utils.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/current")
public class CurrentController {
    @Resource(name = "currentServiceImpl")
    private CurrentService currentServer;

    //添加当前线路
    @RequestMapping(value = "/insertCurrent",method = RequestMethod.POST)
    public Result insertCurrent(@RequestBody Current current){
         Result result = currentServer.insertCurrent(current);
        return result;
    }
}
