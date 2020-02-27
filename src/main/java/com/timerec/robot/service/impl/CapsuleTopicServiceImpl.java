package com.timerec.robot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.entity.CapsuleTopic;
import com.timerec.robot.mapper.CapsuleTopicMapper;
import com.timerec.robot.service.ICapsuleTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CapsuleTopicServiceImpl extends ServiceImpl<CapsuleTopicMapper, CapsuleTopic> implements ICapsuleTopicService {

    @Autowired
    CapsuleTopicMapper capsuleTopicMapper;

    @Override
    public void addCapTopic(CapsuleTopic capTopic){
        this.save(capTopic);
    }

    @Override
    public boolean isExist(CapsuleTopic capTopic) {
        return capsuleTopicMapper.isExist(capTopic) != 0 ;
    }

    @Override
    public int countTopic(String topicGuid) {
        return capsuleTopicMapper.countTopic(topicGuid);
    }


}
