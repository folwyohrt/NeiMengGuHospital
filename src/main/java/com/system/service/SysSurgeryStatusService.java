package com.system.service;

import com.system.entity.SysSurgeryStatus;
import com.system.pojo.CreateSysSurgeryStatusInfo;
import com.system.pojo.SysSurgeryStatusDTO;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
public interface SysSurgeryStatusService {
    SysSurgeryStatus get(int id);
    SysSurgeryStatus get(String sslx);
    List<SysSurgeryStatus> getList();
    boolean insert(CreateSysSurgeryStatusInfo sysSurgeryStatusInfo);
    boolean update(SysSurgeryStatusDTO sysSurgeryStatusDTO);
    boolean delete(List<Integer> idList);
}
