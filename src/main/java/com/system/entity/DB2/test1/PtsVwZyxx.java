package com.system.entity.DB2.test1;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "TEST1.PTS_VW_ZYXX")
public class PtsVwZyxx implements Serializable {
    @Column(name = "ZYH")
    private String zyh;

    @Column(name = "YLFKFS")
    private String ylfkfs;

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

    @Column(name = "RYRQ")
    private Date ryrq;

    @Column(name = "RYKS")
    private String ryks;

    @Column(name = "RYBCH")
    private String rybch;

    @Column(name = "ZZYS")
    private String zzys;

    @Column(name = "RYZDYSMS")
    private String ryzdysms;

    @Column(name = "RYTJ")
    private String rytj;

    @Column(name = "HLJB")
    private String hljb;

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
     * @return YLFKFS
     */
    public String getYlfkfs() {
        return ylfkfs;
    }

    /**
     * @param ylfkfs
     */
    public void setYlfkfs(String ylfkfs) {
        this.ylfkfs = ylfkfs == null ? null : ylfkfs.trim();
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
     * @return RYRQ
     */
    public Date getRyrq() {
        return ryrq;
    }

    /**
     * @param ryrq
     */
    public void setRyrq(Date ryrq) {
        this.ryrq = ryrq;
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
     * @return RYBCH
     */
    public String getRybch() {
        return rybch;
    }

    /**
     * @param rybch
     */
    public void setRybch(String rybch) {
        this.rybch = rybch == null ? null : rybch.trim();
    }

    /**
     * @return ZZYS
     */
    public String getZzys() {
        return zzys;
    }

    /**
     * @param zzys
     */
    public void setZzys(String zzys) {
        this.zzys = zzys == null ? null : zzys.trim();
    }

    /**
     * @return RYZDYSMS
     */
    public String getRyzdysms() {
        return ryzdysms;
    }

    /**
     * @param ryzdysms
     */
    public void setRyzdysms(String ryzdysms) {
        this.ryzdysms = ryzdysms == null ? null : ryzdysms.trim();
    }

    /**
     * @return RYTJ
     */
    public String getRytj() {
        return rytj;
    }

    /**
     * @param rytj
     */
    public void setRytj(String rytj) {
        this.rytj = rytj == null ? null : rytj.trim();
    }

    /**
     * @return HLJB
     */
    public String getHljb() {
        return hljb;
    }

    /**
     * @param hljb
     */
    public void setHljb(String hljb) {
        this.hljb = hljb == null ? null : hljb.trim();
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
        PtsVwZyxx other = (PtsVwZyxx) that;
        return (this.getZyh() == null ? other.getZyh() == null : this.getZyh().equals(other.getZyh()))
            && (this.getYlfkfs() == null ? other.getYlfkfs() == null : this.getYlfkfs().equals(other.getYlfkfs()))
            && (this.getZycs() == null ? other.getZycs() == null : this.getZycs().equals(other.getZycs()))
            && (this.getXm() == null ? other.getXm() == null : this.getXm().equals(other.getXm()))
            && (this.getXb() == null ? other.getXb() == null : this.getXb().equals(other.getXb()))
            && (this.getCsrq() == null ? other.getCsrq() == null : this.getCsrq().equals(other.getCsrq()))
            && (this.getNl() == null ? other.getNl() == null : this.getNl().equals(other.getNl()))
            && (this.getRyrq() == null ? other.getRyrq() == null : this.getRyrq().equals(other.getRyrq()))
            && (this.getRyks() == null ? other.getRyks() == null : this.getRyks().equals(other.getRyks()))
            && (this.getRybch() == null ? other.getRybch() == null : this.getRybch().equals(other.getRybch()))
            && (this.getZzys() == null ? other.getZzys() == null : this.getZzys().equals(other.getZzys()))
            && (this.getRyzdysms() == null ? other.getRyzdysms() == null : this.getRyzdysms().equals(other.getRyzdysms()))
            && (this.getRytj() == null ? other.getRytj() == null : this.getRytj().equals(other.getRytj()))
            && (this.getHljb() == null ? other.getHljb() == null : this.getHljb().equals(other.getHljb()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getZyh() == null) ? 0 : getZyh().hashCode());
        result = prime * result + ((getYlfkfs() == null) ? 0 : getYlfkfs().hashCode());
        result = prime * result + ((getZycs() == null) ? 0 : getZycs().hashCode());
        result = prime * result + ((getXm() == null) ? 0 : getXm().hashCode());
        result = prime * result + ((getXb() == null) ? 0 : getXb().hashCode());
        result = prime * result + ((getCsrq() == null) ? 0 : getCsrq().hashCode());
        result = prime * result + ((getNl() == null) ? 0 : getNl().hashCode());
        result = prime * result + ((getRyrq() == null) ? 0 : getRyrq().hashCode());
        result = prime * result + ((getRyks() == null) ? 0 : getRyks().hashCode());
        result = prime * result + ((getRybch() == null) ? 0 : getRybch().hashCode());
        result = prime * result + ((getZzys() == null) ? 0 : getZzys().hashCode());
        result = prime * result + ((getRyzdysms() == null) ? 0 : getRyzdysms().hashCode());
        result = prime * result + ((getRytj() == null) ? 0 : getRytj().hashCode());
        result = prime * result + ((getHljb() == null) ? 0 : getHljb().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", zyh=").append(zyh);
        sb.append(", ylfkfs=").append(ylfkfs);
        sb.append(", zycs=").append(zycs);
        sb.append(", xm=").append(xm);
        sb.append(", xb=").append(xb);
        sb.append(", csrq=").append(csrq);
        sb.append(", nl=").append(nl);
        sb.append(", ryrq=").append(ryrq);
        sb.append(", ryks=").append(ryks);
        sb.append(", rybch=").append(rybch);
        sb.append(", zzys=").append(zzys);
        sb.append(", ryzdysms=").append(ryzdysms);
        sb.append(", rytj=").append(rytj);
        sb.append(", hljb=").append(hljb);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}