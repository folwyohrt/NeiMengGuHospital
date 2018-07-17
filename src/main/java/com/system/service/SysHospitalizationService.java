package com.system.service;

import com.system.entity.SysHospitalization;
import com.system.pojo.PagingRequest;
import com.system.pojo.PagingResult;
import com.system.pojo.SysHospitalizationDTO;
import com.system.pojo.SysHospitalizationQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 09:48
 * @Description:
 */
public interface SysHospitalizationService {
    SysHospitalizationDTO get(long id);

    SysHospitalization get(int times, String hId);

    PagingResult getPageList( int pageNum,int  pageSize,String sort, String sortOrder);

    PagingResult getPageList(SysHospitalizationQuery sysHospitalizationQuery,int pageNum, int pageSize,String sort, String sortOrder);

    //用于 后台 修改 出院状态
    List<SysHospitalization> getList(int pStatus);

    boolean insert(SysHospitalizationDTO createSysHospitalizationInfo);

    boolean insert(SysHospitalization sysHospitalization);

    boolean update(SysHospitalizationDTO sysHospitalizationDTO);

    boolean update(SysHospitalization sysHospitalization);

    boolean delete(List<Long> idList);
}
