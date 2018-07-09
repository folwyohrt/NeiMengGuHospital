package com.system.service;

import com.system.entity.SysUser;
import com.system.pojo.CreateSysUserInfo;
import com.system.pojo.EnumBean;
import com.system.pojo.SysUserDTO;
import com.system.pojo.SysUserQuery;

import java.util.List;

/**
 * Created by 韩宪斌 on 2017/7/10.
 * 用户Service层
 */
public interface SysUserService {

    SysUserDTO login(SysUserQuery sysUserQuery);

    SysUserDTO get(int id);

    SysUser get(String codeno);

    boolean isHave(String codeno);

    List<SysUserDTO> getList();

    List<SysUserDTO> getList(String name);

    boolean insert(CreateSysUserInfo createSysUserInfo);

    boolean insert(SysUser sysUser);

    boolean update(SysUserDTO sysUserDTO);

    boolean delete(List<Integer> idList);


}
