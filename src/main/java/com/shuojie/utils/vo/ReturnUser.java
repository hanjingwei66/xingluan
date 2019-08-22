package com.shuojie.utils.vo;

public class ReturnUser extends Result{

    private Integer id;

    private String mobile;

    private Integer firmId;

    private String username;

    private String idNumber;

    private String affiliationFirm;

    private String position;

    private String areaname;

    public ReturnUser(int code, String message) {
        super(code, message);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
