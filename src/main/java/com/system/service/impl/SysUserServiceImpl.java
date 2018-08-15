package com.system.service.impl;

import com.system.dao.SysUserDao;
import com.system.entity.SysUser;
import com.system.pojo.*;
import com.system.service.SysUserService;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 韩宪斌 on 2017/7/10.
 */
@Service("sysUserService")
class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public SysUserDTO get(int id) {
        SysUser result = sysUserDao.selectByPrimaryKey(id);
        if (result == null) {
            throw new NoneGetException("没有该用户！");
        }
        return convertToSysUserDTO(result);
    }

    @Override
    public SysUserDTO get(String codeno){
        List<SysUser> resultList = sysUserDao.selectByExample(getExample(codeno));
        if (resultList != null&&resultList.size()>0) {
            return convertToSysUserDTO(resultList.get(0));
        }
        return null;
    }

    @Override
    public List<SysUserDTO> getByCodenoList(String codeno){
        List<SysUser> resultList = sysUserDao.selectByExample(getExample(codeno));
        if (resultList != null&&resultList.size()>0) {
            List<SysUserDTO> list=new ArrayList<>();
            for (SysUser item: resultList){
                list.add(convertToSysUserDTO(item));
            }
            return list;
        }
        return null;
    }

    @Override
    @DataSwitch(dataSource="dataSource1")
    public boolean isHave(String codeno){
        SysUserDTO sysUser=get(codeno);
        if(sysUser==null){
            return false;
        }
        return true;
    }

    public SysUserDTO login(SysUserQuery sysUserQuery) {
        List<SysUser> sysUserList = sysUserDao.selectByExample(getExample(sysUserQuery));
        if (sysUserList.size() != 0) {
            SysUserDTO sysUserDTO = convertToSysUserDTO(sysUserList.get(0));
            return sysUserDTO;
        }
        throw new NoneGetException();
    }

    @Override
    public List<SysUserDTO> getList() {
        List<SysUserDTO> resultList = sysUserDao.selectAll().stream().sorted(Comparator.comparing
                (SysUser::getGmtModified).reversed()).map(this::convertToSysUserDTO).collect(Collectors.toList());
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到用户相关记录！");
        }
        return resultList;
    }

    @Override
    public List<SysUserDTO> getList(String name) {
        List<SysUserDTO> resultList = sysUserDao.selectAll().stream().filter(item -> item.getUserName().equals(name)).sorted(Comparator.comparing
                (SysUser::getGmtModified).reversed()).map(this::convertToSysUserDTO).collect(Collectors.toList());
        if (resultList.size() == 0) {
            throw new NoneGetException("没有查询到用户相关记录！");
        }
        return resultList;
    }

    public boolean insert(CreateSysUserInfo createSysUserInfo) {
        List<SysUser> sysUserList = sysUserDao.selectByExample(getExample(createSysUserInfo.getCodeno()));
        if (sysUserList != null && sysUserList.size() > 0) {
            throw new NoneSaveException("不能新增同一条记录");
        }
        SysUser sysUser = convertToSysUser(createSysUserInfo);
        return sysUserDao.insertSelective(sysUser) > 0;
    }

    public boolean insert(SysUser sysUser){
        return sysUserDao.insertSelective(sysUser) > 0;
    }

    @Override
    public boolean update(SysUserDTO sysUserDTO) {
        SysUser sysUser = convertToSysUser(sysUserDTO);
        if (sysUserDao.updateByPrimaryKeySelective(sysUser) > 0) {
//            return convertToSysUserDTO(sysUserDao.selectByPrimaryKey(sysUserDTO.getUserId()));
            return true;
        }
        throw new NoneUpdateException();
    }

    @Override
    @Transactional(rollbackFor = NoneRemoveException.class)
    public boolean delete(List<Integer> idList) {
        for (Integer anIdList : idList) {
            if (sysUserDao.deleteByPrimaryKey(anIdList) == 0) {
                throw new NoneRemoveException();
            }
        }
        return true;
    }

    @Override
    @DataSwitch(dataSource="dataSource1")
    public List<String> getRoleList(){
        List<String> list=sysUserDao.getRoleList();
        return list;
    }

    private Example getExample(String codeno) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("codeno", codeno);
        return example;
    }

    private Example getExample(SysUserQuery sysUserQuery) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (sysUserQuery.getCodeno() != "") {
            criteria.andEqualTo("codeno", sysUserQuery.getCodeno());
        }
        if (sysUserQuery.getUserPwd() != "") {
            criteria.andEqualTo("userPwd", sysUserQuery.getUserPwd());
        }
        return example;
    }

    private SysUserDTO convertToSysUserDTO(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysUserDTO resultDTO = new SysUserDTO();
        BeanUtils.copyProperties(inputObject, resultDTO);
        return resultDTO;
    }

    private SysUser convertToSysUser(Object inputObject) {
        if (null == inputObject) {
            return null;
        }
        SysUser result = new SysUser();
        BeanUtils.copyProperties(inputObject, result);
        return result;
    }

}
