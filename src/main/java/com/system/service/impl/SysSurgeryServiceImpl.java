package com.system.service.impl;

import com.system.dao.SysSurgeryDao;
import com.system.entity.SysArea;
import com.system.entity.SysSurgery;
import com.system.entity.SysSurgery;
import com.system.entity.SysUser;
import com.system.pojo.*;
import com.system.service.SysAreaService;
import com.system.service.SysSurgeryService;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.system.util.tools.DateFormatHelper.getDate;
import static com.system.util.tools.DateFormatHelper.getDateFormat;
import static com.system.util.tools.DateFormatHelper.getDateStr;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:45
 * @Description:
 */
@Service("sysSurgeryService")
public class SysSurgeryServiceImpl implements SysSurgeryService {

    @Resource
    private SysSurgeryDao sysSurgeryDao;

    @Override
    public SysSurgeryDTO get(long id) {
        SysSurgery result = sysSurgeryDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有此条信息！");
        }
        SysSurgeryDTO sysSurgeryDTO = convertToSysSurgeryDTO(result);
        sysSurgeryDTO.sethAreaStr(getAreaStr(result.gethArea()));
        sysSurgeryDTO.setSurgeryDatetime(getDateStr(result.getSurgeryDatetime()));
        return sysSurgeryDTO;
    }

    @Override
    public List<SysSurgeryDTO> getList() {
        List<SysSurgery> list = sysSurgeryDao.selectAll().stream().sorted(Comparator.comparing(SysSurgery::getGmtModified).reversed()).collect(Collectors.toList());
        List<SysSurgeryDTO> resultList = new ArrayList<>();
        for (SysSurgery item : list) {
            SysSurgeryDTO sysSurgeryDTO = convertToSysSurgeryDTO(item);
            sysSurgeryDTO.sethAreaStr(getAreaStr(item.gethArea()));
            sysSurgeryDTO.setSurgeryDatetime(getDateStr(item.getSurgeryDatetime()));
            resultList.add(sysSurgeryDTO);
        }
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到用户相关记录！");
        }
        return resultList;
    }

    @Override
    public List<SysSurgeryDTO> getList(SysSurgeryQuery sysSurgeryQuery) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
        List<SysSurgery> list = sysSurgeryDao.selectByExample(getExample(sysSurgeryQuery)).stream().sorted(Comparator.comparing
                (SysSurgery::getGmtModified).reversed()).filter(item -> {
            if (sysSurgeryQuery.getSurgeryDatetime() != ""&&!simpleDateFormat.format(item.getSurgeryDatetime()).substring(0, 10).equals(sysSurgeryQuery.getSurgeryDatetime())) {
            return false;
            }
            return true;
        }).collect(Collectors.toList());
        List<SysSurgeryDTO> resultList = new ArrayList<>();
        for (SysSurgery item : list) {
            SysSurgeryDTO sysSurgeryDTO = convertToSysSurgeryDTO(item);
            sysSurgeryDTO.sethAreaStr(getAreaStr(item.gethArea()));
            sysSurgeryDTO.setSurgeryDatetime(getDateStr(item.getSurgeryDatetime()));
            resultList.add(sysSurgeryDTO);
        }
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到用户相关记录！");
        }
        return resultList;
    }

    @Override
    public boolean insert(SysSurgeryDTO sysSurgeryDTO) {
        List<SysSurgery> sysSurgeryList = sysSurgeryDao.selectByExample(getExample(sysSurgeryDTO));
        if (sysSurgeryList != null && sysSurgeryList.size() > 0) {
            throw new NoneSaveException("不能新增同一条记录");
        }
        SysSurgery sysSurgery = convertToSysSurgery(sysSurgeryDTO);

        sysSurgery.setSurgeryDatetime(getDate(sysSurgeryDTO.getSurgeryDatetime()));
        return sysSurgeryDao.insertSelective(sysSurgery) > 0;
    }

    @Override
    public boolean update(SysSurgeryDTO sysSurgeryDTO) {
        SysSurgery sysSurgery = convertToSysSurgery(sysSurgeryDTO);

        sysSurgery.setSurgeryDatetime(getDate(sysSurgeryDTO.getSurgeryDatetime()));
        if (sysSurgeryDao.updateByPrimaryKeySelective(sysSurgery) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Long> idList) {
        for (Long anIdList : idList) {
            if (sysSurgeryDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }

    private Example getExample(SysSurgeryQuery sysSurgeryQuery) {
        Example example = new Example(SysSurgery.class);
        Example.Criteria criteria = example.createCriteria();

        if (sysSurgeryQuery.getpName() != "") {
            criteria.andEqualTo("pName", sysSurgeryQuery.getpName());
        }
        if (sysSurgeryQuery.gethId() != "") {
            criteria.andEqualTo("hId", sysSurgeryQuery.gethId());
        }
        if (sysSurgeryQuery.gethArea() != 0) {
            criteria.andEqualTo("hArea", sysSurgeryQuery.gethArea());
            ;
        }
        //不能查找时间
//        if(sysSurgeryQuery.getSurgeryDatetime()!=""){
//            Date date = getDate(sysSurgeryQuery. getSurgeryDatetime());
//            criteria.andEqualTo("surgeryDatetime", date);
//        }
        return example;
    }

    private Example getExample(SysSurgeryDTO sysSurgeryDTO) {
        Example example = new Example(SysSurgery.class);
        Example.Criteria criteria = example.createCriteria();
        // 应对输入数据做判空判断
        criteria.andEqualTo("pName", sysSurgeryDTO.getpName());
        criteria.andEqualTo("hId", sysSurgeryDTO.gethId());
        criteria.andEqualTo("hArea", sysSurgeryDTO.gethArea());

        criteria.andEqualTo("escortsNum", sysSurgeryDTO.getEscortsNum());
        criteria.andEqualTo("hBed", sysSurgeryDTO.gethBed());
        criteria.andEqualTo("hId", sysSurgeryDTO.gethId());
        criteria.andEqualTo("pAge", sysSurgeryDTO.getpAge());
        criteria.andEqualTo("pSex", sysSurgeryDTO.getpSex());
        criteria.andEqualTo("surgeryPodx", sysSurgeryDTO.getSurgeryPodx());
        criteria.andEqualTo("visitStatus", sysSurgeryDTO.getVisitStatus());
        criteria.andEqualTo("surgeryStatus", sysSurgeryDTO.getSurgeryStatus());

        Date date = getDate(sysSurgeryDTO.getSurgeryDatetime());
        criteria.andEqualTo("surgeryDatetime", date);
        return example;
    }

    private SysSurgeryDTO convertToSysSurgeryDTO(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysSurgeryDTO resultDTO = new SysSurgeryDTO();
        BeanUtils.copyProperties(inputObject, resultDTO);
        return resultDTO;
    }

    @Resource
    SysAreaService sysAreaService;

    private String getAreaStr(int area) {
        SysArea sysArea = sysAreaService.get(area);
        if(sysArea == null){
            throw new NoneGetException("病区号：" + area + "在sys_area表中不存在！");
        }
        return sysArea.getValue();
    }

    private SysSurgery convertToSysSurgery(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysSurgery result = new SysSurgery();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }
}
