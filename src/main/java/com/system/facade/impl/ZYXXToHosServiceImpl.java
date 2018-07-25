package com.system.facade.impl;

import com.system.controller.util.ExceptionHandlerController;
import com.system.dao.DB2.test1.PtsVwCyxxDao;
import com.system.dao.DB2.test1.PtsVwZyxxDao;
import com.system.entity.*;
import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
import com.system.facade.ZYXXToHosService;
import com.system.pojo.CreateSysAreaInfo;
import com.system.pojo.CreateSysMedicalInsuranceInfo;
import com.system.service.SysAreaService;
import com.system.service.SysHospitalizationService;
import com.system.service.SysMedicalInsuranceService;
import com.system.util.database.DataSwitch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: 李景然
 * @Date: 2018/7/2 09:30
 * @Description:
 */
@Service("zyxxAndCYXXToHosService")
public class ZYXXToHosServiceImpl implements ZYXXToHosService {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
    @Resource
    private SysHospitalizationService sysHospitalizationService;

    @Resource
    private PtsVwZyxxDao ptsVwZyxxDao;

    @Resource
    private PtsVwCyxxDao ptsVwCyxxDao;

    @Resource
    private SysAreaService sysAreaService;

    @Resource
    private SysMedicalInsuranceService sysMedicalInsuranceService;

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public List<PtsVwZyxx> getZYXXList(Date startTime, Date endTime) {
        List<PtsVwZyxx> list = ptsVwZyxxDao.selectAll().stream().filter(item -> (item.getRyrq().after(startTime) && item.getRyrq().before(endTime))||(item.getRyrq().equals(startTime))||(item.getRyrq().equals(endTime))).collect(Collectors.toList());
        return list;
    }

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public List<PtsVwZyxx> getZYXXList() {
        List<PtsVwZyxx> list = ptsVwZyxxDao.selectAll();
        return list;
    }

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public List<PtsVwCyxx> getCYXXList(Date startTime, Date endTime) {
        List<PtsVwCyxx> list = ptsVwCyxxDao.selectAll().stream().filter(item -> item.getCyrq().after(startTime) && item.getCyrq().before(endTime)).collect(Collectors.toList());

        return list;
    }

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public PtsVwCyxx getCYXX(String zyh,int times){
        List<PtsVwCyxx> list=ptsVwCyxxDao.selectByExample(getExample(times,zyh));
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public boolean insertHos(PtsVwZyxx ptsVwZyxx) {
        SysHospitalization sysHospitalization = convertToSysHospitalization(ptsVwZyxx);
        return sysHospitalizationService.insert(sysHospitalization);
    }

    private SysHospitalization convertToSysHospitalization(PtsVwZyxx ptsVwZyxx) {
        if (null == ptsVwZyxx) {
            return null;
        }
        SysHospitalization result = new SysHospitalization();
        result.sethDate(ptsVwZyxx.getRyrq());
        result.setDcrName(ptsVwZyxx.getZzys());
        result.setpAge(ptsVwZyxx.getNl());
        result.sethId(ptsVwZyxx.getZyh());
        result.setpName(ptsVwZyxx.getXm());
        result.setpSex(ptsVwZyxx.getXb());
        result.sethBed(ptsVwZyxx.getRybch());

        //住院次数
        result.sethTimes(ptsVwZyxx.getZycs());
        //陪人数
        result.setEscortsNum(1);
        //访视状态
        result.setVisitStatus("未探访");

        //处理医保类型
        SysMedicalInsurance sysMedicalInsurance = sysMedicalInsuranceService.get(ptsVwZyxx.getYlfkfs());
        if (sysMedicalInsurance == null) {
            CreateSysMedicalInsuranceInfo createSysMedicalInsuranceInfo = new CreateSysMedicalInsuranceInfo();
            createSysMedicalInsuranceInfo.setValue(ptsVwZyxx.getYlfkfs());
            sysMedicalInsuranceService.insert(createSysMedicalInsuranceInfo);
            sysMedicalInsurance = sysMedicalInsuranceService.get(ptsVwZyxx.getYlfkfs());
        }
        result.setpInsur(sysMedicalInsurance.getId());

        //处理病区
        SysArea sysArea = sysAreaService.get(ptsVwZyxx.getRyks());
        if (sysArea == null) {
            CreateSysAreaInfo createSysAreaInfo = new CreateSysAreaInfo();
            createSysAreaInfo.setValue(ptsVwZyxx.getRyks());
            sysAreaService.insert(createSysAreaInfo);
            sysArea = sysAreaService.get(ptsVwZyxx.getRyks());
        }
        result.sethArea(sysArea.getId());

        //待确认,先统一设置成“住院”了
        result.setpStatus(1);

        return result;
    }

    private Example getExample(int times, String zyh) {
        Example example = new Example(PtsVwCyxx.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("zyh", zyh);
        criteria.andEqualTo("zycs", times);

        return example;
    }

}
