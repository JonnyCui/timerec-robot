package com.timerec.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timerec.robot.entity.ContentArticle;

public interface IContentArticleService extends IService<ContentArticle> {
    /**
     * 插入新创建的文章
     * @param article
     */
    void addArticle(ContentArticle article);

    ContentArticle selectArticle(int randId);

    int getCount();
}
