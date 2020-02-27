package com.timerec.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timerec.robot.entity.CapsuleTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CapsuleTopicMapper extends BaseMapper<CapsuleTopic> {

    int isExist(@Param("entity") CapsuleTopic entity);

    int countTopic(@Param("topicGuid") String topicGuid);
}
