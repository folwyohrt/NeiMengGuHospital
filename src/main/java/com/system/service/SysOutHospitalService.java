package com.system.service;

import com.system.entity.SysOutHospital;
import com.system.pojo.*;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:48
 * @Description:
 */
public interface SysOutHospitalService {
    SysOutHospitalDTO get(long id);

    SysOutHospital get(int times, String hId);

    //PagingResult getPageList(PagingRequest request);

    PagingResult getPageList(SysOutHospitalQuery sysOutHospitalQuery);

    boolean insert(SysOutHospitalDTO createSysOutHospitalInfo);

    boolean insert(SysOutHospital sysOutHospital);

    boolean update(SysOutHospitalDTO sysOutHospitalDTO);

    boolean update(SysOutHospital sysOutHospital);

    boolean delete(List<Long> idList);

    List<String> getNameList();
}
