package com.timerec.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timerec.robot.entity.CapsuleTopic;

public interface ICapsuleTopicService extends IService<CapsuleTopic> {
    /**
     * 插入与新创建胶囊关联的#话题
     * @param capTopic
     */
    void addCapTopic(CapsuleTopic capTopic);

    boolean isExist(CapsuleTopic capTopic);

    int countTopic(String topicGuid);
}
