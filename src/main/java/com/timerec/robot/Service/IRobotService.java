package com.timerec.robot.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.timerec.robot.entity.Capsule;


public interface IRobotService extends IService<Capsule> {

    /**
     * 插入新创建的胶囊数据
     * @param entity
     */
    void addCapsule(Capsule entity);
}
