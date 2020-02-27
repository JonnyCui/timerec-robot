package com.timerec.robot.demo;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class RobotScheduler {

    public void main(String[] args) throws SchedulerException, InterruptedException {

        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与Robot类绑定(Job执行内容)
//        JobDetail jobDetail = JobBuilder.newJob(Robot.class)
//                .withIdentity("job1", "group1")
//                .build();

        // 3、构建Trigger实例
         Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 10-22  * * ? *")).build(); // 10Am - 10Pm  每小时执行一次


        //     测试用
             .startNow()//立即生效
             .withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
//            .withIntervalInSeconds(10)//每隔10s执行一次
//             .repeatForever()).build();

        //4、执行
//        scheduler.scheduleJob(jobDetail, trigger);
//        System.out.println("--------scheduler start ! ------------");
//        scheduler.start();

        //睡眠  15s后结束
//        TimeUnit.SECONDS.sleep(15);
//        scheduler.shutdown();
//        System.out.println("--------scheduler shutdown ! ------------");


    }
}