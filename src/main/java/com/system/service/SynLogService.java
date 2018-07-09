package com.system.service;

import com.system.entity.SyncLog;

/**
 * @Auther: 李景然
 * @Date: 2018/7/4 14:45
 * @Description:
 */
public interface SynLogService {
    SyncLog insertSynLog(SyncLog syncLog);
    SyncLog getLastSynLog(String type);
}
