/*
*
* RolePermissionKey.java
* 
* @date 2019-08-17
*/
package com.shuojie.domain;

public class RolePermissionKey {
    /**
     * 
     */
    private Integer permissionId;

    /**
     * 
     */
    private Integer roleId;

    /**
     * 
     * @return permission_id 
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 
     * @param permissionId 
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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