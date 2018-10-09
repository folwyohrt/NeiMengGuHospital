package com.system.entity.DB2.test1;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "DB2ADMIN.PTS_TB_SSXX")
public class PtsTbSsxx implements Serializable {
    @Column(name = "ZYH")
    private String zyh;

    @Column(name = "ZYCS")
    private Integer zycs;

    @Column(name = "SSLX")
    private String sslx;

    @Column(name = "SSRQ")
    private Date ssrq;

    @Column(name = "SQZD")
    private String sqzd;

    @Column(name = "SSYS")
    private String ssys;

    private static final long serialVersionUID = 1L;

    /**
     * @return ZYH
     */
    public String getZyh() {
        return zyh;
    }

    /**
     * @param zyh
     */
    public void setZyh(String zyh) {
        this.zyh = zyh == null ? null : zyh.trim();
    }

    /**
     * @return ZYCS
     */
    public Integer getZycs() {
        return zycs;
    }

    /**
     * @param zycs
     */
    public void setZycs(Integer zycs) {
        this.zycs = zycs;
    }

    /**
     * @return SSLX
     */
    public String getSslx() {
        return sslx;
    }

    /**
     * @param sslx
     */
    public void setSslx(String sslx) {
        this.sslx = sslx == null ? null : sslx.trim();
    }

    /**
     * @return SSRQ
     */
    public Date getSsrq() {
        return ssrq;
    }

    /**
     * @param ssrq
     */
    public void setSsrq(Date ssrq) {
        this.ssrq = ssrq;
    }

    /**
     * @return SQZD
     */
    public String getSqzd() {
        return sqzd;
    }

    /**
     * @param sqzd
     */
    public void setSqzd(String sqzd) {
        this.sqzd = sqzd == null ? null : sqzd.trim();
    }

    /**
     * @return SSYS
     */
    public String getSsys() {
        return ssys;
    }

    /**
     * @param ssys
     */
    public void setSsys(String ssys) {
        this.ssys = ssys == null ? null : ssys.trim();
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
        PtsTbSsxx other = (PtsTbSsxx) that;
        return (this.getZyh() == null ? other.getZyh() == null : this.getZyh().equals(other.getZyh()))
            && (this.getZycs() == null ? other.getZycs() == null : this.getZycs().equals(other.getZycs()))
            && (this.getSslx() == null ? other.getSslx() == null : this.getSslx().equals(other.getSslx()))
            && (this.getSsrq() == null ? other.getSsrq() == null : this.getSsrq().equals(other.getSsrq()))
            && (this.getSqzd() == null ? other.getSqzd() == null : this.getSqzd().equals(other.getSqzd()))
            && (this.getSsys() == null ? other.getSsys() == null : this.getSsys().equals(other.getSsys()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getZyh() == null) ? 0 : getZyh().hashCode());
        result = prime * result + ((getZycs() == null) ? 0 : getZycs().hashCode());
        result = prime * result + ((getSslx() == null) ? 0 : getSslx().hashCode());
        result = prime * result + ((getSsrq() == null) ? 0 : getSsrq().hashCode());
        result = prime * result + ((getSqzd() == null) ? 0 : getSqzd().hashCode());
        result = prime * result + ((getSsys() == null) ? 0 : getSsys().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", zyh=").append(zyh);
        sb.append(", zycs=").append(zycs);
        sb.append(", sslx=").append(sslx);
        sb.append(", ssrq=").append(ssrq);
        sb.append(", sqzd=").append(sqzd);
        sb.append(", ssys=").append(ssys);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}