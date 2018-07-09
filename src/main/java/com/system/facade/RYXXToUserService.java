package com.system.facade;

import com.system.entity.DB2.test1.PtsVwRyxx;
import com.system.entity.SysUser;

import java.util.Date;
import java.util.List;

/**
 * Create by henghhh on 2018.7.3
 */
public interface RYXXToUserService {
    List<PtsVwRyxx> getRYXXList();
    boolean insertRYXX(PtsVwRyxx ryxx);
}
