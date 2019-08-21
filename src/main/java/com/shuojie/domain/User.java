/*
*
* User.java
* 
* @date 2019-08-17
*/
package com.shuojie.domain;

public class User {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String mobile;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Integer firmId;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String idNumber;

    /**
     * 
     */
    private String affiliationFirm;

    /**
     * 
     */
    private String position;

    /**
     * 
     */
    private String areaname;

    /**
     * 
     */
    private Integer roleId;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return mobile 
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile 
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 
     * @return password 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 
     * @return firm_id 
     */
    public Integer getFirmId() {
        return firmId;
    }

    /**
     * 
     * @param firmId 
     */
    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    /**
     * 
     * @return username 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 
     * @return id_number 
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 
     * @param idNumber 
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    /**
     * 
     * @return affiliation_firm 
     */
    public String getAffiliationFirm() {
        return affiliationFirm;
    }

    /**
     * 
     * @param affiliationFirm 
     */
    public void setAffiliationFirm(String affiliationFirm) {
        this.affiliationFirm = affiliationFirm == null ? null : affiliationFirm.trim();
    }

    /**
     * 
     * @return position 
     */
    public String getPosition() {
        return position;
    }

    /**
     * 
     * @param position 
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 
     * @return areaname 
     */
    public String getAreaname() {
        return areaname;
    }

    /**
     * 
     * @param areaname 
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    /**
     * 
     * @return role_id 
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 
     * @param roleId 
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}