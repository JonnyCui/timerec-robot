package com.timerec.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timerec.robot.entity.Capsule;


public interface IRobotService extends IService<Capsule> {

    /**
     * 插入新创建的胶囊数据
     * @param entity
     */
    void addCapsule(Capsule entity);

    /**
     * @return 目前所有胶囊的数量
     */
    int capsTotal();

    int checkCaps(Capsule capsule);
}
