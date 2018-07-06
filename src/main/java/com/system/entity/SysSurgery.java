package com.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_surgery")
public class SysSurgery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 住院号
     */
    @Column(name = "h_id")
    private String hId;

    /**
     * 住院次数
     */
    @Column(name = "h_times")
    private Integer hTimes;

    /**
     * 姓名
     */
    @Column(name = "p_name")
    private String pName;

    /**
     * 性别
     */
    @Column(name = "p_sex")
    private String pSex;

    /**
     * 年龄
     */
    @Column(name = "p_age")
    private String pAge;

    /**
     * 病区
     */
    @Column(name = "h_area")
    private Integer hArea;

    /**
     * 床号
     */
    @Column(name = "h_bed")
    private String hBed;

    /**
     * 陪人数
     */
    @Column(name = "escorts_num")
    private Integer escortsNum;

    /**
     * 访视状态
     */
    @Column(name = "visit_status")
    private String visitStatus;

    /**
     * 手术类型
     */
    @Column(name = "surgery_status")
    private Integer surgeryStatus;

    /**
     * 手术时间
     */
    @Column(name = "surgery_datetime")
    private Date surgeryDatetime;

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

    /**
     * 术前诊断
     */
    @Column(name = "surgery_podx")
    private String surgeryPodx;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取住院号
     *
     * @return h_id - 住院号
     */
    public String gethId() {
        return hId;
    }

    /**
     * 设置住院号
     *
     * @param hId 住院号
     */
    public void sethId(String hId) {
        this.hId = hId == null ? null : hId.trim();
    }

    /**
     * 获取住院次数
     *
     * @return h_times - 住院次数
     */
    public Integer gethTimes() {
        return hTimes;
    }

    /**
     * 设置住院次数
     *
     * @param hTimes 住院次数
     */
    public void sethTimes(Integer hTimes) {
        this.hTimes = hTimes;
    }

    /**
     * 获取姓名
     *
     * @return p_name - 姓名
     */
    public String getpName() {
        return pName;
    }

    /**
     * 设置姓名
     *
     * @param pName 姓名
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * 获取性别
     *
     * @return p_sex - 性别
     */
    public String getpSex() {
        return pSex;
    }

    /**
     * 设置性别
     *
     * @param pSex 性别
     */
    public void setpSex(String pSex) {
        this.pSex = pSex == null ? null : pSex.trim();
    }

    /**
     * 获取年龄
     *
     * @return p_age - 年龄
     */
    public String getpAge() {
        return pAge;
    }

    /**
     * 设置年龄
     *
     * @param pAge 年龄
     */
    public void setpAge(String pAge) {
        this.pAge = pAge == null ? null : pAge.trim();
    }

    /**
     * 获取病区
     *
     * @return h_area - 病区
     */
    public Integer gethArea() {
        return hArea;
    }

    /**
     * 设置病区
     *
     * @param hArea 病区
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
     * 获取陪人数
     *
     * @return escorts_num - 陪人数
     */
    public Integer getEscortsNum() {
        return escortsNum;
    }

    /**
     * 设置陪人数
     *
     * @param escortsNum 陪人数
     */
    public void setEscortsNum(Integer escortsNum) {
        this.escortsNum = escortsNum;
    }

    /**
     * 获取访视状态
     *
     * @return visit_status - 访视状态
     */
    public String getVisitStatus() {
        return visitStatus;
    }

    /**
     * 设置访视状态
     *
     * @param visitStatus 访视状态
     */
    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus == null ? null : visitStatus.trim();
    }

    /**
     * 获取手术类型
     *
     * @return surgery_status - 手术类型
     */
    public Integer getSurgeryStatus() {
        return surgeryStatus;
    }

    /**
     * 设置手术类型
     *
     * @param surgeryStatus 手术类型
     */
    public void setSurgeryStatus(Integer surgeryStatus) {
        this.surgeryStatus = surgeryStatus;
    }

    /**
     * 获取手术时间
     *
     * @return surgery_datetime - 手术时间
     */
    public Date getSurgeryDatetime() {
        return surgeryDatetime;
    }

    /**
     * 设置手术时间
     *
     * @param surgeryDatetime 手术时间
     */
    public void setSurgeryDatetime(Date surgeryDatetime) {
        this.surgeryDatetime = surgeryDatetime;
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

    /**
     * 获取术前诊断
     *
     * @return surgery_podx - 术前诊断
     */
    public String getSurgeryPodx() {
        return surgeryPodx;
    }

    /**
     * 设置术前诊断
     *
     * @param surgeryPodx 术前诊断
     */
    public void setSurgeryPodx(String surgeryPodx) {
        this.surgeryPodx = surgeryPodx == null ? null : surgeryPodx.trim();
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
        SysSurgery other = (SysSurgery) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.gethId() == null ? other.gethId() == null : this.gethId().equals(other.gethId()))
            && (this.gethTimes() == null ? other.gethTimes() == null : this.gethTimes().equals(other.gethTimes()))
            && (this.getpName() == null ? other.getpName() == null : this.getpName().equals(other.getpName()))
            && (this.getpSex() == null ? other.getpSex() == null : this.getpSex().equals(other.getpSex()))
            && (this.getpAge() == null ? other.getpAge() == null : this.getpAge().equals(other.getpAge()))
            && (this.gethArea() == null ? other.gethArea() == null : this.gethArea().equals(other.gethArea()))
            && (this.gethBed() == null ? other.gethBed() == null : this.gethBed().equals(other.gethBed()))
            && (this.getEscortsNum() == null ? other.getEscortsNum() == null : this.getEscortsNum().equals(other.getEscortsNum()))
            && (this.getVisitStatus() == null ? other.getVisitStatus() == null : this.getVisitStatus().equals(other.getVisitStatus()))
            && (this.getSurgeryStatus() == null ? other.getSurgeryStatus() == null : this.getSurgeryStatus().equals(other.getSurgeryStatus()))
            && (this.getSurgeryDatetime() == null ? other.getSurgeryDatetime() == null : this.getSurgeryDatetime().equals(other.getSurgeryDatetime()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getSurgeryPodx() == null ? other.getSurgeryPodx() == null : this.getSurgeryPodx().equals(other.getSurgeryPodx()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((gethId() == null) ? 0 : gethId().hashCode());
        result = prime * result + ((gethTimes() == null) ? 0 : gethTimes().hashCode());
        result = prime * result + ((getpName() == null) ? 0 : getpName().hashCode());
        result = prime * result + ((getpSex() == null) ? 0 : getpSex().hashCode());
        result = prime * result + ((getpAge() == null) ? 0 : getpAge().hashCode());
        result = prime * result + ((gethArea() == null) ? 0 : gethArea().hashCode());
        result = prime * result + ((gethBed() == null) ? 0 : gethBed().hashCode());
        result = prime * result + ((getEscortsNum() == null) ? 0 : getEscortsNum().hashCode());
        result = prime * result + ((getVisitStatus() == null) ? 0 : getVisitStatus().hashCode());
        result = prime * result + ((getSurgeryStatus() == null) ? 0 : getSurgeryStatus().hashCode());
        result = prime * result + ((getSurgeryDatetime() == null) ? 0 : getSurgeryDatetime().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getSurgeryPodx() == null) ? 0 : getSurgeryPodx().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hId=").append(hId);
        sb.append(", hTimes=").append(hTimes);
        sb.append(", pName=").append(pName);
        sb.append(", pSex=").append(pSex);
        sb.append(", pAge=").append(pAge);
        sb.append(", hArea=").append(hArea);
        sb.append(", hBed=").append(hBed);
        sb.append(", escortsNum=").append(escortsNum);
        sb.append(", visitStatus=").append(visitStatus);
        sb.append(", surgeryStatus=").append(surgeryStatus);
        sb.append(", surgeryDatetime=").append(surgeryDatetime);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", surgeryPodx=").append(surgeryPodx);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}