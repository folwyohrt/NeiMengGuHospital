package com.system.entity.SqlServer;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "pts_vw_ssxx")
public class PtsVwSsxx implements Serializable {
    @Column(name = "ZYH")
    private String ZYH;
    @Column(name = "ZYCS")
    private String ZYCS;
    @Column(name = "XM")
    private String XM;
    @Column(name = "XB")
    private String XB;
    @Column(name = "XH")
    private String XH;
    @Column(name = "SSBM")
    private String SSBM;
    @Column(name = "SSMC")
    private String SSMC;
    @Column(name = "SSRQ")
    private Date SSRQ;
    @Column(name = "SQZD")
    private String SQZD;
    @Column(name = "SSLX")
    private String SSLX;
    @Column(name = "SSYS")
    private String SSYS;
    @Column(name = "ZYKS")
    private String ZYKS;
    @Column(name = "PCSJ")
    private Date PCSJ;
    
    private static final long serialVersionUID = 1L;
    
    public String getZYH() {
        return ZYH;
    }

    public void setZYH(String ZYH) {
        this.ZYH = ZYH;
    }

    public String getZYCS() {
        return ZYCS;
    }

    public void setZYCS(String ZYCS) {
        this.ZYCS = ZYCS;
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public String getXB() {
        return XB;
    }

    public void setXB(String XB) {
        this.XB = XB;
    }

    public String getXH() {
        return XH;
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public String getSSBM() {
        return SSBM;
    }

    public void setSSBM(String SSBM) {
        this.SSBM = SSBM;
    }

    public String getSSMC() {
        return SSMC;
    }

    public void setSSMC(String SSMC) {
        this.SSMC = SSMC;
    }

    public Date getSSRQ() {
        return SSRQ;
    }

    public void setSSRQ(Date SSRQ) {
        this.SSRQ = SSRQ;
    }

    public String getSQZD() {
        return SQZD;
    }

    public void setSQZD(String SQZD) {
        this.SQZD = SQZD;
    }

    public String getSSLX() {
        return SSLX;
    }

    public void setSSLX(String SSLX) {
        this.SSLX = SSLX;
    }

    public String getSSYS() {
        return SSYS;
    }

    public void setSSYS(String SSYS) {
        this.SSYS = SSYS;
    }

    public String getZYKS() {
        return ZYKS;
    }

    public void setZYKS(String ZYKS) {
        this.ZYKS = ZYKS;
    }

    public Date getPCSJ() {
        return PCSJ;
    }

    public void setPCSJ(Date PCSJ) {
        this.PCSJ = PCSJ;
    }

    @Override
    public String toString() {
        return "PtsVwSsxx{" +
                "ZYH='" + ZYH + '\'' +
                ", ZYCS='" + ZYCS + '\'' +
                ", XM='" + XM + '\'' +
                ", XB='" + XB + '\'' +
                ", XH='" + XH + '\'' +
                ", SSBM='" + SSBM + '\'' +
                ", SSMC='" + SSMC + '\'' +
                ", SSRQ=" + SSRQ +
                ", SQZD='" + SQZD + '\'' +
                ", SSLX='" + SSLX + '\'' +
                ", SSYS='" + SSYS + '\'' +
                ", ZYKS='" + ZYKS + '\'' +
                ", PCSJ=" + PCSJ +
                '}';
    }
}
