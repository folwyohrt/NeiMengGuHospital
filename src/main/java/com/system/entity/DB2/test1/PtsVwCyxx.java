package com.system.entity.DB2.test1;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "TEST1.PTS_VW_CYXX")
public class PtsVwCyxx implements Serializable {
    @Column(name = "ZYH")
    private String zyh;

    @Column(name = "ZYCS")
    private Integer zycs;

    @Column(name = "XM")
    private String xm;

    @Column(name = "XB")
    private String xb;

    @Column(name = "CSRQ")
    private Date csrq;

    @Column(name = "NL")
    private String nl;

    @Column(name = "CYRQ")
    private Date cyrq;

    @Column(name = "RYKS")
    private String ryks;

    @Column(name = "ZYTS")
    private Integer zyts;

    @Column(name = "CYZD")
    private String cyzd;

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
     * @return XM
     */
    public String getXm() {
        return xm;
    }

    /**
     * @param xm
     */
    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    /**
     * @return XB
     */
    public String getXb() {
        return xb;
    }

    /**
     * @param xb
     */
    public void setXb(String xb) {
        this.xb = xb == null ? null : xb.trim();
    }

    /**
     * @return CSRQ
     */
    public Date getCsrq() {
        return csrq;
    }

    /**
     * @param csrq
     */
    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    /**
     * @return NL
     */
    public String getNl() {
        return nl;
    }

    /**
     * @param nl
     */
    public void setNl(String nl) {
        this.nl = nl == null ? null : nl.trim();
    }

    /**
     * @return CYRQ
     */
    public Date getCyrq() {
        return cyrq;
    }

    /**
     * @param cyrq
     */
    public void setCyrq(Date cyrq) {
        this.cyrq = cyrq;
    }

    /**
     * @return RYKS
     */
    public String getRyks() {
        return ryks;
    }

    /**
     * @param ryks
     */
    public void setRyks(String ryks) {
        this.ryks = ryks == null ? null : ryks.trim();
    }

    /**
     * @return ZYTS
     */
    public Integer getZyts() {
        return zyts;
    }

    /**
     * @param zyts
     */
    public void setZyts(Integer zyts) {
        this.zyts = zyts;
    }

    /**
     * @return CYZD
     */
    public String getCyzd() {
        return cyzd;
    }

    /**
     * @param cyzd
     */
    public void setCyzd(String cyzd) {
        this.cyzd = cyzd == null ? null : cyzd.trim();
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
        PtsVwCyxx other = (PtsVwCyxx) that;
        return (this.getZyh() == null ? other.getZyh() == null : this.getZyh().equals(other.getZyh()))
            && (this.getZycs() == null ? other.getZycs() == null : this.getZycs().equals(other.getZycs()))
            && (this.getXm() == null ? other.getXm() == null : this.getXm().equals(other.getXm()))
            && (this.getXb() == null ? other.getXb() == null : this.getXb().equals(other.getXb()))
            && (this.getCsrq() == null ? other.getCsrq() == null : this.getCsrq().equals(other.getCsrq()))
            && (this.getNl() == null ? other.getNl() == null : this.getNl().equals(other.getNl()))
            && (this.getCyrq() == null ? other.getCyrq() == null : this.getCyrq().equals(other.getCyrq()))
            && (this.getRyks() == null ? other.getRyks() == null : this.getRyks().equals(other.getRyks()))
            && (this.getZyts() == null ? other.getZyts() == null : this.getZyts().equals(other.getZyts()))
            && (this.getCyzd() == null ? other.getCyzd() == null : this.getCyzd().equals(other.getCyzd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getZyh() == null) ? 0 : getZyh().hashCode());
        result = prime * result + ((getZycs() == null) ? 0 : getZycs().hashCode());
        result = prime * result + ((getXm() == null) ? 0 : getXm().hashCode());
        result = prime * result + ((getXb() == null) ? 0 : getXb().hashCode());
        result = prime * result + ((getCsrq() == null) ? 0 : getCsrq().hashCode());
        result = prime * result + ((getNl() == null) ? 0 : getNl().hashCode());
        result = prime * result + ((getCyrq() == null) ? 0 : getCyrq().hashCode());
        result = prime * result + ((getRyks() == null) ? 0 : getRyks().hashCode());
        result = prime * result + ((getZyts() == null) ? 0 : getZyts().hashCode());
        result = prime * result + ((getCyzd() == null) ? 0 : getCyzd().hashCode());
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
        sb.append(", xm=").append(xm);
        sb.append(", xb=").append(xb);
        sb.append(", csrq=").append(csrq);
        sb.append(", nl=").append(nl);
        sb.append(", cyrq=").append(cyrq);
        sb.append(", ryks=").append(ryks);
        sb.append(", zyts=").append(zyts);
        sb.append(", cyzd=").append(cyzd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}