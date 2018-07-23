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
    private String pAge;
    private Integer hTimes;

    private Integer hArea;
    private String hAreaStr;

    private String hBed;
    private Integer escortsNum;
    private String visitStatus;
    private String hDate;
//    private String hOutDate;

    private Integer pStatus;
    private String pStatusStr;
    private Integer pInsur;
    private String pInsurStr;

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

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
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

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    public Integer getpInsur() {
        return pInsur;
    }

    public void setpInsur(Integer pInsur) {
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

    public String getpStatusStr() {
        return pStatusStr;
    }

    public void setpStatusStr(String pStatusStr) {
        this.pStatusStr = pStatusStr;
    }

    public String getpInsurStr() {
        return pInsurStr;
    }

    public void setpInsurStr(String pInsurStr) {
        this.pInsurStr = pInsurStr;
    }

    public Integer gethTimes() {
        return hTimes;
    }

    public void sethTimes(Integer hTimes) {
        this.hTimes = hTimes;
    }
//
//    public String gethOutDate() {
//        return hOutDate;
//    }
//
//    public void sethOutDate(String hOutDate) {
//        this.hOutDate = hOutDate;
//    }
}
