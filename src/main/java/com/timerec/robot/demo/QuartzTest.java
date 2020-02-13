package com.timerec.robot.demo;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class QuartzTest extends QuartzJobBean {
    static Logger logger = LoggerFactory.getLogger(QuartzTest.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("我是测试任务，我跑起来了，时间：{}", new Date());
    }
}

