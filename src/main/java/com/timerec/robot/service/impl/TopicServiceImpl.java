package com.timerec.robot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.entity.Topic;
import com.timerec.robot.mapper.TopicMapper;
import com.timerec.robot.service.ITopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public void addTopic(Topic topic){
        this.save(topic);
    }

    public boolean isTopicExist(String topicName){
        return topicMapper.checkTopic(topicName) != 0;
    }

    public String selectTopicGuid(String topicName){
        return topicMapper.selectTopicGuid(topicName);
    }
}
