package com.timerec.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timerec.robot.entity.Topic;

public interface ITopicService extends IService<Topic> {

    /**
     * 插入新创建的 #话题
     * @param entity
     */
    void addTopic(Topic entity);

    /**
     * 查询#话题是否已存在
     * @param topicName
     */
    boolean isTopicExist(String topicName);

    /**
     * 查找话题guid
     * @param topicName
     */
    String selectTopicGuid(String topicName);

    int getCount();
}
