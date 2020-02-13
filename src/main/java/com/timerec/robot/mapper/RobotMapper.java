package com.timerec.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timerec.robot.entity.Capsule;


public interface RobotMapper extends BaseMapper<Capsule>{

    int getCapsTotal();

}
