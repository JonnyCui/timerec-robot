package com.timerec.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timerec.robot.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {

    int checkTopic(@Param("topic") String topic);

    String selectTopicGuid(@Param("topicName") String topicName);

    int getCount();
}
