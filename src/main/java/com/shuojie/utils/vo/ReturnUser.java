package com.shuojie.utils.vo;

public class ReturnUser extends Result{

    private Integer id;//id

    private String mobile;//手机号

    private Integer firmId;//公司id

    private String username;//姓名

    private String idNumber;//身份证号

    private String affiliationFirm;//所属企业

    private String position;//职位

    private String areaname;//所属地区

    public ReturnUser(int code, String message) {
        super(code, message);
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
}
