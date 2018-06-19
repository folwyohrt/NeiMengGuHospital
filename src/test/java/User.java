import java.util.Date;

/**
 * @Auther: 李景然
 * @Date: 2018/6/12 16:42
 * @Description:
 */
public class User {
    private Integer userId;
    private String userName;
    private String userPwd;
    private Date surgeryDatetime;

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

    public Date getSurgeryDatetime() {
        return surgeryDatetime;
    }

    public void setSurgeryDatetime(Date surgeryDatetime) {
        this.surgeryDatetime = surgeryDatetime;
    }
}
