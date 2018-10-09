package com.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sync_log")
public class SyncLog implements Serializable {
    @Id
    @Column(name = "s_id")
    private Integer sId;

    /**
     * 同步数据的类型，在院(hspt) or 手术(sg) or 用户(user)
     */
    @Column(name = "s_type")
    private String sType;

    /**
     * 最后的同步时间
     */
    @Column(name = "s_starttime")
    private Date sStarttime;

    @Column(name = "s_endtime")
    private Date sEndtime;

    /**
     * 同步成功的标记
     */
    @Column(name = "s_success")
    private Boolean sSuccess;

    @Column(name = "s_count")
    private Long sCount;

    @Column(name = "s_update")
    private Long sUpdate;

    @Column(name = "s_insert")
    private Long sInsert;

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
     * @return s_id
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * @param sId
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    /**
     * 获取同步数据的类型，在院(hspt) or 手术(sg) or 用户(user)
     *
     * @return s_type - 同步数据的类型，在院(hspt) or 手术(sg) or 用户(user)
     */
    public String getsType() {
        return sType;
    }

    /**
     * 设置同步数据的类型，在院(hspt) or 手术(sg) or 用户(user)
     *
     * @param sType 同步数据的类型，在院(hspt) or 手术(sg) or 用户(user)
     */
    public void setsType(String sType) {
        this.sType = sType == null ? null : sType.trim();
    }

    /**
     * 获取最后的同步时间
     *
     * @return s_starttime - 最后的同步时间
     */
    public Date getsStarttime() {
        return sStarttime;
    }

    /**
     * 设置最后的同步时间
     *
     * @param sStarttime 最后的同步时间
     */
    public void setsStarttime(Date sStarttime) {
        this.sStarttime = sStarttime;
    }

    /**
     * @return s_endtime
     */
    public Date getsEndtime() {
        return sEndtime;
    }

    /**
     * @param sEndtime
     */
    public void setsEndtime(Date sEndtime) {
        this.sEndtime = sEndtime;
    }

    /**
     * 获取同步成功的标记
     *
     * @return s_success - 同步成功的标记
     */
    public Boolean getsSuccess() {
        return sSuccess;
    }

    /**
     * 设置同步成功的标记
     *
     * @param sSuccess 同步成功的标记
     */
    public void setsSuccess(Boolean sSuccess) {
        this.sSuccess = sSuccess;
    }

    /**
     * @return s_count
     */
    public Long getsCount() {
        return sCount;
    }

    /**
     * @param sCount
     */
    public void setsCount(Long sCount) {
        this.sCount = sCount;
    }

    /**
     * @return s_update
     */
    public Long getsUpdate() {
        return sUpdate;
    }

    /**
     * @param sUpdate
     */
    public void setsUpdate(Long sUpdate) {
        this.sUpdate = sUpdate;
    }

    /**
     * @return s_insert
     */
    public Long getsInsert() {
        return sInsert;
    }

    /**
     * @param sInsert
     */
    public void setsInsert(Long sInsert) {
        this.sInsert = sInsert;
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
        SyncLog other = (SyncLog) that;
        return (this.getsId() == null ? other.getsId() == null : this.getsId().equals(other.getsId()))
            && (this.getsType() == null ? other.getsType() == null : this.getsType().equals(other.getsType()))
            && (this.getsStarttime() == null ? other.getsStarttime() == null : this.getsStarttime().equals(other.getsStarttime()))
            && (this.getsEndtime() == null ? other.getsEndtime() == null : this.getsEndtime().equals(other.getsEndtime()))
            && (this.getsSuccess() == null ? other.getsSuccess() == null : this.getsSuccess().equals(other.getsSuccess()))
            && (this.getsCount() == null ? other.getsCount() == null : this.getsCount().equals(other.getsCount()))
            && (this.getsUpdate() == null ? other.getsUpdate() == null : this.getsUpdate().equals(other.getsUpdate()))
            && (this.getsInsert() == null ? other.getsInsert() == null : this.getsInsert().equals(other.getsInsert()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getsId() == null) ? 0 : getsId().hashCode());
        result = prime * result + ((getsType() == null) ? 0 : getsType().hashCode());
        result = prime * result + ((getsStarttime() == null) ? 0 : getsStarttime().hashCode());
        result = prime * result + ((getsEndtime() == null) ? 0 : getsEndtime().hashCode());
        result = prime * result + ((getsSuccess() == null) ? 0 : getsSuccess().hashCode());
        result = prime * result + ((getsCount() == null) ? 0 : getsCount().hashCode());
        result = prime * result + ((getsUpdate() == null) ? 0 : getsUpdate().hashCode());
        result = prime * result + ((getsInsert() == null) ? 0 : getsInsert().hashCode());
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
        sb.append(", sId=").append(sId);
        sb.append(", sType=").append(sType);
        sb.append(", sStarttime=").append(sStarttime);
        sb.append(", sEndtime=").append(sEndtime);
        sb.append(", sSuccess=").append(sSuccess);
        sb.append(", sCount=").append(sCount);
        sb.append(", sUpdate=").append(sUpdate);
        sb.append(", sInsert=").append(sInsert);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}