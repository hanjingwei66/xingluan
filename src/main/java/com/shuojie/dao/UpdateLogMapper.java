package com.shuojie.dao;

import com.shuojie.domain.UpdateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateLogMapper {

    UpdateLog getUpdateLog();
}