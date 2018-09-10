package com.system.service;

import com.system.entity.SysHospitalization;
import com.system.pojo.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:48
 * @Description:
 */
public interface SysHospitalizationService {
    SysHospitalizationDTO get(long id);

    SysHospitalization get(int times, String hId);

    PagingResult getPageList( PagingRequest request);

    PagingResult getPageList(SysHospitalizationQuery sysHospitalizationQuery);
    //用于 后台 修改 出院状态
    List<SysHospitalization> getList(int pStatus);

    List<String> getBedList(Integer areaId);

    SysHospitalization getLatestRecordByhBed(String hBed);

    boolean insert(SysHospitalizationDTO createSysHospitalizationInfo);

    boolean insert(SysHospitalization sysHospitalization);

    boolean update(SysHospitalizationDTO sysHospitalizationDTO);

    boolean update(SysHospitalization sysHospitalization);

    boolean delete(List<Long> idList);

    boolean truncate();

    List<String> getNameList();
}
