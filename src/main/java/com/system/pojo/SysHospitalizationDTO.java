package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:49
 * @Description:
 */
public class SysHospitalizationDTO {
    private Long id;
    private String hId;
    private String pName;
    private String pSex;
    private Integer pAge;

    private Integer hArea;
    private String hAreaStr;

    private String hBed;
    private Integer escortsNum;
    private String visitStatus;
    private String hDate;
    private String pStatus;
    private String pInsur;
    private String dcrName;

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

    public Integer getpAge() {
        return pAge;
    }

    public void setpAge(Integer pAge) {
        this.pAge = pAge;
    }

    public Integer gethArea() {
        return hArea;
    }

    public void sethArea(Integer hArea) {
        this.hArea = hArea;
    }

    public String gethBed() {
        return hBed;
    }

    public void sethBed(String hBed) {
        this.hBed = hBed;
    }

    public Integer getEscortsNum() {
        return escortsNum;
    }

    public void setEscortsNum(Integer escortsNum) {
        this.escortsNum = escortsNum;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

    public String gethDate() {
        return hDate;
    }

    public void sethDate(String hDate) {
        this.hDate = hDate;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getpInsur() {
        return pInsur;
    }

    public void setpInsur(String pInsur) {
        this.pInsur = pInsur;
    }

    public String getDcrName() {
        return dcrName;
    }

    public void setDcrName(String dcrName) {
        this.dcrName = dcrName;
    }

    public String gethAreaStr() {
        return hAreaStr;
    }

    public void sethAreaStr(String hAreaStr) {
        this.hAreaStr = hAreaStr;
    }
}
