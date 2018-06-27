package com.system.service.impl;

import com.system.dao.SysMedicalInsuranceDao;
import com.system.entity.SysMedicalInsurance;
import com.system.pojo.CreateSysMedicalInsuranceInfo;
import com.system.pojo.SysMedicalInsuranceDTO;
import com.system.service.SysMedicalInsuranceService;
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
@Service("sysMedicalInsuranceService")
public class SysMedicalInsuranceServicempl implements SysMedicalInsuranceService {
    @Resource
    private SysMedicalInsuranceDao sysMedicalInsuranceDao;

    @Override
    public SysMedicalInsurance get(int id) {
        return sysMedicalInsuranceDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysMedicalInsurance> getList() {
        List<SysMedicalInsurance> list= sysMedicalInsuranceDao.selectAll();
        if(list==null||list.size()==0){
            throw new NoneGetException();
        }
        return list;
    }

    @Override
    public boolean insert(CreateSysMedicalInsuranceInfo sysMedicalInsuranceInfo) {
        List<SysMedicalInsurance> sysMedicalInsuranceList = sysMedicalInsuranceDao.selectByExample(getExample(sysMedicalInsuranceInfo.getValue()));
        if(sysMedicalInsuranceList!=null&&sysMedicalInsuranceList.size()>0){
            throw new NoneSaveException("不能新增相同的患者状态");
        }

        SysMedicalInsurance sysMedicalInsurance = convertToSysMedicalInsurance(sysMedicalInsuranceInfo);
        return sysMedicalInsuranceDao.insertSelective(sysMedicalInsurance)>0;
    }

    private Example getExample(String name) {
        Example example = new Example(SysMedicalInsurance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("value", name);
        return example;
    }

    @Override
    public boolean update(SysMedicalInsuranceDTO sysMedicalInsuranceDTO) {
        SysMedicalInsurance sysMedicalInsurance = convertToSysMedicalInsurance(sysMedicalInsuranceDTO);
        if (sysMedicalInsuranceDao.updateByPrimaryKeySelective(sysMedicalInsurance) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    private SysMedicalInsurance convertToSysMedicalInsurance(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysMedicalInsurance result = new SysMedicalInsurance();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Integer> idList) {
        for (Integer anIdList : idList) {
            if (sysMedicalInsuranceDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }
}
