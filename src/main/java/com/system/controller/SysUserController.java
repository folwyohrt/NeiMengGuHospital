package com.system.controller;

import com.system.entity.SysUser;
import com.system.pojo.*;
import com.system.service.SysUserService;
import com.system.util.CheckException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/8 15:24
 * @Description:
 */
@RestController
@Api(tags = "sysUser", description = "用户相关操作")
@RequestMapping(value = "/sysUser")
@CheckException(reason = "检查用户操作参数的合法性")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "根据id查询用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysUserDTO get(@PathVariable int id){
        return sysUserService.get(id);
    }

    @ApiOperation(value = "根据codeno查询用户")
    @RequestMapping(value = "/getByCodeno", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public SysUser get(@RequestBody String codeno){
        return sysUserService.get(codeno);
    }

    @ApiOperation(value ="登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public SysUserDTO login(@RequestBody SysUserQuery sysUserQuery){
        return sysUserService.login(sysUserQuery);
    }

    @ApiOperation(value = "按名字查询用户")
    @RequestMapping(value = "/getList/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysUserDTO> getList(@PathVariable String name) {
        return sysUserService.getList(name);
    }

    @ApiOperation(value = "获取所有用户")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysUserDTO> getList() {
        return sysUserService.getList();
    }

    @ApiOperation(value ="新增用户")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody CreateSysUserInfo createSysUserInfo){
        return sysUserService.insert(createSysUserInfo);
    }

    @ApiOperation(value ="修改用户")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysUserDTO sysUserDTO){
        return sysUserService.update(sysUserDTO);
    }

    @ApiOperation(value ="删除用户")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Integer> idList){
        return sysUserService.delete(idList);
    }


}
