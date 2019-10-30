package com.shuojie.utils.vo;

import lombok.Data;

@Data
public class Page {
    private String startTime ;//开始时间
    private String endTime ;//结束时间
    private Integer currentPage ;//当前页
    private Integer pageSize ;//每页数据大小
    private Integer totle;//总记录数
    private Integer totlePage;//总页数
}
