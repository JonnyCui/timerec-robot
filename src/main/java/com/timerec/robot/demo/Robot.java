package com.timerec.robot.demo;

import com.timerec.robot.entity.Capsule;
import com.timerec.robot.service.IRobotService;
import com.timerec.robot.util.HttpClientEllo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class Robot {

    @Resource
    private IRobotService robotService;


    //    @Scheduled(cron = "0 0 10-22  * * ?") // 10Am - 10Pm  每小时执行一次

    @Scheduled(cron = "1/10 * * * * ? ") //测试用 每十秒执行一次
    public void jobScheduled() {

        // 编写要运行的任务Job
        System.out.println("-------- Robot Start --------");

        String address = "https://ello.co/phototkh";
        Capsule capsule = HttpClientEllo.main(address);
        capsule.setUserGuid("1000000");

        //添加数据到数据库
        robotService.addCapsule(capsule);
        System.out.println("-------- Uploading Capsules!--------");


       //查询数据 胶囊总数
        System.out.println("Capsule Total " + robotService.capsTotal());
    }
}
