package com.system.service.impl;

import com.system.dao.SyncLogDao;
import com.system.entity.SyncLog;
import com.system.service.SynLogService;
import com.system.util.database.DataSwitch;
import com.system.util.exception.controller.result.NoneSaveException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: 李景然
 * @Date: 2018/7/4 14:45
 * @Description:
 */
@Service("synLogService")
public class    SynLogServiceImpl implements SynLogService {

    @Resource
    private SyncLogDao syncLogDao;

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public SyncLog insertSynLog(SyncLog syncLog) {
        syncLogDao.insertSelective(syncLog);
        return syncLog;
    }

    @Override
    @DataSwitch(dataSource = "dataSource1")
    public SyncLog getLastSynLog(String type) {
        List<SyncLog> list=syncLogDao.selectAll().stream().filter(item->item.getsType().equals(type)).sorted(Comparator.comparing
                (SyncLog::getsId).reversed()).collect(Collectors.toList());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
