package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/7/24 22:28
 * @Description:
 */
public class SysOutHospitalQuery extends PagingRequest {
    private String hId;
    private String pName;
    private Integer hArea;
    private String hOutDate;

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

    public String gethOutDate() {
        return hOutDate;
    }

    public void sethOutDate(String hOutDate) {
        this.hOutDate = hOutDate;
    }
}
