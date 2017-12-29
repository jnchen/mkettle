package com.jnchen.mkettle.utils.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by caojingchen on 2017/12/29.
 */

public class SchedulePool {
    private static Logger logger = LoggerFactory.getLogger(SchedulePool.class);
    private static Map<String,ScheduledFuture<?>> futureTasks;

    public static Map<String,ScheduledFuture<?>> getFutureTasks(){
        if(null==futureTasks){
            futureTasks = new HashMap<>();
        }
        return futureTasks;
    }
}
