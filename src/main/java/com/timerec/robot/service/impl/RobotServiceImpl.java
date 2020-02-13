package com.timerec.robot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.entity.Capsule;
import com.timerec.robot.mapper.RobotMapper;
import com.timerec.robot.service.IRobotService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Capsule> implements IRobotService {

    @Autowired
    private RobotMapper robotMapper;

    @Override
    public void addCapsule(Capsule capsule) {


        capsule.setDeleted(0);
        String capGuid = UUID.randomUUID().toString().replaceAll("-","");
        capsule.setCapsuleGuid(capGuid);
        capsule.setCreateTime(DateUtil.now());
        capsule.setQueryTime(capsule.getCreateTime());

        // 打印新建胶囊结果
        System.out.println(capsule);

        this.save(capsule);
    }

    @Override
    public int capsTotal(){
        return robotMapper.getCapsTotal();
    }
}
