package com.system.controller;


import com.system.controller.util.ExceptionHandlerController;
import com.system.entity.DB2.test1.PtsVwCyxx;
import com.system.entity.DB2.test1.PtsVwRyxx;
import com.system.entity.DB2.test1.PtsVwZyxx;
import com.system.entity.SqlServer.PtsVwSsxx;
import com.system.entity.SyncLog;
import com.system.facade.RYXXToUserService;
import com.system.facade.ZYXXAndSSXXToSurgeryService;
import com.system.service.SynLogService;
import com.system.service.SysHospitalizationService;
import com.system.service.SysUserService;
import com.system.util.CheckException;
import com.system.util.exception.controller.result.NoneSaveException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.system.util.tools.DateFormatHelper.getTodayDate;

/**
 * @Auther: 李景然
 * @Date: 2018/7/2 11:13
 * @Description:
 */
@RestController
@Api(tags = "synchronousDBController", description = "同步数据库相关操作")
@RequestMapping(value = "/synchronousDBController")
@CheckException(reason = "同步数据库操作参数的合法性")
public class SynchronousDBController {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
    Timer syncHosTimer;
    Timer syncUserTimer;

    @Resource
    private SysHospitalizationService sysHospitalizationService;

    @Resource
    private SynLogService synLogService;
//
//    @ApiOperation(value = "同步病人在院信息(分钟)")
//    @RequestMapping(value = "/syncHos/{period}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public boolean synchronousHos(@PathVariable Long period) {
//
//       if (syncHosTimer != null) {
//           syncHosTimer.cancel();
//       }
//        syncHosTimer = new Timer();
//        //周期任务执行的开始时间
//        Date beginTime = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//        logger.info("period(分钟)---" + period);
//        period = 1000 * 60 * period;
//
//        syncHosTimer.schedule(new TimerTask() {
//            int num = 0;
//
//            @Override
//            public void run() {
//                System.out.println("第几次---" + ++num);
//                //取db2数据的开始、截止时间
//                Date startTime = null;
//                Date endTime = new Date();
//
//                SyncLog lastSyncLog = synLogService.getLastSynLog("hspt");
//                if (lastSyncLog == null) {
//                    startTime=new Date(0);
//                } else {
//                    startTime = lastSyncLog.getsEndtime();
//                }
//
//                // 执行你的方法
//                List<PtsVwZyxx> list = zyxxAndCYXXToHosService.getZYXXList(startTime, endTime);
//                if (list != null && list.size() > 0) {
//                    System.out.println("获取 病人信息的总条数count---" + list.size());
//                    for (PtsVwZyxx item : list) {
//                        boolean bl = zyxxAndCYXXToHosService.insertHos(item);
//                        if (bl == false) {
//                            throw new NoneRemoveException();
//                        }
//                    }
//                    SyncLog syncLog = new SyncLog();
//                    syncLog.setsCount((long) list.size());
//                    syncLog.setsEndtime(endTime);
//                    syncLog.setsStarttime(startTime);
//                    syncLog.setsSuccess(true);
//                    syncLog.setsType("hspt");
//                    synLogService.insertSynLog(syncLog);
//
//
//                    System.out.println("startTime---" + startTime);
//                    System.out.println("endTime---" + endTime);
//                    System.out.println("插入 病人信息count---" + list.size());
//                } else {
//                    System.out.println("startTime---" + startTime);
//                    System.out.println("endTime---" + endTime);
//                    System.out.println("暂无需要同步 插入 病人信息的数据");
//                }
//
//                //修改 出院状态
//                List<SysHospitalization> zaiyuanList= sysHospitalizationService.getList(1);
//                if(zaiyuanList!=null&&zaiyuanList.size()>0){
//                    for (SysHospitalization item :zaiyuanList){
//                        PtsVwCyxx ptsVwCyxx=zyxxAndCYXXToHosService.getCYXX(item.gethId(),item.gethTimes());
//                        if(ptsVwCyxx!=null){
//                            item.setpStatus(3);
//                            sysHospitalizationService.update(item);
//                        }
//                    }
//                }
//            }
//        }, beginTime, period);
//
//        return true;
//
//
//    }

    Timer syncSgTimer;
    @Resource
    private ZYXXAndSSXXToSurgeryService zyxxAndSSXXToSurgeryService;

    @ApiOperation(value = "同步病人手术信息，period时间间隔，delay延迟，syncType同步类型（1.当天 2.所有带日期（未实现） 3.所有（未实现））")
    @RequestMapping(value = {"/syncSurgery/{period}/{delay}/{syncType}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean syncSurgery(@PathVariable Long period, @PathVariable Long delay, @PathVariable Long syncType) {
        if (syncSgTimer != null) {
            syncSgTimer.cancel();
        }
        syncSgTimer = new Timer();

        logger.info("- SSXX-- period(分钟)---" + period);
        logger.info("- SSXX-- delay(分钟)---" + delay);
        period = 1000 * 60 * period;
        delay =  1000 * 60 * delay;
        final String syncTypeStr = "sgToday";

        syncSgTimer.schedule(new TimerTask() {
            int num = 0;
            @Override
            public void run() {
                logger.info("SSXX---times---" + ++num);
                // 获取今天和明天的时间（格式：2018-08-01 00:00:00）
                Date startTime = new Date();
                Calendar cal = Calendar.getInstance();
                Date todayDate = getTodayDate();
                cal.setTime(todayDate);
                cal.add(Calendar.DATE, 1);
                // 当天的手术信息
                List<PtsVwSsxx> list = zyxxAndSSXXToSurgeryService.getSSXXListBySgDate(todayDate, cal.getTime());
                long totalNum = Long.valueOf(list.size());
                logger.info("手术信息总数：" + totalNum);
                long insertNum = 0;
                long updateNum = 0;
                long noneNum = 0;
                // 从pts_vw_ssxx视图中获取到所有手术信息，并获取到住院信息和出院信息（如果住院信息未获取到），
                // 然后往sys_surgery中插入或更新数据；
                // 同时记录本次同步的总数（也就是当前手术信息总数），更新的数目，新增的数目，未变化的数目，开始时间，结束时间
                for (PtsVwSsxx item : list) {
                    PtsVwZyxx zyxx = zyxxAndSSXXToSurgeryService.getZyxx(item.getZYH(), item.getZYCS());
                    PtsVwCyxx cyxx = null;
                    if (zyxx == null) {
                        cyxx = zyxxAndSSXXToSurgeryService.getCyxx(item.getZYH(), item.getZYCS());
                    }
                    String result = zyxxAndSSXXToSurgeryService.insertOrUpdateSurgery(item, zyxx, cyxx);
                    // 记录本次同步的行为
                    if (result.equals("insert")) insertNum++;
                    else if (result.equals("update")) updateNum++;
                    else noneNum++;
                }
                // 往数据库中记录本次同步的详细信息
                SyncLog syncLog = new SyncLog();
                syncLog.setsCount(totalNum);
                syncLog.setsStarttime(startTime);
                syncLog.setsEndtime(new Date());
                syncLog.setsSuccess(totalNum == (insertNum + updateNum + noneNum));
                syncLog.setsInsert(insertNum);
                syncLog.setsUpdate(updateNum);
                syncLog.setsType(syncTypeStr);
                synLogService.insertSynLog(syncLog);
                logger.info("SSXX---times--- 第 "+ num +" 次同步，新增数目：" + insertNum + "，更新数目：" + updateNum);
            }
        },delay, period); // delay为0，是立即执行
        return true;
    }

    @Resource
    private RYXXToUserService ryxxToUserService;

    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "同步用户信息")
    @RequestMapping(value = "/syncUser/{period}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean syncUser(@PathVariable Long period) {
        //周期任务执行的开始时间
        Date beginTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (syncUserTimer != null) {
            syncUserTimer.cancel();
        }
        syncUserTimer = new Timer();
        logger.info("period(分钟)---" + period);
        period = 1000 * 60 * period;

        syncUserTimer.schedule(new TimerTask() {
            int num = 0;

            @Override
            public void run() {
                logger.info("第几次---" + ++num);
                //取db2数据的开始、截止时间
                Date startTime = null;
                Date endTime = new Date();

                SyncLog lastSyncLog = synLogService.getLastSynLog("user");
                if (lastSyncLog == null) {
                    startTime=new Date(0);
                } else {
                    startTime = lastSyncLog.getsEndtime();
                }

                // 执行你的方法
                List<PtsVwRyxx> list = ryxxToUserService.getRYXXList();
                if (list != null && list.size() > 0) {
                    for (PtsVwRyxx item : list) {
                        boolean bl = sysUserService.isHave(item.getCodeno());
                        if (bl == false) {
                            boolean blInsert = ryxxToUserService.insertRYXX(item);
                            if (blInsert == false) {
                                throw new NoneSaveException();
                            }
                        }
                    }
                    SyncLog syncLog = new SyncLog();
                    syncLog.setsCount((long) list.size());
                    syncLog.setsEndtime(endTime);
                    syncLog.setsStarttime(startTime);
                    syncLog.setsSuccess(true);
                    syncLog.setsType("user");
                    synLogService.insertSynLog(syncLog);

                    logger.info("startTime---" + startTime);
                    logger.info("endTime---" + endTime);
                    logger.info("同步 用户 的数据count---" + list.size());
                } else {
                    logger.info("startTime---" + startTime);
                    logger.info("endTime---" + endTime);
                    logger.info("暂无需要同步 用户 的数据");
                }
            }
        }, beginTime, period);
        return true;
    }
}
