package com.system.service;

import com.system.entity.SysMedicalInsurance;
import com.system.pojo.CreateSysMedicalInsuranceInfo;
import com.system.pojo.SysMedicalInsuranceDTO;

import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
public interface SysMedicalInsuranceService {
    SysMedicalInsurance get(int id);
    SysMedicalInsurance get(String str);
    List<SysMedicalInsurance> getList();
    boolean insert(CreateSysMedicalInsuranceInfo sysMedicalInsuranceInfo);
    boolean update(SysMedicalInsuranceDTO sysMedicalInsuranceDTO);
    boolean delete(List<Integer> idList);
}
