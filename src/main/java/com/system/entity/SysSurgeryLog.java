package com.system.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_surgery_log")
public class SysSurgeryLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "s_id")
    private Long sId;

    @Column(name = "visit_time")
    private Date visitTime;

    @Column(name = "exit_time")
    private Date exitTime;

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
     * @return s_id
     */
    public Long getsId() {
        return sId;
    }

    /**
     * @param sId
     */
    public void setsId(Long sId) {
        this.sId = sId;
    }

    /**
     * @return visit_time
     */
    public Date getVisitTime() {
        return visitTime;
    }

    /**
     * @param visitTime
     */
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * @return exit_time
     */
    public Date getExitTime() {
        return exitTime;
    }

    /**
     * @param exitTime
     */
    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
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
        SysSurgeryLog other = (SysSurgeryLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getsId() == null ? other.getsId() == null : this.getsId().equals(other.getsId()))
            && (this.getVisitTime() == null ? other.getVisitTime() == null : this.getVisitTime().equals(other.getVisitTime()))
            && (this.getExitTime() == null ? other.getExitTime() == null : this.getExitTime().equals(other.getExitTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getsId() == null) ? 0 : getsId().hashCode());
        result = prime * result + ((getVisitTime() == null) ? 0 : getVisitTime().hashCode());
        result = prime * result + ((getExitTime() == null) ? 0 : getExitTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sId=").append(sId);
        sb.append(", visitTime=").append(visitTime);
        sb.append(", exitTime=").append(exitTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}