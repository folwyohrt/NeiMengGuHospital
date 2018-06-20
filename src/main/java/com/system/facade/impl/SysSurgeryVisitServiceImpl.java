package com.system.facade.impl;

import com.system.entity.SysSurgery;
import com.system.entity.SysSurgeryLog;
import com.system.facade.SysSurgeryVisitService;
import com.system.service.SysSurgeryLogService;
import com.system.service.SysSurgeryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: 李景然
 * @Date: 2018/6/14 11:28
 * @Description:
 */
@Service("sysSurgeryVisitService")
public class SysSurgeryVisitServiceImpl implements SysSurgeryVisitService {
    @Resource
    private SysSurgeryService sysSurgeryService;
    @Resource
    private SysSurgeryLogService sysSurgeryLogService;

    @Override
    public boolean updateVisit(long id, int visitStatus) {
        SysSurgery sysSurgery = sysSurgeryService.get(id);
        if (visitStatus == 1) {
            sysSurgery.setVisitStatus("已探视");
            sysSurgeryService.update(sysSurgery);

            SysSurgeryLog sysSurgeryLog = new SysSurgeryLog();
            sysSurgeryLog.setsId(sysSurgery.getId());
            sysSurgeryLogService.insert(sysSurgeryLog);
        } else {
            sysSurgery.setVisitStatus("未探视");
            sysSurgeryService.update(sysSurgery);

            SysSurgeryLog sysSurgeryLog = sysSurgeryLogService.get(id);
            sysSurgeryLog.setExitTime(new Date());
            sysSurgeryLogService.update(sysSurgeryLog);
        }
        return true;
    }
}
