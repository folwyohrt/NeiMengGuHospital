package com.system.service;

import com.system.entity.SysSurgeryLog;

/**
 * @Auther: 李景然
 * @Date: 2018/6/14 10:09
 * @Description:
 */
public interface SysSurgeryLogService {
    SysSurgeryLog get(long sId);

    boolean insert(SysSurgeryLog sysSurgeryLog);

    boolean update(SysSurgeryLog sysSurgeryLog);
}
