package com.timerec.robot.Controller;

import com.timerec.robot.Mapper.RobotMapper;
import com.timerec.robot.entity.Capsule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("capsule")
public class RobotController {

    @Autowired
    private RobotMapper robotMapper;

    //添加数据到数据库
    @PostMapping("createCapsule")
    public void createCapsule(Capsule entity){
        this.robotMapper.addCapsule(entity);
    }
}
