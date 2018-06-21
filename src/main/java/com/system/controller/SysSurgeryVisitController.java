package com.system.controller;

import com.system.facade.SysSurgeryVisitService;
import com.system.pojo.SysUserDTO;
import com.system.service.SysUserService;
import com.system.util.CheckException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther: 李景然
 * @Date: 2018/6/19 14:56
 * @Description:
 */
@RestController
@Api(tags = "sysSurgeryVisit", description = "手术探视操作")
@RequestMapping(value = "/sysSurgeryVisit")
@CheckException(reason = "检查手术探视 操作参数的合法性")
public class SysSurgeryVisitController {
    @Resource
    private SysSurgeryVisitService sysSurgeryVisitService;

    @ApiOperation(value = "修改探视信息")
    @RequestMapping(value = "/update/{id}/{visitStatus}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@PathVariable long id,int visitStatus){
        return sysSurgeryVisitService.updateVisit(id,visitStatus);
    }
}
