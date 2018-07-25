package com.system.controller;

import com.system.pojo.PagingRequest;
import com.system.pojo.PagingResult;
import com.system.pojo.SysOutHospitalDTO;
import com.system.pojo.SysOutHospitalQuery;
import com.system.service.SysOutHospitalService;
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
 * @Date: 2018/6/13 09:47
 * @Description:
 */
@RestController
@Api(tags = "sysOutHospital", description = "病人离院相关操作")
@RequestMapping(value = "/sysOutHospital")
@CheckException(reason = "检查病人离院操作参数的合法性")
public class SysOutHospitalController {
    @Resource
    private SysOutHospitalService sysOutHospitalService;

    @ApiOperation(value = "根据id查询病人离院信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysOutHospitalDTO get(@PathVariable long id) {
        return sysOutHospitalService.get(id);
    }

    @ApiOperation(value = "获取所有病人离院信息---分页")
    @RequestMapping(value = "/getAllList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PagingResult getPagingList(@RequestBody PagingRequest request) {
        PagingResult pagingResult = sysOutHospitalService.getPageList(request);
        return pagingResult;
    }

    @ApiOperation(value = "按多条件查询病人离院信息--分页")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PagingResult getList(@RequestBody SysOutHospitalQuery query) throws ParseException, InterruptedException {
        PagingResult pagingResult=sysOutHospitalService.getPageList(query);
        return pagingResult;
    }

    @ApiOperation(value = "新增病人离院信息")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody SysOutHospitalDTO sysOutHospitalDTO) throws ParseException {
        return sysOutHospitalService.insert(sysOutHospitalDTO);
    }

    @ApiOperation(value = "修改病人离院信息")
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysOutHospitalDTO sysOutHospitalDTO) throws ParseException {
        return sysOutHospitalService.update(sysOutHospitalDTO);
    }

    @ApiOperation(value = "删除病人离院信息")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Long> idList) {
        return sysOutHospitalService.delete(idList);
    }

}
