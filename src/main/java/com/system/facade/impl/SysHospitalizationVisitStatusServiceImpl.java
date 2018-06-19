package com.system.facade.impl;

import com.system.dao.SysHospitalizationDao;
import com.system.dao.SysHospitalizationLogDao;
import com.system.entity.SysHospitalization;
import com.system.entity.SysHospitalizationLog;
import com.system.facade.SysHospitalizationVisitStatusService;
import com.system.pojo.SysHospitalizationDTO;
import com.system.pojo.SysHospitalizationLogDTO;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("sysHospitalizationVisitStatusService")
public class SysHospitalizationVisitStatusServiceImpl implements SysHospitalizationVisitStatusService {
    @Resource
    private SysHospitalizationDao sysHospitalizationDao;
    @Resource
    private SysHospitalizationLogDao sysHospitalizationLogDao;

    /**
     * 建立在前端不会对病人一次探视未结束，就开始另一个探视的基础上
     * @param id
     * @param status
     */
    @Override
    public void updateHospitalizationVisitStatus(Long id, Integer status) {
        if (status != 0 && status != 1)
            throw new NoneUpdateException("status 输入有误！");
        // 修改住院信息表中id对应记录的探视状态
        SysHospitalization sysHospitalization = new SysHospitalization();
        sysHospitalization.setId(id);
        sysHospitalization.setVisitStatus(status == 0 ? "未探视" : "已探视");
        // 如果状态没有变化（也就是前台在重复点击“探视”或者“离开”），就忽略该行为
        Integer same = sysHospitalizationDao.selectCount(sysHospitalization);
        if (same == 1){
            throw new NoneUpdateException("该操作忽略：住院信息表中已是该状态，无需变化，同时操作日志中不记录该操作");
        }
        Integer changed = sysHospitalizationDao.updateByPrimaryKeySelective(sysHospitalization);

        if (changed != 1) { // 住院信息表中没有该记录，自然无需往log做记录
            throw new NoneUpdateException("住院信息表中没有该记录，请检查输入参数");
        }

        // 在住院信息表日志表中记录日志
        SysHospitalizationLog sysHospitalizationLog = new SysHospitalizationLog();
        sysHospitalizationLog.sethId(id);
        if (status == 1) { // 新来探视，往住院信息log表新增记录
            sysHospitalizationLog.setVisitTime(new Date());
            sysHospitalizationLogDao.insertSelective(sysHospitalizationLog);
        }
        else{ // 探视结束，修改id对应的住院信息log记录的探视离开时间
            List<SysHospitalizationLog> sysHospitalizationLogs = sysHospitalizationLogDao.selectByExample(getExample(sysHospitalizationLog));
            if (sysHospitalizationLogs != null && sysHospitalizationLogs.size() == 0){ // 没找到对应记录，怎么更新记录
                System.out.println("更新住院探视记录失败: size 为0");
                throw new NoneUpdateException("更新住院探视记录失败：sys_hospitalization_log没找到对应记录，怎么更新？");
            }
            if (sysHospitalizationLogs != null && sysHospitalizationLogs.size() > 1){ // 多于1条记录，出错了
                throw new NoneUpdateException("更新住院探视记录失败：sys_hospitalization_log中有 " + sysHospitalizationLogs.size() + " 条记录，更新哪个？");
            }
            sysHospitalizationLog.setId(sysHospitalizationLogs.get(0).getId());
            sysHospitalizationLog.sethId(null);
            sysHospitalizationLog.setExitTime(new Date());
            sysHospitalizationLogDao.updateByPrimaryKeySelective(sysHospitalizationLog);
        }
    }

    private Example getExample(SysHospitalizationLog sysHospitalizationLog) {
        Example example = new Example(SysHospitalizationLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(sysHospitalizationLog.gethId() != null){
            criteria.andEqualTo("hId", sysHospitalizationLog.gethId());
        }
        criteria.andIsNull("exitTime");

        return example;
    }
}
