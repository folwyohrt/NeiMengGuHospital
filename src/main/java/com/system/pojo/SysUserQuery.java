package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/6/8 16:46
 * @Description:
 */
public class SysUserQuery {
    public SysUserQuery(){}

    public SysUserQuery(String name){
        this.userName =name;
    }

    private String userName;
    private String userPwd;

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

}
