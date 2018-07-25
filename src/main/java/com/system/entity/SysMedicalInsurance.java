package com.system.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_medical_insurance")
public class SysMedicalInsurance implements Serializable {
    /**
     * 医保类型id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 医保类型
     */
    private String value;

    private static final long serialVersionUID = 1L;

    /**
     * 获取医保类型id
     *
     * @return id - 医保类型id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置医保类型id
     *
     * @param id 医保类型id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取医保类型
     *
     * @return value - 医保类型
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置医保类型
     *
     * @param value 医保类型
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
        SysMedicalInsurance other = (SysMedicalInsurance) that;
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