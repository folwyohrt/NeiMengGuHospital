package com.system.service.impl;

import com.system.dao.SysSurgeryLogDao;
import com.system.entity.SysSurgeryLog;
import com.system.entity.SysUser;
import com.system.service.SysSurgeryLogService;
import com.system.pojo.SysSurgeryDTO;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: 李景然
 * @Date: 2018/6/14 10:10
 * @Description:
 */
@Service("sysSurgeryLogService")
public class SysSurgeryLogServiceImpl implements SysSurgeryLogService {

    @Resource
    private SysSurgeryLogDao sysSurgeryLogDao;

    public SysSurgeryLog get(long sId) {
        List<SysSurgeryLog> resultList = sysSurgeryLogDao.selectByExample(getExample(sId));
        resultList.sort(Comparator.comparing(SysSurgeryLog::getVisitTime).reversed());
        if (resultList.size() != 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean insert(SysSurgeryLog sysSurgeryLog) {

        sysSurgeryLog.setVisitTime(new Date());
        return sysSurgeryLogDao.insertSelective(sysSurgeryLog) > 0;
    }

    @Override
    public boolean update(SysSurgeryLog sysSurgeryLog) {
        sysSurgeryLog.setExitTime(new Date());
        if (sysSurgeryLogDao.updateByPrimaryKeySelective(sysSurgeryLog) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    private Example getExample(long sId) {
        Example example = new Example(SysSurgeryLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sId", sId);
        return example;
    }
}
