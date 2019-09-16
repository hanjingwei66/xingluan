package com.shuojie.dao;

import com.shuojie.domain.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper {

    //添加模型信息
    void insertModel(Model model);

    //查询模型信息
    List<Model> getModel();
}