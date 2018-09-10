package com.system.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_patient_status")
public class SysPatientStatus implements Serializable {
    /**
     * 患者状态id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 患者状态
     */
    private String value;

    private static final long serialVersionUID = 1L;

    /**
     * 获取患者状态id
     *
     * @return id - 患者状态id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置患者状态id
     *
     * @param id 患者状态id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取患者状态
     *
     * @return value - 患者状态
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置患者状态
     *
     * @param value 患者状态
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
        SysPatientStatus other = (SysPatientStatus) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}