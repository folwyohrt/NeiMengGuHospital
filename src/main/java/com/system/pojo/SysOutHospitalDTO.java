package com.system.pojo;

import java.util.Date;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:49
 * @Description:
 */
public class SysOutHospitalDTO {
    private Long id;
    private String hId;
    private String pName;
    private String pSex;
    private String pBirthday;
    private String pAge;
    private String hOutDate;
    private String hReckoningDate;
    private Integer hArea;
    private String hAreaStr;
    private Integer hDays;
    private Integer hTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSex() {
        return pSex;
    }

    public void setpSex(String pSex) {
        this.pSex = pSex;
    }

    public String getpBirthday() {
        return pBirthday;
    }

    public void setpBirthday(String pBirthday) {
        this.pBirthday = pBirthday;
    }

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
        this.pAge = pAge;
    }

    public String gethOutDate() {
        return hOutDate;
    }

    public void sethOutDate(String hOutDate) {
        this.hOutDate = hOutDate;
    }

    public String gethReckoningDate() {
        return hReckoningDate;
    }

    public void sethReckoningDate(String hReckoningDate) {
        this.hReckoningDate = hReckoningDate;
    }

    public Integer gethArea() {
        return hArea;
    }

    public void sethArea(Integer hArea) {
        this.hArea = hArea;
    }

    public String gethAreaStr() {
        return hAreaStr;
    }

    public void sethAreaStr(String hAreaStr) {
        this.hAreaStr = hAreaStr;
    }

    public Integer gethDays() {
        return hDays;
    }

    public void sethDays(Integer hDays) {
        this.hDays = hDays;
    }

    public Integer gethTimes() {
        return hTimes;
    }

    public void sethTimes(Integer hTimes) {
        this.hTimes = hTimes;
    }
}
