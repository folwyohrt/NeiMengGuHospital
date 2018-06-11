package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Auther: 李景然
 * @Date: 2018/6/8 16:38
 * @Description:
 */
public class SysUserDTO {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userRole;
    private String userGroup;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
