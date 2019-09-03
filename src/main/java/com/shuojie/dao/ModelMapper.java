package com.shuojie.dao;

import com.shuojie.domain.Model;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModelMapper {

    //添加模型信息
    void insertModel(Model model);
}