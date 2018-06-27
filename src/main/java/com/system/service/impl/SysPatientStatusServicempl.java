package com.system.service.impl;

import com.system.dao.SysPatientStatusDao;
import com.system.entity.SysPatientStatus;
import com.system.pojo.CreateSysPatientStatusInfo;
import com.system.pojo.SysPatientStatusDTO;
import com.system.service.SysPatientStatusService;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
@Service("sysPatientStatusService")
public class SysPatientStatusServicempl implements SysPatientStatusService {
    @Resource
    private SysPatientStatusDao sysPatientStatusDao;

    @Override
    public SysPatientStatus get(int id) {
        return sysPatientStatusDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPatientStatus> getList() {
        List<SysPatientStatus> list= sysPatientStatusDao.selectAll();
        if(list==null||list.size()==0){
            throw new NoneGetException();
        }
        return list;
    }

    @Override
    public boolean insert(CreateSysPatientStatusInfo sysPatientStatusInfo) {
        List<SysPatientStatus> sysPatientStatusList = sysPatientStatusDao.selectByExample(getExample(sysPatientStatusInfo.getValue()));
        if(sysPatientStatusList!=null&&sysPatientStatusList.size()>0){
            throw new NoneSaveException("不能新增相同的患者状态");
        }

        SysPatientStatus sysPatientStatus = convertToSysPatientStatus(sysPatientStatusInfo);
        return sysPatientStatusDao.insertSelective(sysPatientStatus)>0;
    }

    private Example getExample(String name) {
        Example example = new Example(SysPatientStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("value", name);
        return example;
    }

    @Override
    public boolean update(SysPatientStatusDTO sysPatientStatusDTO) {
        SysPatientStatus sysPatientStatus = convertToSysPatientStatus(sysPatientStatusDTO);
        if (sysPatientStatusDao.updateByPrimaryKeySelective(sysPatientStatus) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    private SysPatientStatus convertToSysPatientStatus(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysPatientStatus result = new SysPatientStatus();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Integer> idList) {
        for (Integer anIdList : idList) {
            if (sysPatientStatusDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }
}
