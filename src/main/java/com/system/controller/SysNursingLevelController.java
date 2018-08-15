package com.system.controller;

import com.system.entity.SysNursingLevel;
import com.system.pojo.CreateSysNursingLevelInfo;
import com.system.pojo.SysNursingLevelDTO;
import com.system.service.SysNursingLevelService;
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
@Api(tags = "sysNursingLevel",description = "护理相关操作")
@RequestMapping(value = "/sysNursingLevel")
@CheckException(reason = "检查护理操作参数的合法性")
public class SysNursingLevelController {

    @Resource
    private SysNursingLevelService sysNursingLevelService;

    @ApiOperation(value = "根据id查询护理")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysNursingLevel get(@PathVariable int id){
        return sysNursingLevelService.get(id);
    }

    @ApiOperation(value = "获取所有护理")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<SysNursingLevel> getList() {
        return sysNursingLevelService.getList();
    }

    @ApiOperation(value ="新增护理名")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody CreateSysNursingLevelInfo sysNursingLevelInfo){
        return sysNursingLevelService.insert(sysNursingLevelInfo);
    }

    @ApiOperation(value ="修改护理名")
    @RequestMapping(value = "/",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysNursingLevelDTO sysNursingLevelDTO){
        return sysNursingLevelService.update(sysNursingLevelDTO);
    }

    @ApiOperation(value ="删除护理名")
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Integer> idList){
        return sysNursingLevelService.delete(idList);
    }
}
