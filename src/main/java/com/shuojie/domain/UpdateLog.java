/*
*
* UpdateLog.java
* 
* @date 2019-08-26
*/
package com.shuojie.domain;

public class UpdateLog {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String updateName;

    /**
     * 
     */
    private String updateText;

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
     * @return update_name 
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 
     * @param updateName 
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    /**
     * 
     * @return update_text 
     */
    public String getUpdateText() {
        return updateText;
    }

    /**
     * 
     * @param updateText 
     */
    public void setUpdateText(String updateText) {
        this.updateText = updateText == null ? null : updateText.trim();
    }
}