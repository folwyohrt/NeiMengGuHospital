package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:51
 * @Description:
 */
public class SysHospitalizationQuery extends PagingRequest {
    //病区
    private Integer hArea;
    //住院号
    private String hId;
    private String pName;
    //住院时间
    private String hDate;

    public Integer gethArea() {
        return hArea;
    }

    public void sethArea(Integer hArea) {
        this.hArea = hArea;
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

    public String gethDate() {
        return hDate;
    }

    public void sethDate(String hDate) {
        this.hDate = hDate;
    }
}
