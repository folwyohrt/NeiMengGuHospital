package com.system.service;

import com.system.entity.SysPatientStatus;
import com.system.pojo.CreateSysPatientStatusInfo;
import com.system.pojo.SysPatientStatusDTO;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
public interface SysPatientStatusService {
    SysPatientStatus get(int id);
    List<SysPatientStatus> getList();
    boolean insert(CreateSysPatientStatusInfo sysPatientStatusInfo);
    boolean update(SysPatientStatusDTO sysPatientStatusDTO);
    boolean delete(List<Integer> idList);
}
