package com.timerec.robot.models;

import com.timerec.robot.demo.HttpClientEllo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Robot implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 编写要运行的任务Job
        System.out.println("Robot Start Uploading Capsules!");
        HttpClientEllo.main("https://ello.co/alexgzarate");
    }
}
