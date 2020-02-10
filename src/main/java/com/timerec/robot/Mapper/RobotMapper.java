package com.timerec.robot.Mapper;

import com.timerec.robot.entity.Capsule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RobotMapper{

    void addCapsule(Capsule entity);
}
