package com.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.dao.SysSurgeryDao;
import com.system.entity.*;
import com.system.entity.SqlServer.PtsVwSsxx;
import com.system.entity.SysSurgery;
import com.system.pojo.*;
import com.system.service.SysAreaService;
import com.system.service.SysSurgeryService;
import com.system.service.SysSurgeryStatusService;
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

import static com.system.util.tools.DateFormatHelper.*;

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
    public SysSurgeryDTO getDTO(long id) {
        SysSurgery result = sysSurgeryDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有此条信息！");
        }
        return getSysSurgeryDTO(result);
    }

    private SysSurgeryDTO getSysSurgeryDTO(SysSurgery result) {
        SysSurgeryDTO sysSurgeryDTO = convertToSysSurgeryDTO(result);
        sysSurgeryDTO.sethAreaStr(getAreaStr(result.gethArea()));
        sysSurgeryDTO.setSurgeryStatusStr(getSurgeryStatusStr(result.getSurgeryStatus()));
        sysSurgeryDTO.setSurgeryDatetime(getDateStr(result.getSurgeryDatetime()));
        return sysSurgeryDTO;
    }

    @Override
    public SysSurgery get(long id) {
        SysSurgery result = sysSurgeryDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有此条信息！");
        }
        return result;
    }

    @Override
    public SysSurgery get(String hId, Integer hTimes, String hXh) {
        // 查询mysql中的手术信息列表
        List<SysSurgery> sysSurgeryList = sysSurgeryDao.selectByExample(getSgExample(hId, hTimes, hXh));
        if (sysSurgeryList.size() > 0) {
            return sysSurgeryList.get(0);
        }
        return null;
    }

    private Example getSgExample(String hId, Integer hTimes, String hXh) {
        Example example = new Example(SysSurgery.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hId", hId);
        criteria.andEqualTo("hTimes", hTimes);
        criteria.andEqualTo("hXh", hXh);
        return example;
    }

    @Override
    public PagingResult getList(PagingRequest pagingRequest) {
        //设置分页参数
        Page page = PageHelper.startPage(pagingRequest.getPageNum(), pagingRequest.getPageSize(), true);

        Date todayDate = getTodayDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SysSurgeryQuery sysSurgeryQuery = new SysSurgeryQuery();
        sysSurgeryQuery.setSurgeryDatetime(sdf.format(todayDate));
        sysSurgeryQuery.sethArea(0);
        sysSurgeryQuery.sethId("");
        sysSurgeryQuery.setpName("");

        List<SysSurgery> list = sysSurgeryDao.selectByExample(getExample(sysSurgeryQuery));
        //list = getOrderedSysSurgerys(pagingRequest.getSort(), pagingRequest.getSortOrder(), list);
        List<SysSurgeryDTO> resultList = getSysSurgeryDTOS(list);
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到手术相关记录！");
        }

        //获取分页之后的信息
        PagingResult pagingResult = new PagingResult((int) page.getTotal(), resultList);
        return pagingResult;
    }

    private List<SysSurgery> getOrderedSysSurgerys(String sort, String sortOrder, List<SysSurgery> list) {
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(sortOrder)) {
            if (sort.equals("pName")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::getpName).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::getpName)).collect(Collectors.toList());
                }
            }
            if (sort.equals("hId")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::gethId).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::gethId)).collect(Collectors.toList());
                }
            }
            if (sort.equals("hBed")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::gethBed).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::gethBed)).collect(Collectors.toList());
                }
            }
            if (sort.equals("visitStatus")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::getVisitStatus).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::getVisitStatus)).collect(Collectors.toList());
                }
            }
            if (sort.equals("surgeryDatetime")) {
                if (sortOrder.equals("desc")) {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::getSurgeryDatetime).reversed()).collect(Collectors.toList());
                } else {
                    list = list.stream().sorted(Comparator.comparing(SysSurgery::getSurgeryDatetime)).collect(Collectors.toList());
                }
            }
        }
        return list;
    }

    private List<SysSurgeryDTO> getSysSurgeryDTOS(List<SysSurgery> list) {
        List<SysSurgeryDTO> resultList = new ArrayList<>();
        for (SysSurgery item : list) {
            SysSurgeryDTO sysSurgeryDTO = getSysSurgeryDTO(item);
            resultList.add(sysSurgeryDTO);
        }
        return resultList;
    }

    @Override
    public PagingResult getList(SysSurgeryQuery query) {
        //设置分页参数
        Page page = PageHelper.startPage(query.getPageNum(), query.getPageSize(), true);
        List<SysSurgery> list = sysSurgeryDao.selectByExample(getExample(query));
        if (list != null && list.size() > 0) {
            //list = getOrderedSysSurgerys(query.getSort(), query.getSortOrder(), list);
            //获取分页之后的信息
            List<SysSurgeryDTO> resultList = getSysSurgeryDTOS(list);
            PagingResult pagingResult = new PagingResult((int) page.getTotal(), resultList);
            return pagingResult;
        }
        throw new NoneGetException("没有查询到手术相关记录！");
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
    public boolean insert(SysSurgery sysSurgery) {
        List<SysSurgery> sysSurgeryList = sysSurgeryDao.selectByExample(getSgExample(sysSurgery));
        if (sysSurgeryList != null && sysSurgeryList.size() > 0) {
            throw new NoneSaveException("不能新增同一条记录");
        }
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
    public boolean update(SysSurgery sysSurgery) {
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

    private final Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

    public List<String> getNameList() {
        Date todayDate = getTodayDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<String> list = sysSurgeryDao.selectByExample(getExampleByDate(
                getTodayDate())).stream().sorted(Comparator.comparing(SysSurgery::getpName)).map(item -> item.getpName()).distinct().collect(Collectors.toList());
        Collections.sort(list, CHINA_COMPARE);
        return list;
    }

    private Example getExample(SysSurgeryQuery query) {
        Example example = new Example(SysSurgery.class);

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
        if (query.gethBed() != "") {
            criteria.andLike("hBed", "%" + query.gethBed() + "%");
        }
        if (query.gethArea() != 0) {
            criteria.andEqualTo("hArea", query.gethArea());
        }
        //筛选时间
        Calendar cal = Calendar.getInstance();
        if (query.getSurgeryDatetime() != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateBegin = null;
            Date dateEnd = null;
            try {
                dateBegin = sdf.parse(query.getSurgeryDatetime());
                dateEnd = sdf.parse(query.getSurgeryDatetime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            cal.setTime(dateEnd);
            cal.add(Calendar.DATE, 1);
            criteria.andBetween("surgeryDatetime", dateBegin, cal.getTime());
        } else {
            Date todayDate = getTodayDate();
            cal.setTime(todayDate);
            cal.add(Calendar.DATE, 1);
            criteria.andBetween("surgeryDatetime", todayDate, cal.getTime());
        }
        return example;
    }

    private Example getExampleByDate(Date todayDate) {
        Example example = new Example(SysSurgery.class);
        Example.Criteria criteria = example.createCriteria();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayDate);
        cal.add(Calendar.DATE, 1);
        criteria.andBetween("surgeryDatetime", todayDate, cal.getTime());
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

    private Example getSgExample(SysSurgery sysSurgery) {
        Example example = new Example(SysSurgery.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hId", sysSurgery.gethId());
        criteria.andEqualTo("hTimes", sysSurgery.gethTimes());
        criteria.andEqualTo("hXh", sysSurgery.gethXh());
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
        if (sysArea == null) {
            throw new NoneGetException("病区号：" + area + "在sys_area表中不存在！");
        }
        return sysArea.getValue();
    }

    @Resource
    SysSurgeryStatusService sysSurgeryStatusService;

    private String getSurgeryStatusStr(int id) {
        SysSurgeryStatus sysSurgeryStatus = sysSurgeryStatusService.get(id);
        return sysSurgeryStatus.getValue();
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
