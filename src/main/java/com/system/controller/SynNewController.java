package com.system.controller;

import com.system.controller.util.ExceptionHandlerController;
import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
import com.system.entity.SyncLog;
import com.system.entity.SysHospitalization;
import com.system.facade.ZYXXToHosService;
import com.system.service.SynLogService;
import com.system.service.SysHospitalizationService;
import com.system.util.CheckException;
import com.system.util.exception.controller.result.NoneRemoveException;
import com.system.util.tools.DateFormatHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Auther: 李景然
 * @Date: 2018/7/24 15:33
 * @Description:
 */
@RestController
@Api(tags = "synNewController", description = "同步数据库相关操作")
@RequestMapping(value = "/synNewController")
@CheckException(reason = "同步数据库操作参数的合法性")
public class SynNewController {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
    Timer syncHosTimer;
    Timer syncOutHosTimer;

    @Resource
    private ZYXXToHosService zyxxToHosService;

    @Resource
    private SysHospitalizationService sysHospitalizationService;

    @Resource
    private SynLogService synLogService;

    @ApiOperation(value = "同步在院病人在院信息(分钟)")
    @RequestMapping(value = "/syncHos/{period}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean synchronousHos(@PathVariable Long period) {
        if (syncHosTimer != null) {
            syncHosTimer.cancel();
        }
        syncHosTimer = new Timer();
        //周期任务执行的开始时间
        Date beginTime = new Date();
        logger.info("period(分钟)---" + period);
        period = 1000 * 60 * period;

        syncHosTimer.schedule(new TimerTask() {
            int num = 0;

            @Override
            public void run() {
                System.out.println("第几次---" + ++num);
                //取db2数据的开始、截止时间
                Date startTime = new Date(0);
                Date endTime = new Date();

                List<PtsVwZyxx> list = zyxxToHosService.getZYXXList();
                if (list != null && list.size() > 0) {
                    System.out.println("获取 病人信息的总条数count---" + list.size());
                    sysHospitalizationService.truncate();
                    for (PtsVwZyxx item : list) {
                        boolean bl = zyxxToHosService.insertHos(item);
                        if (bl == false) {
                            throw new NoneRemoveException();
                        }
                    }
                    SyncLog syncLog = new SyncLog();
                    syncLog.setsCount((long) list.size());
                    syncLog.setsEndtime(endTime);
                    syncLog.setsStarttime(startTime);
                    syncLog.setsSuccess(true);
                    syncLog.setsType("hspt");
                    synLogService.insertSynLog(syncLog);

                    System.out.println("startTime---" + startTime);
                    System.out.println("endTime---" + endTime);
                    System.out.println("插入 病人信息count---" + list.size());
                } else {
                    System.out.println("startTime---" + startTime);
                    System.out.println("endTime---" + endTime);
                    System.out.println("暂无需要同步 插入 病人信息的数据");
                }
            }
        }, beginTime, period);
        return true;
    }


    @ApiOperation(value = "同步离院病人在院信息(分钟)")
    @RequestMapping(value = "/syncOutHos/{period}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean synchronousOutHos(@PathVariable Long period) {

        if (syncOutHosTimer != null) {
            syncOutHosTimer.cancel();
        }
        syncOutHosTimer = new Timer();
        //周期任务执行的开始时间
        Date beginTime = new Date();
        System.out.println("beginTime---"+beginTime);

        logger.info("period(分钟)---" + period);
        period = 1000 * 60 * period;

        syncOutHosTimer.schedule(new TimerTask() {
            int num = 0;

            @Override
            public void run() {
                System.out.println("第几次---" + ++num);
                //取db2数据的开始、截止时间
                Date startTime = null;
                Date endTime = new Date();

                SyncLog lastSyncLog = synLogService.getLastSynLog("outhspt");

                if (lastSyncLog == null) {
                    //startTime=new Date(0);
                    String startTimeStr="2018-01-01 00:00:00";
                    startTime=DateFormatHelper.getDate(startTimeStr);
                } else {
                    startTime = lastSyncLog.getsEndtime();
                    System.out.println("lastSyncLog.getsEndtime()---" + startTime);
                }

                // 执行你的方法
                List<PtsVwZyxx> list = zyxxToHosService.getZYXXList(startTime, endTime);
                if (list != null && list.size() > 0) {
                    System.out.println("获取 病人信息的总条数count---" + list.size());
                    for (PtsVwZyxx item : list) {
                        boolean bl = zyxxToHosService.insertHos(item);
                        if (bl == false) {
                            throw new NoneRemoveException();
                        }
                    }
                    SyncLog syncLog = new SyncLog();
                    syncLog.setsCount((long) list.size());
                    syncLog.setsEndtime(endTime);
                    syncLog.setsStarttime(startTime);
                    syncLog.setsSuccess(true);
                    syncLog.setsType("outhspt");
                    synLogService.insertSynLog(syncLog);


                    System.out.println("startTime---" + startTime);
                    System.out.println("endTime---" + endTime);
                    System.out.println("插入 病人信息count---" + list.size());
                } else {
                    System.out.println("startTime---" + startTime);
                    System.out.println("endTime---" + endTime);
                    System.out.println("暂无需要同步 插入 病人信息的数据");
                }

                //修改 出院状态
                List<SysHospitalization> zaiyuanList= sysHospitalizationService.getList(1);
                if(zaiyuanList!=null&&zaiyuanList.size()>0){
                    for (SysHospitalization item :zaiyuanList){
                        PtsVwCyxx ptsVwCyxx= zyxxToHosService.getCYXX(item.gethId(),item.gethTimes());
                        if(ptsVwCyxx!=null){
                            item.setpStatus(3);
                            sysHospitalizationService.update(item);
                        }
                    }
                }
            }
        }, beginTime, period);

        return true;
    }

}
