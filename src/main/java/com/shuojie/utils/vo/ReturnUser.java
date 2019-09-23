package com.shuojie.utils.vo;

import com.shuojie.domain.UserFirm;
import lombok.Data;

@Data
public class ReturnUser extends Result{

    private Long id;//id

    private String mobile;//手机号

    private Integer firmId;//公司id

    private String username;//姓名

    private String idNumber;//身份证号

    private String affiliationFirm;//所属企业

    private String position;//职位

    private String areaname;//所属地区

    private String sex;//性别

    private UserFirm userFirm;//企业关联表

    private Integer firmUserId;//企业用户id

    private Integer loginFlag;//登陆状态

    private String token;


    public ReturnUser(int code, String message,String command) {
        super(code, message,command);
    }

}
