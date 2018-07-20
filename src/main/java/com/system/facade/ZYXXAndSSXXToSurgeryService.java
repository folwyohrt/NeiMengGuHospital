package com.system.facade;

import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
//import com.system.entity.mannul.SqlServer.PtsVwSsxx;
import com.system.entity.SqlServer.PtsVwSsxx;
import java.util.Date;
import java.util.List;

/**
 * Create by henghhh on 2018.7.2
 */
public interface ZYXXAndSSXXToSurgeryService {
    PtsVwSsxx getSSXX(String zyh, String zycs, String xh);
    List<PtsVwSsxx> getAllSSXXList();
    // 依靠住院号从住院信息获取病人住院信息，只要住院号就行了，知道一个就行了
    PtsVwZyxx getZyxx(String zyh,  String zycs);
    PtsVwCyxx getCyxx(String zyh,  String zycs);
    // 往Sys_Surgery表中插入或更新
    String insertOrUpdateSurgery(PtsVwSsxx ssxx, PtsVwZyxx zyxx, PtsVwCyxx cyxx);
}
