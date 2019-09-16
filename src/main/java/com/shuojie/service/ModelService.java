package com.shuojie.service;

import com.shuojie.domain.Model;
import com.shuojie.utils.vo.Result;

import java.util.List;

public interface ModelService {

    Result insertModel(Model model);

    List<Model> getModel();
}
