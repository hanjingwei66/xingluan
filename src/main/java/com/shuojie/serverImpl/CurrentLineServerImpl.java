package com.shuojie.serverImpl;

import com.shuojie.dao.CurrentLineMapper;
import com.shuojie.domain.CurrentLine;
import com.shuojie.service.CurrentLineServer;
import com.shuojie.utils.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("currentLineServerImpl")
public class CurrentLineServerImpl implements CurrentLineServer {
    @Resource
    private CurrentLineMapper currentLineMapper;
    private Result result;
    @Override
    public Result insertCurrentLine(CurrentLine currentLine) {
        currentLineMapper.insertCurrentLine(currentLine);
        if (currentLine != null){
            result = new Result(200,"insertLineSuccess","insertCurrentLine");
        }else {
            result = new Result(201,"insertLineError","insertCurrentLine");
        }
        return result;
    }
}
