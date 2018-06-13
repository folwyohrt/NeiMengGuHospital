package com.system.controller;

import com.system.pojo.SysSurgeryDTO;
import com.system.pojo.SysSurgeryQuery;
import com.system.service.SysSurgeryService;
import com.system.util.CheckException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/12 16:06
 * @Description:
 */
@RestController
@Api(tags = "sysSurgery", description = "手术相关操作")
@RequestMapping(value = "/sysSurgery")
@CheckException(reason = "检查手术操作参数的合法性")
public class SysSurgeryController {
    @Resource
    private SysSurgeryService sysSurgeryService;

    @ApiOperation(value = "根据id查询手术信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysSurgeryDTO get(@PathVariable long id){
        return sysSurgeryService.get(id);
    }

    @ApiOperation(value = "按多条件查询手术信息")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<SysSurgeryDTO> getList(@RequestBody SysSurgeryQuery sysSurgeryQuery)  {
        return sysSurgeryService.getList(sysSurgeryQuery);
    }

    @ApiOperation(value = "获取所有手术信息")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysSurgeryDTO> getList() {
        return sysSurgeryService.getList();
    }

    @ApiOperation(value ="新增手术信息")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody SysSurgeryDTO sysSurgeryDTO)  {
        return sysSurgeryService.insert(sysSurgeryDTO);
    }

    @ApiOperation(value ="修改手术信息")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysSurgeryDTO sysSurgeryDTO)  {
        return sysSurgeryService.update(sysSurgeryDTO);
    }

    @ApiOperation(value ="删除手术信息")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Long> idList){
        return sysSurgeryService.delete(idList);
    }
}
