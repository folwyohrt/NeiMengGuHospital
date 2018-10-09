package com.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_bed")
public class SysBed implements Serializable {
    @Id
    @Column(name = "b_id")
    private Integer bId;

    /**
     * 科室（病区）id
     */
    @Column(name = "h_area")
    private Integer hArea;

    /**
     * 床号
     */
    @Column(name = "h_bed")
    private String hBed;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    /**
     * @return b_id
     */
    public Integer getbId() {
        return bId;
    }

    /**
     * @param bId
     */
    public void setbId(Integer bId) {
        this.bId = bId;
    }

    /**
     * 获取科室（病区）id
     *
     * @return h_area - 科室（病区）id
     */
    public Integer gethArea() {
        return hArea;
    }

    /**
     * 设置科室（病区）id
     *
     * @param hArea 科室（病区）id
     */
    public void sethArea(Integer hArea) {
        this.hArea = hArea;
    }

    /**
     * 获取床号
     *
     * @return h_bed - 床号
     */
    public String gethBed() {
        return hBed;
    }

    /**
     * 设置床号
     *
     * @param hBed 床号
     */
    public void sethBed(String hBed) {
        this.hBed = hBed == null ? null : hBed.trim();
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取最后修改时间
     *
     * @return gmt_modified - 最后修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置最后修改时间
     *
     * @param gmtModified 最后修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysBed other = (SysBed) that;
        return (this.getbId() == null ? other.getbId() == null : this.getbId().equals(other.getbId()))
            && (this.gethArea() == null ? other.gethArea() == null : this.gethArea().equals(other.gethArea()))
            && (this.gethBed() == null ? other.gethBed() == null : this.gethBed().equals(other.gethBed()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getbId() == null) ? 0 : getbId().hashCode());
        result = prime * result + ((gethArea() == null) ? 0 : gethArea().hashCode());
        result = prime * result + ((gethBed() == null) ? 0 : gethBed().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bId=").append(bId);
        sb.append(", hArea=").append(hArea);
        sb.append(", hBed=").append(hBed);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}