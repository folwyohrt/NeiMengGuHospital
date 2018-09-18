package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.dao.SysOutHospitalDao;
import com.system.entity.*;
import com.system.pojo.*;
import com.system.service.*;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneGetException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.exception.controller.result.NoneSaveException;
import com.system.util.exception.controller.result.NoneUpdateException;
import org.apache.commons.lang3.StringUtils;
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

import static com.system.util.tools.DateFormatHelper.getDate;
import static com.system.util.tools.DateFormatHelper.getDateStr;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:54
 * @Description:
 */
@Service("sysOutHospitalService")
public class SysOutHospitalServiceImpl implements SysOutHospitalService {
    @Resource
    private SysOutHospitalDao sysOutHospitalDao;

    @Override
    public SysOutHospitalDTO get(long id) {
        SysOutHospital result = sysOutHospitalDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有此条信息！");
        }
        return getSysOutHospitalDTO(result);
    }

    @Override
    public SysOutHospital get(int times, String hId) {
        List<SysOutHospital> sysOutHospitalList = sysOutHospitalDao.selectByExample(getExample(times, hId));
        if (sysOutHospitalList != null && sysOutHospitalList.size() > 0) {
            return sysOutHospitalList.get(0);
        }
        return null;
    }
//
//    @Override
//    public PagingResult getPageList(PagingRequest request) {
//        //设置分页参数
//        Page page = PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);
//
//        List<SysOutHospital> list = sysOutHospitalDao.selectAll();
//        if (list.size() == 0) {
//            throw new NoneGetException("没有查询到出院病人相关记录！");
//        }
//        list = getOrderedSysOutHospitals(request.getSort(), request.getSortOrder(), list);
//        List<SysOutHospitalDTO> resultList = getSysOutHospitalDTOS(list);
//
//        //获取分页之后的信息
//        PagingResult pagingResult = new PagingResult((int) page.getTotal(), resultList);
//        return pagingResult;
//    }

    @Override
    public PagingResult getPageList(SysOutHospitalQuery query) {
        //设置分页参数
        Page page = PageHelper.startPage(query.getPageNum(), query.getPageSize(), true);
        List<SysOutHospital> list = sysOutHospitalDao.selectByExample(getExample(query));
        if (list != null && list.size() > 0) {
            //list = getOrderedSysOutHospitals(query.getSort(), query.getSortOrder(), list);
            //获取分页之后的信息
            List<SysOutHospitalDTO> resultList = getSysOutHospitalDTOS(list);
            PagingResult pagingResult = new PagingResult((int) page.getTotal(), resultList);
            return pagingResult;
        }
        throw new NoneGetException("没有查询到相关记录！");
    }

    private List<SysOutHospital> getOrderedSysOutHospitals(String sort, String sortOrder, List<SysOutHospital> list) {
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(sortOrder)) {
            if (sort.equals("pName")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysOutHospital::getpName).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysOutHospital::getpName)).collect(Collectors.toList());
                }
            }
            if (sort.equals("hId")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysOutHospital::gethId).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysOutHospital::gethId)).collect(Collectors.toList());
                }
            }
        }
        return list;
    }

    @Override
    public boolean insert(SysOutHospitalDTO sysOutHospitalDTO) {
        List<SysOutHospital> sysOutHospitalList = sysOutHospitalDao.selectByExample(getExample(sysOutHospitalDTO));
        if (sysOutHospitalList != null && sysOutHospitalList.size() > 0) {
            throw new NoneSaveException("不能新增同一条记录");
        }
        SysOutHospital sysOutHospital = convertToSysOutHospital(sysOutHospitalDTO);

        sysOutHospital.setpBirthday(getDate(sysOutHospitalDTO.getpBirthday()));
        sysOutHospital.sethReckoningDate(getDate(sysOutHospitalDTO.gethReckoningDate()));
        sysOutHospital.sethOutDate(getDate(sysOutHospitalDTO.gethOutDate()));
        return sysOutHospitalDao.insertSelective(sysOutHospital) > 0;
    }

    //住院号和住院次数 唯一确定一条记录
    @Override
    public boolean insert(SysOutHospital sysOutHospital) {
        SysOutHospital old = get(sysOutHospital.gethTimes(), sysOutHospital.gethId());
        if (old != null) {
            return true;
        }
        return sysOutHospitalDao.insertSelective(sysOutHospital) > 0;
    }

    @Override
    public boolean update(SysOutHospitalDTO sysOutHospitalDTO) {
        SysOutHospital sysOutHospital = convertToSysOutHospital(sysOutHospitalDTO);

        sysOutHospital.setpBirthday(getDate(sysOutHospitalDTO.getpBirthday()));
        sysOutHospital.sethOutDate(getDate(sysOutHospitalDTO.gethOutDate()));
        sysOutHospital.sethReckoningDate(getDate(sysOutHospitalDTO.gethReckoningDate()));
        if (sysOutHospitalDao.updateByPrimaryKeySelective(sysOutHospital) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public boolean update(SysOutHospital sysOutHospital) {
        if (sysOutHospitalDao.updateByPrimaryKeySelective(sysOutHospital) > 0) {
            return true;
        }
        throw new NoneUpdateException();
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Long> idList) {
        for (Long anIdList : idList) {
            if (sysOutHospitalDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }

    private final Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

    public List<String> getNameList(int hArea){
        List<String> list= sysOutHospitalDao.selectByExample(getExample(hArea)).stream().sorted(Comparator.comparing(SysOutHospital::getpName)).map(item->item.getpName()).distinct().collect(Collectors.toList());
        Collections.sort(list, CHINA_COMPARE);
        return list;
    }

    public List<SysOutHospitalDTO> getListByName(List<String> names){
        List<SysOutHospitalDTO> list = sysOutHospitalDao.selectByExample(getExample(names)).stream().sorted(Comparator.comparing(SysOutHospital::gethOutDate).reversed()).map(this::getSysOutHospitalDTO).collect(Collectors.toList());
        return list;
    }

    private Example getExample(List<String> names) {
        Example example = new Example(SysOutHospital.class);
        Example.Criteria criteria = example.createCriteria();
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> iterator = arrayList.iterator();
        criteria.andIn("pName", names);
        return example;
    }

    private Example getExample(SysOutHospitalQuery query) {
        Example example = new Example(SysOutHospital.class);

        String sort=query.getSort();
        for (int i=0;i<sort.length();i++){
            if(Character.isUpperCase(sort.charAt(i))){
                sort=sort.substring(0,i)+"_"+sort.substring(i,sort.length());
                break;
            }
        }
        String orderStr=sort+" "+query.getSortOrder();
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
        //筛选时间
        if (query.gethOutDate() != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBegin = null;
            Date dateEnd = null;
            try {
                dateBegin = sdf.parse(query.gethOutDate());
                dateEnd = sdf.parse(query.gethOutDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateEnd);
            cal.add(Calendar.DATE, 1);
            criteria.andBetween("hOutDate", dateBegin, cal.getTime());
        }
        return example;
    }

    private Example getExample(int times, String hId) {
        Example example = new Example(SysOutHospital.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hId", hId);
        criteria.andEqualTo("hTimes", times);
        return example;
    }

    private Example getExample(int areaId) {
        Example example = new Example(SysOutHospital.class);
        Example.Criteria criteria = example.createCriteria();
        if(areaId!=0){
            criteria.andEqualTo("hArea", areaId);
        }
        return example;
    }

    private Example getExample(String hBed) {
        Example example = new Example(SysOutHospital.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hBed", hBed);
        return example;
    }


    private Example getExample(SysOutHospitalDTO sysOutHospitalDTO) {
        Example example = new Example(SysOutHospital.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pName", sysOutHospitalDTO.getpName());
        criteria.andEqualTo("hId", sysOutHospitalDTO.gethId());
        criteria.andEqualTo("hArea", sysOutHospitalDTO.gethArea());
        criteria.andEqualTo("pAge", sysOutHospitalDTO.getpAge());
        criteria.andEqualTo("pSex", sysOutHospitalDTO.getpSex());

        Date pBirthday = getDate(sysOutHospitalDTO.getpBirthday());
        criteria.andEqualTo("pBirthday", pBirthday);

        Date hOutDate = getDate(sysOutHospitalDTO.gethOutDate());
        criteria.andEqualTo("hOutDate", hOutDate);

        Date hReckoningDate = getDate(sysOutHospitalDTO.gethReckoningDate());
        criteria.andEqualTo("hReckoningDate", hReckoningDate);

        return example;
    }

    private SysOutHospitalDTO convertToSysOutHospitalDTO(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysOutHospitalDTO resultDTO = new SysOutHospitalDTO();
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

    private SysOutHospital convertToSysOutHospital(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysOutHospital result = new SysOutHospital();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

    private List<SysOutHospitalDTO> getSysOutHospitalDTOS(List<SysOutHospital> list) {
        List<SysOutHospitalDTO> resultList = new ArrayList<>();
        for (SysOutHospital item : list) {
            SysOutHospitalDTO sysOutHospitalDTO = getSysOutHospitalDTO(item);
            resultList.add(sysOutHospitalDTO);
        }
        return resultList;
    }

    private SysOutHospitalDTO getSysOutHospitalDTO(SysOutHospital sysOutHospital) {
        SysOutHospitalDTO sysOutHospitalDTO = convertToSysOutHospitalDTO(sysOutHospital);
        sysOutHospitalDTO.sethAreaStr(getAreaStr(sysOutHospital.gethArea()));
        sysOutHospitalDTO.sethOutDate(getDateStr(sysOutHospital.gethOutDate()));
        sysOutHospitalDTO.sethReckoningDate(getDateStr(sysOutHospital.gethReckoningDate()));
        sysOutHospitalDTO.setpBirthday(getDateStr(sysOutHospital.getpBirthday()));
        return sysOutHospitalDTO;
    }

}
