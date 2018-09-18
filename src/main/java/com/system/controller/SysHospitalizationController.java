package com.system.controller;

import com.system.entity.SysHospitalization;
import com.system.facade.SysHospitalizationVisitStatusService;
import com.system.pojo.*;
import com.system.service.SysHospitalizationService;
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
@Api(tags = "sysHospitalization", description = "病人在院相关操作")
@RequestMapping(value = "/sysHospitalization")
@CheckException(reason = "检查病人在院操作参数的合法性")
public class SysHospitalizationController {
    @Resource
    private SysHospitalizationService sysHospitalizationService;

    @ApiOperation(value = "根据id查询病人在院信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysHospitalizationDTO get(@PathVariable long id) {
        return sysHospitalizationService.get(id);
    }

    @ApiOperation(value = "获取所有病人在院信息---分页")
    @RequestMapping(value = "/getAllList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PagingResult getPagingList(@RequestBody PagingRequest request) {
        PagingResult pagingResult = sysHospitalizationService.getPageList(request);
        return pagingResult;
    }

    @ApiOperation(value = "按多条件查询病人在院信息--分页")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PagingResult getList(@RequestBody SysHospitalizationQuery query) throws ParseException, InterruptedException {
        PagingResult pagingResult=sysHospitalizationService.getPageList(query);
        return pagingResult;
    }

    @ApiOperation(value = "新增病人在院信息")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody SysHospitalizationDTO sysHospitalizationDTO) throws ParseException {
        return sysHospitalizationService.insert(sysHospitalizationDTO);
    }

    @ApiOperation(value = "修改病人在院信息")
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysHospitalizationDTO sysHospitalizationDTO) throws ParseException {
        return sysHospitalizationService.update(sysHospitalizationDTO);
    }

    @ApiOperation(value = "删除病人在院信息")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Long> idList) {
        return sysHospitalizationService.delete(idList);
    }

    @Resource
    private SysHospitalizationVisitStatusService sysHospitalizationVisitStatusService;

    @ApiOperation(value = "修改病人探视信息")
    @RequestMapping(value = "update/{id}/{status}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateVisitStatus(@PathVariable Long id, @PathVariable Integer status) {
        sysHospitalizationVisitStatusService.updateHospitalizationVisitStatus(id, status);
    }

    @ApiOperation(value = "根据病区获取到床号列表")
    @RequestMapping(value = "/getBedList/{areaId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getBedList(@PathVariable Integer areaId){
        return sysHospitalizationService.getBedList(areaId);
    }

    @ApiOperation(value = "根据床号获取到病人信息")
    @RequestMapping(value = "/get/{hBed}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysHospitalization getLatestRecordByhBed(@PathVariable String hBed){
        return sysHospitalizationService.getLatestRecordByhBed(hBed);
    }

    @ApiOperation(value = "根据住院信息的id修改陪同人数")
    @RequestMapping(value = "/updateEsortnum/{id}/{esortNum}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean updateEsortnumById(@PathVariable Long id, @PathVariable Integer esortNum){
        SysHospitalizationDTO sysHospitalizationDTO = new SysHospitalizationDTO();
        sysHospitalizationDTO.setId(id);
        sysHospitalizationDTO.setEscortsNum(esortNum);
        return sysHospitalizationService.update(sysHospitalizationDTO);
    }

    @ApiOperation(value = "获某个病区的所有姓名")
    @RequestMapping(value = "/getNameList/{hArea}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getList(@PathVariable int hArea) {
        return sysHospitalizationService.getNameList(hArea);
    }

    @ApiOperation(value = "按照名字获取相应的列表")
    @RequestMapping(value = "/getListByName/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<SysHospitalizationDTO> getListByName(@RequestBody List<String> names) {
        return sysHospitalizationService.getListByName(names);
    }
}
