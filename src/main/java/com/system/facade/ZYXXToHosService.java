package com.system.facade;


import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
import com.system.entity.SyncLog;
import com.system.entity.SysHospitalization;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/7/2 09:29
 * @Description:
 */
public interface ZYXXToHosService {
    List<PtsVwZyxx> getZYXXList(Date startTime,Date endTime);
    List<PtsVwZyxx> getZYXXList();
    List<PtsVwCyxx> getCYXXList(Date startTime, Date endTime);
    PtsVwCyxx getCYXX(String zyh,int times);
    boolean insertHos(PtsVwZyxx ptsVwZyxx);
    boolean update(PtsVwZyxx ptsVwZyxx,SysHospitalization old);
    boolean isExitHos(SysHospitalization item);
    boolean deleteExitHos(List<Long> idList);
}
