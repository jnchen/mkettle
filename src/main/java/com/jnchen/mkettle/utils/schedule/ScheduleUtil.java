package com.jnchen.mkettle.utils.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by caojingchen on 2017/12/29.
 */
public class ScheduleUtil {
    private static Logger logger = LoggerFactory.getLogger(ScheduleUtil.class);
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public static ThreadPoolTaskScheduler getThreadPoolTaskScheduler(){
        if(null==threadPoolTaskScheduler){
            threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(10);
            threadPoolTaskScheduler.initialize();
        }
        return threadPoolTaskScheduler;
    }

    /**
     * 启动一个Task
     * @param taskName task的名称
     * @param task task的Runnable
     * @param cron task执行的cron表达式
     * @return
     */
    public static ScheduleResult startTask(String taskName,Runnable task,String cron){
        ScheduleResult result = new ScheduleResult();
        if(SchedulePool.getFutureTasks().containsKey(taskName)){
            result.setSuccess(false);
            result.setMessage("该任务已存在:"+taskName);
            return result;
        }
        ScheduledFuture<?> future = getThreadPoolTaskScheduler().schedule(task,new CronTrigger(cron));
        SchedulePool.getFutureTasks().put(taskName,future);
        logger.debug("StartTask:"+taskName);
        result.setSuccess(true);
        return result;
    }

    /**
     * 停止一个task
     * @param taskName task的名称
     * @return
     */
    public static ScheduleResult stopTask(String taskName){
        ScheduleResult result = new ScheduleResult();
        if(!SchedulePool.getFutureTasks().containsKey(taskName)){
            result.setSuccess(false);
            result.setMessage("该任务不存在:"+taskName);
            return result;
        }
        ScheduledFuture<?> future = SchedulePool.getFutureTasks().get(taskName);
        if(null==future){
            result.setSuccess(false);
            result.setMessage("该任务不存在:"+taskName);
            return result;
        }
        future.cancel(true);
        SchedulePool.getFutureTasks().remove(taskName);
        logger.debug("StopTask:"+taskName);
        result.setSuccess(true);
        return result;
    }

    public static ScheduleResult changeTask(String taskName,Runnable task,String cron){
        ScheduleResult result = new ScheduleResult();
        ScheduleResult stopResult = stopTask(taskName);
        if(!stopResult.isSuccess()){
            return stopResult;
        }
        ScheduleResult startResult = startTask(taskName,task,cron);
        if(!startResult.isSuccess()){
            return startResult;
        }
        logger.debug("ChangeTask:"+taskName);
        result.setSuccess(true);
        return result;
    }
}
