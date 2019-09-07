package com.shuojie.serverImpl;

import com.shuojie.dao.ModelMapper;
import com.shuojie.domain.Model;
import com.shuojie.service.ModelService;
import com.shuojie.utils.vo.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service("modelServiceImpl")
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelMapper modelMapper;
    private Result result;

    @Override
    public Result insertModel(Model model) {
            modelMapper.insertModel(model);
            if (!StringUtil.isNullOrEmpty(model.getModelName())
                    && model.getMoLatitude() != null
                    && model.getMoLongitude() != null
                    && !StringUtil.isNullOrEmpty(model.getDirection())){
                 result = new Result(200,"insertModelSuccess","insertModel");
            }else {
                 result = new Result(201,"insertModelError","insertModel");
            }
                return result;
    }

    @Override
    public List<Model> getModel() {
        return modelMapper.getModel();
    }
}
