package com.system.service.impl;

import com.system.dao.SysNursingLevelDao;
import com.system.entity.SysNursingLevel;
import com.system.pojo.CreateSysNursingLevelInfo;
import com.system.pojo.SysNursingLevelDTO;
import com.system.service.SysNursingLevelService;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
@Service("sysNursingLevelService")
public class SysNursingLevelServiceImpl implements SysNursingLevelService {
    @Resource
    private SysNursingLevelDao sysNursingLevelDao;

    @Override
    public SysNursingLevel get(String nursingLevelStr) {
        List<SysNursingLevel> list = sysNursingLevelDao.selectByExample(getExample(nursingLevelStr));
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public SysNursingLevel get(int id) {
        return sysNursingLevelDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysNursingLevel> getList() {
        List<SysNursingLevel> list= sysNursingLevelDao.selectAll()
                .stream()
                .sorted(Comparator.comparing(sysNursingLevel -> sysNursingLevel.getValue()))
                .collect(Collectors.toList());
        if(list==null||list.size()==0){
            throw new NoneGetException();
        }
        return list;
    }

    @Override
    public boolean insert(CreateSysNursingLevelInfo sysNursingLevelInfo) {
        List<SysNursingLevel> sysNursingLevelList = sysNursingLevelDao.selectByExample(getExample(sysNursingLevelInfo.getValue()));
        if(sysNursingLevelList!=null&&sysNursingLevelList.size()>0){
            throw new NoneSaveException("不能新增相同的病区名");
        }

        SysNursingLevel sysNursingLevel = convertToSysNursingLevel(sysNursingLevelInfo);
        return sysNursingLevelDao.insertSelective(sysNursingLevel)>0;
    }

    private Example getExample(String nursingLevelName) {
        Example example = new Example(SysNursingLevel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("value", nursingLevelName);
        return example;
    }

    @Override
    public boolean update(SysNursingLevelDTO sysNursingLevelDTO) {
        SysNursingLevel sysNursingLevel = convertToSysNursingLevel(sysNursingLevelDTO);
        if (sysNursingLevelDao.updateByPrimaryKeySelective(sysNursingLevel) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    private SysNursingLevel convertToSysNursingLevel(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysNursingLevel result = new SysNursingLevel();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Integer> idList) {
        for (Integer anIdList : idList) {
            if (sysNursingLevelDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }
}
