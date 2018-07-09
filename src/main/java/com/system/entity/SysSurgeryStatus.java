package com.system.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_surgery_status")
public class SysSurgeryStatus implements Serializable {
    /**
     * 手术类型id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手术类型
     */
    private String value;

    private static final long serialVersionUID = 1L;

    /**
     * 获取手术类型id
     *
     * @return id - 手术类型id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置手术类型id
     *
     * @param id 手术类型id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取手术类型
     *
     * @return value - 手术类型
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置手术类型
     *
     * @param value 手术类型
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
        SysSurgeryStatus other = (SysSurgeryStatus) that;
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