package com.system.service.impl;

import com.system.dao.SysSurgeryStatusDao;
import com.system.entity.SysSurgeryStatus;
import com.system.pojo.CreateSysSurgeryStatusInfo;
import com.system.pojo.SysSurgeryStatusDTO;
import com.system.service.SysSurgeryStatusService;
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
@Service("sysSurgeryStatusService")
public class SysSurgeryStatusServicempl implements SysSurgeryStatusService {
    @Resource
    private SysSurgeryStatusDao sysSurgeryStatusDao;

    @Override
    public SysSurgeryStatus get(int id) {
        return sysSurgeryStatusDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysSurgeryStatus> getList() {
        List<SysSurgeryStatus> list= sysSurgeryStatusDao.selectAll();
        if(list==null||list.size()==0){
            throw new NoneGetException();
        }
        return list;
    }

    @Override
    public boolean insert(CreateSysSurgeryStatusInfo sysSurgeryStatusInfo) {
        List<SysSurgeryStatus> sysSurgeryStatusList = sysSurgeryStatusDao.selectByExample(getExample(sysSurgeryStatusInfo.getValue()));
        if(sysSurgeryStatusList!=null&&sysSurgeryStatusList.size()>0){
            throw new NoneSaveException("不能新增相同的患者状态");
        }

        SysSurgeryStatus sysSurgeryStatus = convertToSysSurgeryStatus(sysSurgeryStatusInfo);
        return sysSurgeryStatusDao.insertSelective(sysSurgeryStatus)>0;
    }

    private Example getExample(String name) {
        Example example = new Example(SysSurgeryStatus.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("value", name);
        return example;
    }

    @Override
    public boolean update(SysSurgeryStatusDTO sysSurgeryStatusDTO) {
        SysSurgeryStatus sysSurgeryStatus = convertToSysSurgeryStatus(sysSurgeryStatusDTO);
        if (sysSurgeryStatusDao.updateByPrimaryKeySelective(sysSurgeryStatus) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    private SysSurgeryStatus convertToSysSurgeryStatus(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysSurgeryStatus result = new SysSurgeryStatus();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Integer> idList) {
        for (Integer anIdList : idList) {
            if (sysSurgeryStatusDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }
}
