package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/6/8 16:46
 * @Description:
 */
public class SysUserQuery {
    public SysUserQuery(){}

    public SysUserQuery(String name){
        this.codeno =name;
    }

    private String codeno;
    private String userPwd;

    public String getCodeno() {
        return codeno;
    }

    public void setCodeno(String codeno) {
        this.codeno = codeno;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

}
