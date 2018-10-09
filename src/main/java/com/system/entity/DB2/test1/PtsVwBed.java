package com.system.entity.DB2.test1;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "TEST1.PTS_VW_BED")
public class PtsVwBed implements Serializable {
    @Column(name = "OFFICE")
    private String office;

    @Column(name = "OFFICEID")
    private String officeid;

    @Column(name = "BEDID")
    private String bedid;

    private static final long serialVersionUID = 1L;

    /**
     * @return OFFICE
     */
    public String getOffice() {
        return office;
    }

    /**
     * @param office
     */
    public void setOffice(String office) {
        this.office = office == null ? null : office.trim();
    }

    /**
     * @return OFFICEID
     */
    public String getOfficeid() {
        return officeid;
    }

    /**
     * @param officeid
     */
    public void setOfficeid(String officeid) {
        this.officeid = officeid == null ? null : officeid.trim();
    }

    /**
     * @return BEDID
     */
    public String getBedid() {
        return bedid;
    }

    /**
     * @param bedid
     */
    public void setBedid(String bedid) {
        this.bedid = bedid == null ? null : bedid.trim();
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
        PtsVwBed other = (PtsVwBed) that;
        return (this.getOffice() == null ? other.getOffice() == null : this.getOffice().equals(other.getOffice()))
            && (this.getOfficeid() == null ? other.getOfficeid() == null : this.getOfficeid().equals(other.getOfficeid()))
            && (this.getBedid() == null ? other.getBedid() == null : this.getBedid().equals(other.getBedid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOffice() == null) ? 0 : getOffice().hashCode());
        result = prime * result + ((getOfficeid() == null) ? 0 : getOfficeid().hashCode());
        result = prime * result + ((getBedid() == null) ? 0 : getBedid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", office=").append(office);
        sb.append(", officeid=").append(officeid);
        sb.append(", bedid=").append(bedid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}