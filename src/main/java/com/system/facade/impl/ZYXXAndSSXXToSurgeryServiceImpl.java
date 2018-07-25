package com.system.facade.impl;

import com.system.controller.util.ExceptionHandlerController;
import com.system.dao.DB2.test1.PtsVwCyxxDao;
import com.system.dao.DB2.test1.PtsVwZyxxDao;
import com.system.dao.SqlServer.PtsVwSsxxDao;
import com.system.dao.SysSurgeryDao;
import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
import com.system.entity.SqlServer.PtsVwSsxx;
import com.system.entity.SysArea;
import com.system.entity.SysSurgery;
import com.system.entity.SysSurgeryStatus;
import com.system.facade.ZYXXAndSSXXToSurgeryService;
import com.system.pojo.CreateSysAreaInfo;
import com.system.pojo.CreateSysSurgeryStatusInfo;
import com.system.service.SysAreaService;
import com.system.service.SysSurgeryService;
import com.system.service.SysSurgeryStatusService;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneSaveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Create by henghhh on 2018.7.2
 */
@Service("zyxxAndSSXXToSurgeryService")
public class ZYXXAndSSXXToSurgeryServiceImpl implements ZYXXAndSSXXToSurgeryService {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Resource
    private PtsVwZyxxDao ptsVwZyxxDao;
    @Resource
    private PtsVwCyxxDao ptsVwCyxxDao;

    @Resource
    private PtsVwSsxxDao ptsVwSsxxDao;

    @Resource
    private SysSurgeryDao sysSurgeryDao;

    @Resource
    private SysSurgeryService sysSurgeryService;

    @Resource
    private SysAreaService sysAreaService;

    @Resource
    private SysSurgeryStatusService sysSurgeryStatusService;

    @Override
    @DataSwitch(dataSource = "dataSource5")
    public List<PtsVwSsxx> getAllSSXXList() {
        List<PtsVwSsxx> ssxxes = ptsVwSsxxDao.selectAll();
        if (ssxxes == null || ssxxes.size() == 0) {
            throw new NoneGetException("获取手术信息失败");
        }
        return ssxxes;
    }

    @Override
    @DataSwitch(dataSource = "dataSource5")
    public List<PtsVwSsxx> getSSXXListBySgDate(Date fromDate, Date toDate) {
        List<PtsVwSsxx> ssxxes = ptsVwSsxxDao.selectByExample(getSsxxExample(fromDate, toDate));
        if (ssxxes == null || ssxxes.size() == 0) {
            throw new NoneGetException("获取手术信息失败");
        }
        return ssxxes;
    }

    @Override
    @DataSwitch(dataSource = "dataSource5")
    public synchronized PtsVwSsxx getSSXX(String zyh, String zycs, String xh) {
        List<PtsVwSsxx> ssxxes = ptsVwSsxxDao.selectByExample(getSsxxExample(zyh, zycs, xh));
        if (ssxxes == null || ssxxes.size() == 0) {
            //System.out.printf("住院号：%s，住院次数：%s\n", zyh, zycs);
            throw new NoneGetException("zyh: " + zyh + "zycs: " + zycs + "xh: " + xh + " 获取手术信息失败");
        }
        return ssxxes.get(0);
    }

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public synchronized PtsVwZyxx getZyxx(String zyh, String zycs) {
        List<PtsVwZyxx> zyxxList = ptsVwZyxxDao.selectByExample(getZyxxExample(zyh, zycs));
        if (zyxxList == null || zyxxList.size() == 0) {
            //System.out.printf("住院号：%s，住院次数：%s\n", zyh, zycs);
            return null;
        }
        return zyxxList.get(0);
    }

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public PtsVwCyxx getCyxx(String zyh, String zycs) {
        List<PtsVwCyxx> cyxxList = ptsVwCyxxDao.selectByExample(getCyxxExample(zyh, zycs));
        if (cyxxList == null || cyxxList.size() == 0) {
            //System.out.printf("住院号：%s，住院次数：%s\n", zyh, zycs);
            return null;
        }
        return cyxxList.get(0);
    }

    @Override
    @DataSwitch(dataSource="dataSource1")
    public synchronized String insertOrUpdateSurgery(PtsVwSsxx ssxx, PtsVwZyxx zyxx, PtsVwCyxx cyxx) {
        if (ssxx == null) {
            throw new NoneSaveException("手术信息为空！");
        }
        // 查询mysql中的手术信息列表
        List<SysSurgery> sysSurgeryList = sysSurgeryDao.selectByExample(getSgExample(ssxx));
        // 已经存在该信息，无需插入，但可能需要更新手术日期
        if (sysSurgeryList.size() > 0) {
            // 需要更新 && 有更新
            if (sysSurgeryList.get(0).getSurgeryDatetime() == null && ssxx.getPCSJ() != null) {
                if (updateSurgeryDate(ssxx)) {
                    return "update";
                }
            }
        }
        // 没有该信息，需要插入
        else {
            if (insertSurgery(ssxx, zyxx, cyxx)) {
                return "insert";
            }
        }
        return "none";
    }

    @Override
    @DataSwitch(dataSource="dataSource1")
    public synchronized boolean updateSurgeryDate(PtsVwSsxx ssxx) {
        SysSurgery sysSurgery = new SysSurgery();
        sysSurgery.setSurgeryDatetime(ssxx.getPCSJ());
        return sysSurgeryDao.updateByExampleSelective(sysSurgery, getSgExample(ssxx)) > 0;
    }

    @Override
    @DataSwitch(dataSource="dataSource1")
    public synchronized boolean insertSurgery(PtsVwSsxx ssxx, PtsVwZyxx zyxx, PtsVwCyxx cyxx) {
        SysSurgery sysSurgery = new SysSurgery();
        // 填充来自手术视图的信息
        supplySSXX(sysSurgery, ssxx);
        // 填充来自住院视图的信息
        if (zyxx != null) {
            supplyWithZyxx(sysSurgery, zyxx);
        }
        // 填充来自出院视图的信息
        else if (cyxx != null) {
            supplyWithCyxx(sysSurgery, cyxx);
        }
        // 填充默认信息
        else {
            supplyWithEmpty(sysSurgery);
        }
        return sysSurgeryService.insert(sysSurgery);
    }


    private Example getSgExample(PtsVwSsxx ssxx) {
        Example example = new Example(SysSurgery.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hId", ssxx.getZYH());
        criteria.andEqualTo("hTimes", Integer.parseInt(ssxx.getZYCS()));
        criteria.andEqualTo("hXh", ssxx.getXH());
        return example;
    }

    private Example getSsxxExample(String zyh, String zycs, String xh) {
        Example example = new Example(PtsVwSsxx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("zyh", zyh);
        criteria.andEqualTo("zycs", zycs);
        criteria.andEqualTo("xh", xh);
        return example;
    }

    private Example getSsxxExample(Date fromDate, Date toDate) {
        Example example = new Example(PtsVwSsxx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("PCSJ", fromDate, toDate);
        return example;
    }

    private Example getZyxxExample(String zyh, String zycs) {
        Example example = new Example(PtsVwZyxx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("zyh", zyh);
        criteria.andEqualTo("zycs", Integer.parseInt(zycs));
        return example;
    }

    private Example getCyxxExample(String zyh, String zycs) {
        Example example = new Example(PtsVwCyxx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("zyh", zyh);
        criteria.andEqualTo("zycs", Integer.parseInt(zycs));
        return example;
    }

    private SysSurgery supplyWithZyxx (SysSurgery sysSurgery, PtsVwZyxx zyxx) {
        // 年龄
        sysSurgery.setpAge(zyxx.getNl());
        // 入院病床号
        sysSurgery.sethBed(zyxx.getRybch());
        //处理病区
        String ryks = zyxx.getRyks() == null ? "-" : zyxx.getRyks();
        SysArea sysArea = sysAreaService.get(ryks);
        if (sysArea == null) {
            CreateSysAreaInfo createSysAreaInfo = new CreateSysAreaInfo();
            createSysAreaInfo.setValue(zyxx.getRyks());
            sysAreaService.insert(createSysAreaInfo);
            sysArea = sysAreaService.get(zyxx.getRyks());
        }
        sysSurgery.sethArea(sysArea.getId());
        return sysSurgery;
    }

    private SysSurgery supplyWithCyxx (SysSurgery sysSurgery, PtsVwCyxx cyxx) {
        // 年龄
        sysSurgery.setpAge(cyxx.getNl());
        // 入院病床号 出院信息无法获取病床号，置空
        sysSurgery.sethBed("-");
        //处理病区
        String ryks = cyxx.getRyks() == null ? "-" : cyxx.getRyks();
        SysArea sysArea = sysAreaService.get(ryks);
        if (sysArea == null) {
            CreateSysAreaInfo createSysAreaInfo = new CreateSysAreaInfo();
            createSysAreaInfo.setValue(cyxx.getRyks());
            sysAreaService.insert(createSysAreaInfo);
            sysArea = sysAreaService.get(cyxx.getRyks());
        }
        sysSurgery.sethArea(sysArea.getId());
        return sysSurgery;
    }

    private SysSurgery supplyWithEmpty (SysSurgery sysSurgery) {
        // 年龄
        sysSurgery.setpAge("-");
        // 入院病床号
        sysSurgery.sethBed("-");
        //处理病区
        String areaName = "-";
        SysArea sysArea = sysAreaService.get(areaName);
        if (sysArea == null) {
            CreateSysAreaInfo createSysAreaInfo = new CreateSysAreaInfo();
            createSysAreaInfo.setValue(areaName);
            sysAreaService.insert(createSysAreaInfo);
            sysArea = sysAreaService.get(areaName);
        }
        sysSurgery.sethArea(sysArea.getId());
        return sysSurgery;
    }

    private SysSurgery supplySSXX (SysSurgery sysSurgery, PtsVwSsxx ssxx) {
        sysSurgery.sethId(ssxx.getZYH());
        sysSurgery.sethTimes(Integer.parseInt(ssxx.getZYCS()));
        sysSurgery.setpName(ssxx.getXM());
        sysSurgery.setpSex(ssxx.getXB());
        sysSurgery.sethXh(ssxx.getXH());
        // 手术日期
        if (ssxx.getPCSJ() != null)
            sysSurgery.setSurgeryDatetime(ssxx.getPCSJ());
        // 术前诊断
        if (ssxx.getSQZD() != null)
            sysSurgery.setSurgeryPodx(ssxx.getSQZD());
        // 手术类型
        String sslx = ssxx.getSSLX() == null ? "-" : ssxx.getSSLX();
        SysSurgeryStatus sysSurgeryStatus = sysSurgeryStatusService.get(sslx);
        if (sysSurgeryStatus == null) {
            CreateSysSurgeryStatusInfo createSysSurgeryStatusInfo = new CreateSysSurgeryStatusInfo();
            createSysSurgeryStatusInfo.setValue(sslx);
            sysSurgeryStatusService.insert(createSysSurgeryStatusInfo);
            sysSurgeryStatus = sysSurgeryStatusService.get(sslx);
        }
        sysSurgery.setSurgeryStatus(sysSurgeryStatus.getId());
        //陪人数
        sysSurgery.setEscortsNum(1);
        //访视状态
        sysSurgery.setVisitStatus("未探访");
        return sysSurgery;
    }

}
