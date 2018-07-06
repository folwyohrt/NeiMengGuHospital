package com.system.facade.impl;

import com.system.dao.DB2.test1.PtsVwRyxxDao;
import com.system.dao.SysUserDao;
import com.system.entity.DB2.test1.PtsVwRyxx;
import com.system.entity.SysUser;
import com.system.facade.RYXXToUserService;
import com.system.service.SysUserService;
import com.system.util.database.DataSwitch;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by henghhh on 2018.7.3
 */
@Service("ryxxToUserService")
public class RYXXToUserServiceImpl implements RYXXToUserService {
    @Resource
    private PtsVwRyxxDao ryxxDao;

    @Resource
    private SysUserService sysUserService;

    @Override
    @DataSwitch(dataSource = "dataSource3")
    public List<PtsVwRyxx> getRYXXList() {
        List<PtsVwRyxx> list = ryxxDao.selectAll();
        return list;
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public boolean insertRYXX(PtsVwRyxx ryxx) {
        SysUser user = convertRYXXToUser(ryxx);
        return sysUserService.insert(user);
    }

    SysUser convertRYXXToUser(PtsVwRyxx ryxx) {
        if (ryxx == null) {
            return null;
        }
        SysUser user = new SysUser();
        // 暂时这样对应，应该需要更改
        user.setUserName(ryxx.getUsername());
        user.setUserPwd("1");
        user.setUserRole(ryxx.getRole());
        user.setUserGroup(ryxx.getOffice());
        user.setCodeno(ryxx.getCodeno());
        return user;
    }
}
