package com.system.service;

import com.system.pojo.SysHospitalizationDTO;
import com.system.pojo.SysHospitalizationQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:48
 * @Description:
 */
public interface SysHospitalizationService {
    SysHospitalizationDTO get(long id);

    List<SysHospitalizationDTO> getList() ;

    List<SysHospitalizationDTO> getList(SysHospitalizationQuery sysHospitalizationQuery)  ;

    boolean insert(SysHospitalizationDTO createSysHospitalizationInfo)  ;

    boolean update(SysHospitalizationDTO sysUserDTO)  ;

    boolean delete(List<Long> idList);
}
