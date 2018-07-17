package com.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.entity.SysSurgery;
import com.system.facade.SysSurgeryVisitService;
import com.system.pojo.PagingResult;
import com.system.pojo.SysHospitalizationDTO;
import com.system.pojo.SysSurgeryDTO;
import com.system.pojo.SysSurgeryQuery;
import com.system.service.SysSurgeryService;
import com.system.util.CheckException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private SysSurgeryVisitService sysSurgeryVisitService;

    @ApiOperation(value = "根据id查询手术信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public SysSurgeryDTO get(@PathVariable long id) {
        return sysSurgeryService.getDTO(id);
    }

    @ApiOperation(value = "按多条件查询手术信息--分页")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PagingResult getList(@RequestBody SysSurgeryQuery sysSurgeryQuery, @RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize, @RequestParam(value = "sort") String sort, @RequestParam(value = "sortOrder") String sortOrder) {
        PagingResult pagingResult = sysSurgeryService.getList(sysSurgeryQuery,pageNum,pageSize, sort, sortOrder);
        return pagingResult;
    }

    @ApiOperation(value = "获取所有手术信息--分页")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PagingResult getList(@RequestParam(value = "page") int pageNum, @RequestParam(value = "rows") int pageSize, @RequestParam(value = "sort") String sort, @RequestParam(value = "sortOrder") String sortOrder) {
        return sysSurgeryService.getList(pageNum, pageSize, sort, sortOrder);
    }

    @ApiOperation(value = "新增手术信息")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public boolean insert(@RequestBody SysSurgeryDTO sysSurgeryDTO) {
        return sysSurgeryService.insert(sysSurgeryDTO);
    }

    @ApiOperation(value = "修改手术信息")
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody SysSurgeryDTO sysSurgeryDTO) {
        return sysSurgeryService.update(sysSurgeryDTO);
    }

    @ApiOperation(value = "删除手术信息")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestBody List<Long> idList) {
        return sysSurgeryService.delete(idList);
    }


    @ApiOperation(value = "探视、离开")
    @RequestMapping(value = "/{id}/{visit_status}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean visit(@RequestAttribute long id, int visitStatus) {
        return sysSurgeryVisitService.updateVisit(id, visitStatus);
    }
}
