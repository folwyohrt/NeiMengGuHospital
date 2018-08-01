package com.system.facade.impl;

import com.system.controller.util.ExceptionHandlerController;
import com.system.dao.DB2.test1.PtsVwCyxxDao;
import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
import com.system.entity.SysArea;
import com.system.entity.SysHospitalization;
import com.system.entity.SysMedicalInsurance;
import com.system.entity.SysOutHospital;
import com.system.facade.CYXXToOutHosService;
import com.system.pojo.CreateSysAreaInfo;
import com.system.pojo.CreateSysMedicalInsuranceInfo;
import com.system.service.*;
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
@Service("cyxxToOutHosServiceImpl")
public class CYXXToOutHosServiceImpl implements CYXXToOutHosService {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
    @Resource
    private SysHospitalizationService sysHospitalizationService;

    @Resource
    private SysOutHospitalService sysOutHospitalService;

    @Resource
    private PtsVwCyxxDao ptsVwCyxxDao;

    @Resource
    private SysAreaService sysAreaService;

    @Resource
    private SysMedicalInsuranceService sysMedicalInsuranceService;

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public List<PtsVwCyxx> getCYXXList(Date startTime, Date endTime) {
        List<PtsVwCyxx> list = ptsVwCyxxDao.selectByExample(getExample(startTime,endTime));
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
    public boolean insertOutHos(PtsVwCyxx ptsVwCyxx) {
        SysOutHospital sysOutHospital = convertToOutSysHospital(ptsVwCyxx);
        return sysOutHospitalService.insert(sysOutHospital);
    }

    private SysOutHospital convertToOutSysHospital(PtsVwCyxx ptsVwCyxx) {
        if (null == ptsVwCyxx) {
            return null;
        }
        SysOutHospital result = new SysOutHospital();
        result.sethOutDate(ptsVwCyxx.getCyrq());
        result.sethReckoningDate(ptsVwCyxx.getCyrq());
        result.setpBirthday(ptsVwCyxx.getCsrq());
        result.setpAge(ptsVwCyxx.getNl());
        result.sethId(ptsVwCyxx.getZyh());
        result.setpName(ptsVwCyxx.getXm());
        result.setpSex(ptsVwCyxx.getXb());
        //住院次数
        result.sethTimes(ptsVwCyxx.getZycs());
        result.sethDays(ptsVwCyxx.getZyts());
        //处理病区
        SysArea sysArea = sysAreaService.get(ptsVwCyxx.getRyks());
        if (sysArea == null) {
            CreateSysAreaInfo createSysAreaInfo = new CreateSysAreaInfo();
            createSysAreaInfo.setValue(ptsVwCyxx.getRyks());
            sysAreaService.insert(createSysAreaInfo);
            sysArea = sysAreaService.get(ptsVwCyxx.getRyks());
        }
        result.sethArea(sysArea.getId());
        return result;
    }

    private Example getExample(int times, String zyh) {
        Example example = new Example(PtsVwCyxx.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("zyh", zyh);
        criteria.andEqualTo("zycs", times);

        return example;
    }

    private Example getExample(Date startTime, Date endTime) {
        Example example = new Example(PtsVwCyxx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("cyrq",startTime,endTime);
        return example;
    }

}
