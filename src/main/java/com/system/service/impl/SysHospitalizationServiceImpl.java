package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.dao.SysHospitalizationDao;
import com.system.entity.*;
import com.system.pojo.*;
import com.system.service.*;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.Collator;
import java.text.ParseException;
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
    @DataSwitch(dataSource = "dataSource1")
    public SysHospitalization get(int times, String hId) {
        List<SysHospitalization> sysHospitalizationList = sysHospitalizationDao.selectByExample(getExample(times, hId));
        if (sysHospitalizationList != null && sysHospitalizationList.size() > 0) {
            return sysHospitalizationList.get(0);
        }
        return null;
    }

    @Override
    public PagingResult getPageList(PagingRequest request) {
        //设置分页参数
        Page page = PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);

        List<SysHospitalization> list = sysHospitalizationDao.selectAll();
        if (list.size() == 0) {
            throw new NoneGetException("没有查询到在院病人相关记录！");
        }
        list = getOrderedSysHospitalizations(request.getSort(), request.getSortOrder(), list);
        List<SysHospitalizationDTO> resultList = getSysHospitalizationDTOS(list);

        //获取分页之后的信息
        PagingResult pagingResult = new PagingResult((int) page.getTotal(), resultList);
        return pagingResult;
    }

    private List<SysHospitalization> getOrderedSysHospitalizations(String sort, String sortOrder, List<SysHospitalization> list) {
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(sortOrder)) {
            if (sort.equals("pName")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::getpName).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::getpName)).collect(Collectors.toList());
                }
            }
            if (sort.equals("hId")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::gethId).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::gethId)).collect(Collectors.toList());
                }
            }
            if (sort.equals("hBed")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::gethBed).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::gethBed)).collect(Collectors.toList());
                }
            }
            if (sort.equals("visitStatus")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::getVisitStatus).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::getVisitStatus)).collect(Collectors.toList());
                }
            }
            if (sort.equals("hDate")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::gethDate).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysHospitalization::gethDate)).collect(Collectors.toList());
                }
            }
        }
        return list;
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    //用于 后台 修改 出院状态
    public List<SysHospitalization> getList(int pStatus) {
        List<SysHospitalization> list = sysHospitalizationDao.selectAll().stream().filter(item -> item.getpStatus() == 1).collect(Collectors.toList());
        return list;
    }

    @Override
    public PagingResult getPageList(SysHospitalizationQuery query) {
        //设置分页参数
        Page page = PageHelper.startPage(query.getPageNum(), query.getPageSize(), true);
        List<SysHospitalization> list = sysHospitalizationDao.selectByExample(getExample(query));
        if (list != null && list.size() > 0) {
            //list = getOrderedSysHospitalizations(query.getSort(), query.getSortOrder(), list);
            //获取分页之后的信息
            List<SysHospitalizationDTO> resultList = getSysHospitalizationDTOS(list);
            PagingResult pagingResult = new PagingResult((int) page.getTotal(), resultList);
            return pagingResult;
        }
        throw new NoneGetException("没有查询到在院病人相关记录！");
    }

    @Override
    public List<String> getBedList(Integer areaId) {
        List<SysHospitalization> sysHospitalizationList = sysHospitalizationDao.selectByExample(getExample(areaId));
        HashSet<String> hashSet = sysHospitalizationList.stream()
                .map(sysHospitalization -> sysHospitalization.gethBed())
                .filter(str -> !str.equals("") && !str.equals(" "))
                .collect(Collectors.toCollection(HashSet::new));
        List<String> bedList = hashSet.stream()
                .sorted((str1, str2) -> str1.compareTo(str2))
                .collect(Collectors.toList());
        return bedList;
    }

    @Override
    public SysHospitalization getLatestRecordByhBed(String hBed) {
        List<SysHospitalization> sysHospitalizationList = sysHospitalizationDao.selectByExample(getExample(hBed))
                .stream()
                .sorted(Comparator.comparing(SysHospitalization::getGmtModified).reversed())
                .collect(Collectors.toList());
        return sysHospitalizationList.get(0);
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
    @DataSwitch(dataSource = "dataSource1")
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Long> idList) {
        for (Long anIdList : idList) {
            if (sysHospitalizationDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public boolean truncate() {
        sysHospitalizationDao.truncate();
        return true;
    }

    private final Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

    public List<String> getNameList(){
        List<String> list= sysHospitalizationDao.selectAll().stream().sorted(Comparator.comparing(SysHospitalization::getpName)).map(item->item.getpName()).distinct().collect(Collectors.toList());
        Collections.sort(list, CHINA_COMPARE);
        return list;
    }

    private Example getExample(SysHospitalizationQuery query) {
        Example example = new Example(SysHospitalization.class);
        String sort = query.getSort();
        for (int i = 0; i < sort.length(); i++) {
            if (Character.isUpperCase(sort.charAt(i))) {
                sort = sort.substring(0, i) + "_" + sort.substring(i, sort.length());
                break;
            }
        }
        String orderStr = sort + " " + query.getSortOrder();
        example.setOrderByClause(orderStr);
        Example.Criteria criteria = example.createCriteria();

        if (query.getpName() != "") {
            criteria.andLike("pName", "%" + query.getpName() + "%");
        }
        if (query.gethId() != "") {
            criteria.andLike("hId", "%" + query.gethId() + "%");
        }
        if (query.gethArea() != 0) {
            criteria.andEqualTo("hArea", query.gethArea());
        }
        if (query.gethBed() != "") {
            criteria.andLike("hBed", "%" + query.gethBed() + "%");
        }
        //筛选时间
        if (query.gethDate() != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBegin = null;
            Date dateEnd = null;
            try {
                dateBegin = sdf.parse(query.gethDate());
                dateEnd = sdf.parse(query.gethDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateEnd);
            cal.add(Calendar.DATE, 1);
            criteria.andBetween("hDate", dateBegin, cal.getTime());
        }
        return example;
    }

    private Example getExample(int times, String hId) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hId", hId);
        criteria.andEqualTo("hTimes", times);

        return example;
    }

    private Example getExample(int areaId) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hArea", areaId);
        return example;
    }

    private Example getExampleBypStatus(int id) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();
        if (id != 0) {
            criteria.andEqualTo("pStatus", id);
        }
        return example;
    }

    private Example getExample(String hBed) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hBed", hBed);
        return example;
    }


    private Example getExample(SysHospitalizationDTO sysHospitalizationDTO) {
        Example example = new Example(SysHospitalization.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pName", sysHospitalizationDTO.getpName());
        criteria.andEqualTo("hId", sysHospitalizationDTO.gethId());
        criteria.andEqualTo("hArea", sysHospitalizationDTO.gethArea());
        criteria.andEqualTo("nursingLevel", sysHospitalizationDTO.getNursingLevel());

        criteria.andEqualTo("escortsNum", sysHospitalizationDTO.getEscortsNum());
        criteria.andEqualTo("hBed", sysHospitalizationDTO.gethBed());
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
        try {
            SysArea sysArea = sysAreaService.get(area);
            return sysArea.getValue();
        } catch (Exception e) {
            throw new NoneGetException("areaId=" + area + "!not found value!");
        }
    }

    @Resource
    SysNursingLevelService sysNursingLevelService;

    private String getNursingLevelStr(int nursingLevel) {
        try {
            SysNursingLevel sysnursingLevel = sysNursingLevelService.get(nursingLevel);
            return sysnursingLevel.getValue();
        } catch (Exception e) {
            throw new NoneGetException("nursingLevelId=" + nursingLevel + "!not found value!");
        }
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
        sysHospitalizationDTO.setNursingLevelStr(getNursingLevelStr(sysHospitalization.getNursingLevel()));
        sysHospitalizationDTO.setpStatusStr(getPatientStatusStr(sysHospitalization.getpStatus()));
        sysHospitalizationDTO.setpInsurStr(getMedicalInsuranceStr(sysHospitalization.getpInsur()));
        sysHospitalizationDTO.sethDate(getDateStr(sysHospitalization.gethDate()));
        return sysHospitalizationDTO;
    }

}
