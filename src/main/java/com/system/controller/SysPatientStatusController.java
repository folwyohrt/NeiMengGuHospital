package com.system.controller;

import com.system.entity.SysPatientStatus;
import com.system.pojo.CreateSysPatientStatusInfo;
import com.system.pojo.SysPatientStatusDTO;
import com.system.service.SysPatientStatusService;
import com.system.util.CheckException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 21:04
 * @Description:
 */
@RestController
@Api(tags = "sysPatientStatus",description = "患者状态相关操作")
@RequestMapping(value = "/sysPatientStatus")
@CheckException(reason = "检查患者状态操作参数的合法性")
public class SysPatientStatusController {

    @Resource
    private SysPatientStatusService sysPatientStatusService;

    @ApiOperation(value = "根据id查询患者状态")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysPatientStatus get(@PathVariable int id){
        return sysPatientStatusService.get(id);
    }

    @ApiOperation(value = "获取所有患者状态")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysPatientStatus> getList() {
        return sysPatientStatusService.getList();
    }

    @ApiOperation(value ="新增患者状态")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody CreateSysPatientStatusInfo sysPatientStatusInfo){
        return sysPatientStatusService.insert(sysPatientStatusInfo);
    }

    @ApiOperation(value ="修改患者状态")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysPatientStatusDTO sysPatientStatusDTO){
        return sysPatientStatusService.update(sysPatientStatusDTO);
    }

    @ApiOperation(value ="删除患者状态")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Integer> idList){
        return sysPatientStatusService.delete(idList);
    }
}
