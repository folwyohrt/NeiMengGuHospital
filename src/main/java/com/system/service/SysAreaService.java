package com.system.service;

import com.system.entity.SysArea;
import com.system.pojo.CreateSysAreaInfo;
import com.system.pojo.SysAreaDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
public interface SysAreaService {
    SysArea get(int id);
    List<SysArea> getList();
    boolean insert(CreateSysAreaInfo sysAreaInfo);
    boolean update(SysAreaDTO sysAreaDTO);
    boolean delete(List<Integer> idList);
}
