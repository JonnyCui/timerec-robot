package com.timerec.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timerec.robot.entity.ContentArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentArticleMapper extends BaseMapper<ContentArticle> {
    int checkArticle(@Param("entity") ContentArticle entity);
}
