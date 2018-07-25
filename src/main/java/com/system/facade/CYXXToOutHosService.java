package com.system.facade;


import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/7/2 09:29
 * @Description:
 */
public interface CYXXToOutHosService {
    List<PtsVwCyxx> getCYXXList(Date startTime, Date endTime);
    PtsVwCyxx getCYXX(String zyh, int times);
    boolean insertHos(PtsVwZyxx ptsVwZyxx);
}
