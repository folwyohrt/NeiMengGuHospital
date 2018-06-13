package com.system.controller;

import com.system.pojo.SysHospitalizationDTO;
import com.system.pojo.SysHospitalizationQuery;
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
    public SysHospitalizationDTO get(@PathVariable long id){
        return sysHospitalizationService.get(id);
    }

    @ApiOperation(value = "按多条件查询病人在院信息")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<SysHospitalizationDTO> getList(@RequestBody SysHospitalizationQuery sysHospitalizationQuery) throws ParseException {
        return sysHospitalizationService.getList(sysHospitalizationQuery);
    }

    @ApiOperation(value = "获取所有病人在院信息")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysHospitalizationDTO> getList() {
        return sysHospitalizationService.getList();
    }

    @ApiOperation(value ="新增病人在院信息")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody SysHospitalizationDTO sysHospitalizationDTO) throws ParseException {
        return sysHospitalizationService.insert(sysHospitalizationDTO);
    }

    @ApiOperation(value ="修改病人在院信息")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysHospitalizationDTO sysHospitalizationDTO) throws ParseException {
        return sysHospitalizationService.update(sysHospitalizationDTO);
    }

    @ApiOperation(value ="删除病人在院信息")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Long> idList){
        return sysHospitalizationService.delete(idList);
    }
}
