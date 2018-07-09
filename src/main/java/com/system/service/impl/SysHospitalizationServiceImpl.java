package com.system.service.impl;

import com.system.dao.SysHospitalizationDao;
import com.system.entity.SysArea;
import com.system.entity.SysHospitalization;
import com.system.entity.SysMedicalInsurance;
import com.system.entity.SysPatientStatus;
import com.system.pojo.SysHospitalizationDTO;
import com.system.pojo.SysHospitalizationQuery;
import com.system.service.SysAreaService;
import com.system.service.SysHospitalizationService;
import com.system.service.SysMedicalInsuranceService;
import com.system.service.SysPatientStatusService;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.system.util.tools.DateFormatHelper.*;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:54
 * @Description:
 */
@Service("sysHospitalizationService")
public class SysHospitalizationServiceImpl implements SysHospitalizationService {
    @Resource
    private SysHospitalizationDao sysHospitalizationDao;

    @Override
    public SysHospitalizationDTO get(long id) {
        SysHospitalization result = sysHospitalizationDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有此条信息！");
        }
        return getSysHospitalizationDTO(result);
    }

    @Override
    public SysHospitalization get(int times, String hId) {
        List<SysHospitalization> sysHospitalizationList = sysHospitalizationDao.selectByExample(getExample(times, hId));
        if (sysHospitalizationList != null && sysHospitalizationList.size() > 0) {
            return sysHospitalizationList.get(0);
        }
        return null;
    }

    @Override
    public List<SysHospitalizationDTO> getList() {
        List<SysHospitalization> list = sysHospitalizationDao.selectAll().stream().sorted(Comparator.comparing(SysHospitalization::getGmtModified).reversed()).collect(Collectors.toList());
        List<SysHospitalizationDTO> resultList = getSysHospitalizationDTOS(list);
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到用户相关记录！");
        }
        return resultList;
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public List<SysHospitalization> getList(int pStatus){
        List<SysHospitalization> list = sysHospitalizationDao.selectAll().stream().filter(item->item.getpStatus()==1).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<SysHospitalizationDTO> getList(SysHospitalizationQuery sysHospitalizationQuery) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
        List<SysHospitalization> list = sysHospitalizationDao.selectByExample(getExample(sysHospitalizationQuery)).stream().sorted(Comparator.comparing
                (SysHospitalization::getGmtModified).reversed()).filter(item -> {
            if (sysHospitalizationQuery.gethDate() != "" && !simpleDateFormat.format(item.gethDate()).substring(0, 10).equals(sysHospitalizationQuery.gethDate())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        List<SysHospitalizationDTO> resultList = getSysHospitalizationDTOS(list);
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到用户相关记录！");
        }
        return resultList;
    }

    @Override
    public boolean insert(SysHospitalizationDTO sysHospitalizationDTO) {
        List<SysHospitalization> sysHospitalizationList = sysHospitalizationDao.selectByExample(getExample(sysHospitalizationDTO));
        if (sysHospitalizationList != null && sysHospitalizationList.size() > 0) {
            throw new NoneSaveException("不能新增同一条记录");
        }
        SysHospitalization sysHospitalization = convertToSysHospitalization(sysHospitalizationDTO);

        sysHospitalization.sethDate(getDate(sysHospitalizationDTO.gethDate()));
        return sysHospitalizationDao.insertSelective(sysHospitalization) > 0;
    }

    //住院号和住院次数 唯一确定一条记录
    @Override
    public boolean insert(SysHospitalization sysHospitalization) {
        SysHospitalization old = get(sysHospitalization.gethTimes(), sysHospitalization.gethId());
        if (old != null) {
            return true;
        }
        return sysHospitalizationDao.insertSelective(sysHospitalization) > 0;
    }

    @Override
    public boolean update(SysHospitalizationDTO sysHospitalizationDTO) {
        SysHospitalization sysHospitalization = convertToSysHospitalization(sysHospitalizationDTO);

        sysHospitalization.sethDate(getDate(sysHospitalizationDTO.gethDate()));
        if (sysHospitalizationDao.updateByPrimaryKeySelective(sysHospitalization) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public boolean update(SysHospitalization sysHospitalization) {
        if (sysHospitalizationDao.updateByPrimaryKeySelective(sysHospitalization) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Long> idList) {
        for (Long anIdList : idList) {
            if (sysHospitalizationDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }

    private Example getExample(SysHospitalizationQuery sysHospitalizationQuery) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();

        if (sysHospitalizationQuery.getpName() != "") {
            criteria.andEqualTo("pName", sysHospitalizationQuery.getpName());
        }
        if (sysHospitalizationQuery.gethId() != "") {
            criteria.andEqualTo("hId", sysHospitalizationQuery.gethId());
        }
        if (sysHospitalizationQuery.gethArea() != 0) {
            criteria.andEqualTo("hArea", sysHospitalizationQuery.gethArea());
            ;
        }
        //查不了时间
//        if (sysHospitalizationQuery.gethDate() != "") {
//            Date date = getDate(sysHospitalizationQuery.gethDate());
//            criteria.andEqualTo("hDate", date);
//        }
        return example;
    }

    private Example getExample(int times, String hId) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("hId", hId);
        criteria.andEqualTo("hTimes", times);

        return example;
    }

    private Example getExample(SysHospitalizationDTO sysHospitalizationDTO) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pName", sysHospitalizationDTO.getpName());
        criteria.andEqualTo("hId", sysHospitalizationDTO.gethId());
        criteria.andEqualTo("hArea", sysHospitalizationDTO.gethArea());

        criteria.andEqualTo("escortsNum", sysHospitalizationDTO.getEscortsNum());
        criteria.andEqualTo("hBed", sysHospitalizationDTO.gethBed());
        criteria.andEqualTo("hId", sysHospitalizationDTO.gethId());
        criteria.andEqualTo("pAge", sysHospitalizationDTO.getpAge());
        criteria.andEqualTo("pSex", sysHospitalizationDTO.getpSex());
        criteria.andEqualTo("pStatus", sysHospitalizationDTO.getpStatus());
        criteria.andEqualTo("visitStatus", sysHospitalizationDTO.getVisitStatus());
        criteria.andEqualTo("pInsur", sysHospitalizationDTO.getpInsur());
        criteria.andEqualTo("dcrName", sysHospitalizationDTO.getDcrName());

        Date date = getDate(sysHospitalizationDTO.gethDate());
        criteria.andEqualTo("hDate", date);
        return example;
    }

    private SysHospitalizationDTO convertToSysHospitalizationDTO(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysHospitalizationDTO resultDTO = new SysHospitalizationDTO();
        BeanUtils.copyProperties(inputObject, resultDTO);
        return resultDTO;
    }

    @Resource
    SysAreaService sysAreaService;

    private String getAreaStr(int area) {
        SysArea sysArea = sysAreaService.get(area);
        return sysArea.getValue();
    }

    @Resource
    SysPatientStatusService sysPatientStatusService;

    private String getPatientStatusStr(int id) {
        SysPatientStatus sysPatientStatus = sysPatientStatusService.get(id);
        return sysPatientStatus.getValue();
    }

    @Resource
    SysMedicalInsuranceService sysMedicalInsuranceService;

    private String getMedicalInsuranceStr(int id) {
        SysMedicalInsurance sysPatientStatus = sysMedicalInsuranceService.get(id);
        return sysPatientStatus.getValue();
    }

    private SysHospitalization convertToSysHospitalization(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysHospitalization result = new SysHospitalization();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    private List<SysHospitalizationDTO> getSysHospitalizationDTOS(List<SysHospitalization> list) {
        List<SysHospitalizationDTO> resultList = new ArrayList<>();
        for (SysHospitalization item : list) {
            SysHospitalizationDTO sysHospitalizationDTO = getSysHospitalizationDTO(item);
            resultList.add(sysHospitalizationDTO);
        }
        return resultList;
    }

    private SysHospitalizationDTO getSysHospitalizationDTO(SysHospitalization sysHospitalization) {
        SysHospitalizationDTO sysHospitalizationDTO = convertToSysHospitalizationDTO(sysHospitalization);
        sysHospitalizationDTO.sethAreaStr(getAreaStr(sysHospitalization.gethArea()));
        sysHospitalizationDTO.setpStatusStr(getPatientStatusStr(sysHospitalization.getpStatus()));
        sysHospitalizationDTO.setpInsurStr(getMedicalInsuranceStr(sysHospitalization.getpInsur()));
        sysHospitalizationDTO.sethDate(getDateStr(sysHospitalization.gethDate()));
        return sysHospitalizationDTO;
    }

}
