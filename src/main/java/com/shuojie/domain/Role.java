/*
*
* Role.java
* 
* @date 2019-08-17
*/
package com.shuojie.domain;

public class Role {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String role;

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
     * @return description 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 
     * @return role 
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @param role 
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}