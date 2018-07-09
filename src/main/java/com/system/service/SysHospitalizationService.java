package com.system.service;

import com.system.entity.SysHospitalization;
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

    SysHospitalization get(int times, String hId);

    List<SysHospitalizationDTO> getList();

    List<SysHospitalizationDTO> getList(SysHospitalizationQuery sysHospitalizationQuery);


    List<SysHospitalization> getList(int pStatus);

    boolean insert(SysHospitalizationDTO createSysHospitalizationInfo);

    boolean insert(SysHospitalization sysHospitalization);

    boolean update(SysHospitalizationDTO sysHospitalizationDTO);

    boolean update(SysHospitalization sysHospitalization);

    boolean delete(List<Long> idList);
}
