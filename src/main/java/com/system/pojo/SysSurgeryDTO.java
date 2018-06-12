package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:29
 * @Description:
 */
public class SysSurgeryDTO {

    private Long id;
    private String hId;
    private String pName;
    private String pSex;
    private Integer pAge;
    //病区key
    private Integer hArea;
    //病区value
    private String hAreaStr;
    private String hBed;
    private Integer escortsNum;
    private String visitStatus;
    private String surgeryStatus;
    private String surgeryDatetime;
    private String surgeryPodx;

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

    public String gethAreaStr() {
        return hAreaStr;
    }

    public void sethAreaStr(String hAreaStr) {
        this.hAreaStr = hAreaStr;
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

    public String getSurgeryStatus() {
        return surgeryStatus;
    }

    public void setSurgeryStatus(String surgeryStatus) {
        this.surgeryStatus = surgeryStatus;
    }

    public String getSurgeryDatetime() {
        return surgeryDatetime;
    }

    public void setSurgeryDatetime(String surgeryDatetime) {
        this.surgeryDatetime = surgeryDatetime;
    }

    public String getSurgeryPodx() {
        return surgeryPodx;
    }

    public void setSurgeryPodx(String surgeryPodx) {
        this.surgeryPodx = surgeryPodx;
    }
}
