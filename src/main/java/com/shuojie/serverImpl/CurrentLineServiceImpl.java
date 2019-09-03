package com.shuojie.serverImpl;

import com.shuojie.dao.CurrentLineMapper;
import com.shuojie.domain.CurrentLine;
import com.shuojie.service.CurrentLineService;
import com.shuojie.utils.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service("currentLineServiceImpl")
public class CurrentLineServiceImpl implements CurrentLineService {
    @Resource
    private CurrentLineMapper currentLineMapper;
    private Result result;
    @Override
    public Result insertCurrentLine(CurrentLine currentLine) {
        currentLineMapper.insertCurrentLine(currentLine);
        BigDecimal cuLiLatitude = currentLine.getCuLiLatitude();
        BigDecimal cuLiLongitude = currentLine.getCuLiLongitude();
        if (!cuLiLatitude.equals(null)
                && !cuLiLongitude.equals(null)){
            result = new Result(200,"insertLineSuccess","insertCurrentLine");
        }else {
            result = new Result(201,"insertLineError","insertCurrentLine");
        }
        return result;
    }
}
