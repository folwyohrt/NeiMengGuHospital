package com.system.dao;

import com.system.entity.SysHospitalization;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SysHospitalizationDao extends Mapper<SysHospitalization> {
    @Update("truncate table sys_hospitalization")
    void truncate();
}