package com.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_out_hospital")
public class SysOutHospital implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 住院号
     */
    @Column(name = "h_id")
    private String hId;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "p_sex")
    private String pSex;

    /**
     * 出生日期
     */
    @Column(name = "p_birthday")
    private Date pBirthday;

    @Column(name = "p_age")
    private String pAge;

    /**
     * 离院日期
     */
    @Column(name = "h_out_date")
    private Date hOutDate;

    /**
     * 结账日期
     */
    @Column(name = "h_reckoning_date")
    private Date hReckoningDate;

    /**
     * 病区
     */
    @Column(name = "h_area")
    private Integer hArea;

    /**
     * 住院天数
     */
    @Column(name = "h_days")
    private Integer hDays;

    /**
     * 住院次数
     */
    @Column(name = "h_times")
    private Integer hTimes;

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
     * @return p_name
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * @return p_sex
     */
    public String getpSex() {
        return pSex;
    }

    /**
     * @param pSex
     */
    public void setpSex(String pSex) {
        this.pSex = pSex == null ? null : pSex.trim();
    }

    /**
     * 获取出生日期
     *
     * @return p_birthday - 出生日期
     */
    public Date getpBirthday() {
        return pBirthday;
    }

    /**
     * 设置出生日期
     *
     * @param pBirthday 出生日期
     */
    public void setpBirthday(Date pBirthday) {
        this.pBirthday = pBirthday;
    }

    /**
     * @return p_age
     */
    public String getpAge() {
        return pAge;
    }

    /**
     * @param pAge
     */
    public void setpAge(String pAge) {
        this.pAge = pAge == null ? null : pAge.trim();
    }

    /**
     * 获取离院日期
     *
     * @return h_out_date - 离院日期
     */
    public Date gethOutDate() {
        return hOutDate;
    }

    /**
     * 设置离院日期
     *
     * @param hOutDate 离院日期
     */
    public void sethOutDate(Date hOutDate) {
        this.hOutDate = hOutDate;
    }

    /**
     * 获取结账日期
     *
     * @return h_reckoning_date - 结账日期
     */
    public Date gethReckoningDate() {
        return hReckoningDate;
    }

    /**
     * 设置结账日期
     *
     * @param hReckoningDate 结账日期
     */
    public void sethReckoningDate(Date hReckoningDate) {
        this.hReckoningDate = hReckoningDate;
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
     * 获取住院天数
     *
     * @return h_days - 住院天数
     */
    public Integer gethDays() {
        return hDays;
    }

    /**
     * 设置住院天数
     *
     * @param hDays 住院天数
     */
    public void sethDays(Integer hDays) {
        this.hDays = hDays;
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
        SysOutHospital other = (SysOutHospital) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.gethId() == null ? other.gethId() == null : this.gethId().equals(other.gethId()))
            && (this.getpName() == null ? other.getpName() == null : this.getpName().equals(other.getpName()))
            && (this.getpSex() == null ? other.getpSex() == null : this.getpSex().equals(other.getpSex()))
            && (this.getpBirthday() == null ? other.getpBirthday() == null : this.getpBirthday().equals(other.getpBirthday()))
            && (this.getpAge() == null ? other.getpAge() == null : this.getpAge().equals(other.getpAge()))
            && (this.gethOutDate() == null ? other.gethOutDate() == null : this.gethOutDate().equals(other.gethOutDate()))
            && (this.gethReckoningDate() == null ? other.gethReckoningDate() == null : this.gethReckoningDate().equals(other.gethReckoningDate()))
            && (this.gethArea() == null ? other.gethArea() == null : this.gethArea().equals(other.gethArea()))
            && (this.gethDays() == null ? other.gethDays() == null : this.gethDays().equals(other.gethDays()))
            && (this.gethTimes() == null ? other.gethTimes() == null : this.gethTimes().equals(other.gethTimes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((gethId() == null) ? 0 : gethId().hashCode());
        result = prime * result + ((getpName() == null) ? 0 : getpName().hashCode());
        result = prime * result + ((getpSex() == null) ? 0 : getpSex().hashCode());
        result = prime * result + ((getpBirthday() == null) ? 0 : getpBirthday().hashCode());
        result = prime * result + ((getpAge() == null) ? 0 : getpAge().hashCode());
        result = prime * result + ((gethOutDate() == null) ? 0 : gethOutDate().hashCode());
        result = prime * result + ((gethReckoningDate() == null) ? 0 : gethReckoningDate().hashCode());
        result = prime * result + ((gethArea() == null) ? 0 : gethArea().hashCode());
        result = prime * result + ((gethDays() == null) ? 0 : gethDays().hashCode());
        result = prime * result + ((gethTimes() == null) ? 0 : gethTimes().hashCode());
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
        sb.append(", pName=").append(pName);
        sb.append(", pSex=").append(pSex);
        sb.append(", pBirthday=").append(pBirthday);
        sb.append(", pAge=").append(pAge);
        sb.append(", hOutDate=").append(hOutDate);
        sb.append(", hReckoningDate=").append(hReckoningDate);
        sb.append(", hArea=").append(hArea);
        sb.append(", hDays=").append(hDays);
        sb.append(", hTimes=").append(hTimes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}