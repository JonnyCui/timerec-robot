package com.timerec.robot.demo;

import com.timerec.robot.Service.IRobotService;
import com.timerec.robot.Service.impl.RobotServiceImpl;
import com.timerec.robot.entity.Capsule;
import com.timerec.robot.util.HttpClientEllo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


public class Robot implements Job {

    @Autowired
    private IRobotService robotService = new RobotServiceImpl();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 编写要运行的任务Job
        System.out.println("-------- Robot Start --------");

        String address = "https://ello.co/alexgzarate";
        Capsule capsule = HttpClientEllo.main(address);
        capsule.setUserGuid("1000000");

        //添加数据到数据库
        robotService.addCapsule(capsule);
        System.out.println("-------- Uploading Capsules!--------");
    }
}
