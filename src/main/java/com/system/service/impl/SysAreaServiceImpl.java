package com.system.service.impl;

import com.system.dao.SysAreaDao;
import com.system.entity.SysArea;
import com.system.pojo.CreateSysAreaInfo;
import com.system.pojo.SysAreaDTO;
import com.system.service.SysAreaService;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
@Service("sysAreaService")
public class SysAreaServiceImpl implements SysAreaService {
    @Resource
    private SysAreaDao sysAreaDao;

    @Override
    public SysArea get(String areaStr) {
        List<SysArea> list = sysAreaDao.selectByExample(getExample(areaStr));
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public SysArea get(int id) {
        return sysAreaDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysArea> getList() {
        List<SysArea> list= sysAreaDao.selectAll();
        if(list==null||list.size()==0){
            throw new NoneGetException();
        }
        return list;
    }

    @Override
    public boolean insert(CreateSysAreaInfo sysAreaInfo) {
        List<SysArea> sysAreaList = sysAreaDao.selectByExample(getExample(sysAreaInfo.getValue()));
        if(sysAreaList!=null&&sysAreaList.size()>0){
            throw new NoneSaveException("不能新增相同的病区名");
        }

        SysArea sysArea = convertToSysArea(sysAreaInfo);
        return sysAreaDao.insertSelective(sysArea)>0;
    }

    private Example getExample(String areaName) {
        Example example = new Example(SysArea.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("value", areaName);
        return example;
    }

    @Override
    public boolean update(SysAreaDTO sysAreaDTO) {
        SysArea sysArea = convertToSysArea(sysAreaDTO);
        if (sysAreaDao.updateByPrimaryKeySelective(sysArea) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    private SysArea convertToSysArea(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysArea result = new SysArea();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Integer> idList) {
        for (Integer anIdList : idList) {
            if (sysAreaDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }
}
