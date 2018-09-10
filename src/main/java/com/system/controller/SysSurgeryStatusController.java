package com.system.controller;

import com.system.entity.SysSurgeryStatus;
import com.system.pojo.CreateSysSurgeryStatusInfo;
import com.system.pojo.SysSurgeryStatusDTO;
import com.system.service.SysSurgeryStatusService;
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
@Api(tags = "sysSurgeryStatus",description = "手术类型相关操作")
@RequestMapping(value = "/sysSurgeryStatus")
@CheckException(reason = "检查手术类型操作参数的合法性")
public class SysSurgeryStatusController {

    @Resource
    private SysSurgeryStatusService sysSurgeryStatusService;

    @ApiOperation(value = "根据id查询手术类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysSurgeryStatus get(@PathVariable int id){
        return sysSurgeryStatusService.get(id);
    }

    @ApiOperation(value = "获取所有手术类型")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysSurgeryStatus> getList() {
        return sysSurgeryStatusService.getList();
    }

    @ApiOperation(value ="新增手术类型")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody CreateSysSurgeryStatusInfo sysSurgeryStatusInfo){
        return sysSurgeryStatusService.insert(sysSurgeryStatusInfo);
    }

    @ApiOperation(value ="修改手术类型")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysSurgeryStatusDTO sysSurgeryStatusDTO){
        return sysSurgeryStatusService.update(sysSurgeryStatusDTO);
    }

    @ApiOperation(value ="删除手术类型")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Integer> idList){
        return sysSurgeryStatusService.delete(idList);
    }
}
