package com.timerec.robot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.entity.Capsule;
import com.timerec.robot.mapper.RobotMapper;
import com.timerec.robot.service.IRobotService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Capsule> implements IRobotService {

    @Autowired
    private RobotMapper robotMapper;

    @Override
    public void addCapsule(Capsule capsule) {

        capsule.setDeleted(0);
        capsule.setCreateTime(DateUtil.now());
        capsule.setQueryTime(capsule.getCreateTime());
        capsule.setWhoSee(4);
        System.out.println(capsule);

        this.save(capsule);
        System.out.println("------ Capsule Uploaded ------");
    }

    @Override
    public int capsTotal(){
        return robotMapper.getCapsTotal();
    }

    @Override
    public int checkCaps(Capsule capsule) {
        return robotMapper.checkCaps(capsule);
    }
}
