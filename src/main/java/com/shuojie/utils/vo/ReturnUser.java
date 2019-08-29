package com.shuojie.utils.vo;

import com.shuojie.domain.UserFirm;
import lombok.Data;

@Data
public class ReturnUser extends Result{

    private Integer id;//id

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

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getFirmUserId() {
        return firmUserId;
    }

    public void setFirmUserId(Integer firmUserId) {
        this.firmUserId = firmUserId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserFirm getUserFirm() {
        return userFirm;
    }

    public void setUserFirm(UserFirm userFirm) {
        this.userFirm = userFirm;
    }

    public ReturnUser(int code, String message,String command) {
        super(code, message,command);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAffiliationFirm() {
        return affiliationFirm;
    }

    public void setAffiliationFirm(String affiliationFirm) {
        this.affiliationFirm = affiliationFirm;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
