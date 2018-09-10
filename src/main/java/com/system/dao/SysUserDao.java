package com.system.dao;

import com.system.entity.SysUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserDao extends Mapper<SysUser> {
    @Select("select distinct user_role from sys_user")
    List<String> getRoleList();
}