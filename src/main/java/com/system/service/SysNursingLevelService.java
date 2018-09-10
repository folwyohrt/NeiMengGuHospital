package com.system.service;

import com.system.entity.SysNursingLevel;
import com.system.pojo.CreateSysNursingLevelInfo;
import com.system.pojo.SysNursingLevelDTO;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
public interface SysNursingLevelService {
    SysNursingLevel get(int id);
    SysNursingLevel get(String nursingLevelStr);
    List<SysNursingLevel> getList();
    boolean insert(CreateSysNursingLevelInfo sysNursingLevelInfo);
    boolean update(SysNursingLevelDTO sysNursingLevelDTO);
    boolean delete(List<Integer> idList);
}
