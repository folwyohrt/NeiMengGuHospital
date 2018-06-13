package com.system.service.impl;

import com.system.controller.util.ExceptionHandlerController;
import com.system.dao.SysAreaDao;
import com.system.entity.SysArea;
import com.system.service.SysAreaService;
import com.system.util.exception.controller.result.NoneGetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 19:48
 * @Description:
 */
@Service("sysAreaService")
public class SysAreaServiceImpl implements SysAreaService {
    @Resource
    private SysAreaDao sysAreaDao;

    @Override
    public SysArea get(int id) {
        return sysAreaDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysArea> getList() {
        List<SysArea> list= sysAreaDao.selectAll();
        if(list==null||list.size()==0){
            throw new NoneGetException();
        }
        return list;
    }
}
