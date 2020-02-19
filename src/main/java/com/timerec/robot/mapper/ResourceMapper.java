package com.timerec.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timerec.robot.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    int checkRes(@Param("resource") Resource resource);
}
