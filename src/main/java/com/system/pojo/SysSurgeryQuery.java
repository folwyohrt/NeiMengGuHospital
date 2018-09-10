package com.system.pojo;

import java.util.Date;

/**
 * @Auther: 李景然
 * @Date: 2018/6/12 09:06
 * @Description:
 */
public class SysSurgeryQuery extends PagingRequest {

    private String hId;
    private String pName;
    private Integer hArea;
    private String surgeryDatetime;
    private String hBed;

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

    public Integer gethArea() {
        return hArea;
    }

    public void sethArea(Integer hArea) {
        this.hArea = hArea;
    }

    public String getSurgeryDatetime() {
        return surgeryDatetime;
    }

    public void setSurgeryDatetime(String surgeryDatetime) {
        this.surgeryDatetime = surgeryDatetime;
    }

    public String gethBed() {
        return hBed;
    }

    public void sethBed(String hBed) {
        this.hBed = hBed;
    }
}
