package com.system.service.impl;

import com.system.dao.NM.SysHahaDao;
import com.system.entity.NM.SysHaha;
import com.system.service.SysHahaService;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 韩宪斌 on 2017/7/10.
 */
@Service("sysHahaService")
class SysHahaServiceImpl implements SysHahaService {

    @Resource
    private SysHahaDao sysHahaDao;

    @Override
    @DataSwitch(dataSource="dataSource2")
    public SysHaha get(int id) {

        SysHaha result = sysHahaDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有该用户！");
        }

        return result;
    }

}
