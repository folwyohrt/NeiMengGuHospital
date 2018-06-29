package com.system.entity.DB2.test1;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "TEST1.LOGIN_MODE_SWITCH_APPLY")
public class LoginModeSwitchApply implements Serializable {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "APPLY_NO")
    private String applyNo;

    @Column(name = "LOGIN_MODE")
    private Integer loginMode;

    @Column(name = "AUDIT_STATUS")
    private Integer auditStatus;

    @Column(name = "APPLY_USER")
    private String applyUser;

    @Column(name = "APPROVAL_USER")
    private String approvalUser;

    @Column(name = "APPLY_TIME")
    private Date applyTime;

    @Column(name = "APPROVAL_TIME")
    private Date approvalTime;

    @Column(name = "SWITCH_REASON")
    private String switchReason;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
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
     * @return APPLY_NO
     */
    public String getApplyNo() {
        return applyNo;
    }

    /**
     * @param applyNo
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    /**
     * @return LOGIN_MODE
     */
    public Integer getLoginMode() {
        return loginMode;
    }

    /**
     * @param loginMode
     */
    public void setLoginMode(Integer loginMode) {
        this.loginMode = loginMode;
    }

    /**
     * @return AUDIT_STATUS
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return APPLY_USER
     */
    public String getApplyUser() {
        return applyUser;
    }

    /**
     * @param applyUser
     */
    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser == null ? null : applyUser.trim();
    }

    /**
     * @return APPROVAL_USER
     */
    public String getApprovalUser() {
        return approvalUser;
    }

    /**
     * @param approvalUser
     */
    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser == null ? null : approvalUser.trim();
    }

    /**
     * @return APPLY_TIME
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return APPROVAL_TIME
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * @param approvalTime
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * @return SWITCH_REASON
     */
    public String getSwitchReason() {
        return switchReason;
    }

    /**
     * @param switchReason
     */
    public void setSwitchReason(String switchReason) {
        this.switchReason = switchReason == null ? null : switchReason.trim();
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
        LoginModeSwitchApply other = (LoginModeSwitchApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getApplyNo() == null ? other.getApplyNo() == null : this.getApplyNo().equals(other.getApplyNo()))
            && (this.getLoginMode() == null ? other.getLoginMode() == null : this.getLoginMode().equals(other.getLoginMode()))
            && (this.getAuditStatus() == null ? other.getAuditStatus() == null : this.getAuditStatus().equals(other.getAuditStatus()))
            && (this.getApplyUser() == null ? other.getApplyUser() == null : this.getApplyUser().equals(other.getApplyUser()))
            && (this.getApprovalUser() == null ? other.getApprovalUser() == null : this.getApprovalUser().equals(other.getApprovalUser()))
            && (this.getApplyTime() == null ? other.getApplyTime() == null : this.getApplyTime().equals(other.getApplyTime()))
            && (this.getApprovalTime() == null ? other.getApprovalTime() == null : this.getApprovalTime().equals(other.getApprovalTime()))
            && (this.getSwitchReason() == null ? other.getSwitchReason() == null : this.getSwitchReason().equals(other.getSwitchReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getApplyNo() == null) ? 0 : getApplyNo().hashCode());
        result = prime * result + ((getLoginMode() == null) ? 0 : getLoginMode().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getApplyUser() == null) ? 0 : getApplyUser().hashCode());
        result = prime * result + ((getApprovalUser() == null) ? 0 : getApprovalUser().hashCode());
        result = prime * result + ((getApplyTime() == null) ? 0 : getApplyTime().hashCode());
        result = prime * result + ((getApprovalTime() == null) ? 0 : getApprovalTime().hashCode());
        result = prime * result + ((getSwitchReason() == null) ? 0 : getSwitchReason().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", applyNo=").append(applyNo);
        sb.append(", loginMode=").append(loginMode);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", applyUser=").append(applyUser);
        sb.append(", approvalUser=").append(approvalUser);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", approvalTime=").append(approvalTime);
        sb.append(", switchReason=").append(switchReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}