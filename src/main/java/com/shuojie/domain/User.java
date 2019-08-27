package com.shuojie.domain;

import lombok.Data;

import java.util.List;
@Data
public class User {
    private Integer id;

    private String mobile;//手机号

    private String number;//版本

    private String command;//用于前台指令标识

    private String oldPassword;//前台传过来的密码
    private String password;

    private Integer firmId;//企业id

    private String username;//姓名

    private String idNumber;//身份证号

    private String affiliationFirm;//归属企业

    private String position;//职位

    private String areaname;//所属地区

    private String sex;//性别
    
    private String yzm;//验证码

    private String contact;//联系我们内容

    private UserFirm userFirm;//企业关联表


}