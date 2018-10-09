package com.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_hospitalization")
public class SysHospitalization implements Serializable {
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
     * 住院日期
     */
    @Column(name = "h_date")
    private Date hDate;

    /**
     * 患者状态
     */
    @Column(name = "p_status")
    private Integer pStatus;

    /**
     * 医保类型
     */
    @Column(name = "p_insur")
    private Integer pInsur;

    /**
     * 责任医生
     */
    @Column(name = "dcr_name")
    private String dcrName;

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

    @Column(name = "nursing_level")
    private Integer nursingLevel;

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
     * 获取住院日期
     *
     * @return h_date - 住院日期
     */
    public Date gethDate() {
        return hDate;
    }

    /**
     * 设置住院日期
     *
     * @param hDate 住院日期
     */
    public void sethDate(Date hDate) {
        this.hDate = hDate;
    }

    /**
     * 获取患者状态
     *
     * @return p_status - 患者状态
     */
    public Integer getpStatus() {
        return pStatus;
    }

    /**
     * 设置患者状态
     *
     * @param pStatus 患者状态
     */
    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    /**
     * 获取医保类型
     *
     * @return p_insur - 医保类型
     */
    public Integer getpInsur() {
        return pInsur;
    }

    /**
     * 设置医保类型
     *
     * @param pInsur 医保类型
     */
    public void setpInsur(Integer pInsur) {
        this.pInsur = pInsur;
    }

    /**
     * 获取责任医生
     *
     * @return dcr_name - 责任医生
     */
    public String getDcrName() {
        return dcrName;
    }

    /**
     * 设置责任医生
     *
     * @param dcrName 责任医生
     */
    public void setDcrName(String dcrName) {
        this.dcrName = dcrName == null ? null : dcrName.trim();
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
     * @return nursing_level
     */
    public Integer getNursingLevel() {
        return nursingLevel;
    }

    /**
     * @param nursingLevel
     */
    public void setNursingLevel(Integer nursingLevel) {
        this.nursingLevel = nursingLevel;
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
        SysHospitalization other = (SysHospitalization) that;
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
            && (this.gethDate() == null ? other.gethDate() == null : this.gethDate().equals(other.gethDate()))
            && (this.getpStatus() == null ? other.getpStatus() == null : this.getpStatus().equals(other.getpStatus()))
            && (this.getpInsur() == null ? other.getpInsur() == null : this.getpInsur().equals(other.getpInsur()))
            && (this.getDcrName() == null ? other.getDcrName() == null : this.getDcrName().equals(other.getDcrName()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getNursingLevel() == null ? other.getNursingLevel() == null : this.getNursingLevel().equals(other.getNursingLevel()));
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
        result = prime * result + ((gethDate() == null) ? 0 : gethDate().hashCode());
        result = prime * result + ((getpStatus() == null) ? 0 : getpStatus().hashCode());
        result = prime * result + ((getpInsur() == null) ? 0 : getpInsur().hashCode());
        result = prime * result + ((getDcrName() == null) ? 0 : getDcrName().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getNursingLevel() == null) ? 0 : getNursingLevel().hashCode());
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
        sb.append(", hDate=").append(hDate);
        sb.append(", pStatus=").append(pStatus);
        sb.append(", pInsur=").append(pInsur);
        sb.append(", dcrName=").append(dcrName);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", nursingLevel=").append(nursingLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}