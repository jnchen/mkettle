package com.jnchen.mkettle.utils.task;

import java.util.Date;

/**
 * Created by caojingchen on 2017/12/29.
 */
public class TestTask implements Runnable {

    @Override
    public void run() {
        System.out.println(new Date()+"我的测试任务");
    }
}
