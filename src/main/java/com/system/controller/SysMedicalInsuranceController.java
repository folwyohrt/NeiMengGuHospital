package com.system.controller;

import com.system.entity.SysMedicalInsurance;
import com.system.pojo.CreateSysMedicalInsuranceInfo;
import com.system.pojo.SysMedicalInsuranceDTO;
import com.system.service.SysMedicalInsuranceService;
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
@Api(tags = "sysMedicalInsurance",description = "医保类型相关操作")
@RequestMapping(value = "/sysMedicalInsurance")
@CheckException(reason = "检查医保类型操作参数的合法性")
public class SysMedicalInsuranceController {

    @Resource
    private SysMedicalInsuranceService sysMedicalInsuranceService;

    @ApiOperation(value = "根据id查询医保类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysMedicalInsurance get(@PathVariable int id){
        return sysMedicalInsuranceService.get(id);
    }

    @ApiOperation(value = "获取所有医保类型")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysMedicalInsurance> getList() {
        return sysMedicalInsuranceService.getList();
    }

    @ApiOperation(value ="新增医保类型")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody CreateSysMedicalInsuranceInfo sysMedicalInsuranceInfo){
        return sysMedicalInsuranceService.insert(sysMedicalInsuranceInfo);
    }

    @ApiOperation(value ="修改医保类型")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysMedicalInsuranceDTO sysMedicalInsuranceDTO){
        return sysMedicalInsuranceService.update(sysMedicalInsuranceDTO);
    }

    @ApiOperation(value ="删除医保类型")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Integer> idList){
        return sysMedicalInsuranceService.delete(idList);
    }
}
