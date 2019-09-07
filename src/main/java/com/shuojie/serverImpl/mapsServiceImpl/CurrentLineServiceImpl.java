package com.shuojie.serverImpl.mapsServiceImpl;

import com.shuojie.dao.mapsMapper.CurrentLineMapper;
import com.shuojie.domain.maps.CurrentLine;
import com.shuojie.service.mapsService.CurrentLineService;
import com.shuojie.utils.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("currentLineServiceImpl")
public class CurrentLineServiceImpl implements CurrentLineService {
    @Resource
    private CurrentLineMapper currentLineMapper;
    private Result result;
    @Override
    public Result insertCurrentLine(CurrentLine currentLine) {
        currentLineMapper.insertCurrentLine(currentLine);
        if (currentLine.getCuid() != null
                && currentLine.getCuLiLatitude() != null
                        &&currentLine.getCuLiLongitude() != null){
            result = new Result(200,"insertLineSuccess","insertCurrentLine");
        }else {
            result = new Result(201,"insertLineError","insertCurrentLine");
        }
        return result;
    }
}
