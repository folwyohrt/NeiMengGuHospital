package com.system.controller;

import com.system.dao.NM.SysHahaDao;
import com.system.entity.NM.SysHaha;
import com.system.service.SysHahaService;
import com.system.util.CheckException;
import com.system.util.database.MultipleDataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther: 李景然
 * @Date: 2018/6/8 15:24
 * @Description:
 */
@RestController
@Api(tags = "sysHaha", description = "用户相关操作")
@RequestMapping(value = "/sysHaha")
@CheckException(reason = "检查用户操作参数的合法性")
public class SysHahaController {
    @Resource
    private SysHahaService sysHahaService;

    @Resource
    private SysHahaDao sysHahaDao;


    @ApiOperation(value = "根据id查询用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysHaha get(@PathVariable int id){
        MultipleDataSource.setDataSourceKey("dataSource2");
        //return sysHahaService.get(id);
        return sysHahaDao.selectByPrimaryKey(1);
    }

}
