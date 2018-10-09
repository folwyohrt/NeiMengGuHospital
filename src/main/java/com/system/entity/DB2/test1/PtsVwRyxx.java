package com.system.entity.DB2.test1;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "TEST1.PTS_VW_RYXX")
public class PtsVwRyxx implements Serializable {
    @Column(name = "CODENO")
    private String codeno;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "OFFICE")
    private String office;

    @Column(name = "ROLE")
    private String role;

    private static final long serialVersionUID = 1L;

    /**
     * @return CODENO
     */
    public String getCodeno() {
        return codeno;
    }

    /**
     * @param codeno
     */
    public void setCodeno(String codeno) {
        this.codeno = codeno == null ? null : codeno.trim();
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

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
     * @return ROLE
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
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
        PtsVwRyxx other = (PtsVwRyxx) that;
        return (this.getCodeno() == null ? other.getCodeno() == null : this.getCodeno().equals(other.getCodeno()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getOffice() == null ? other.getOffice() == null : this.getOffice().equals(other.getOffice()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCodeno() == null) ? 0 : getCodeno().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getOffice() == null) ? 0 : getOffice().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", codeno=").append(codeno);
        sb.append(", username=").append(username);
        sb.append(", office=").append(office);
        sb.append(", role=").append(role);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}