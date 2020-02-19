package com.timerec.robot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timerec.robot.entity.ContentArticle;
import com.timerec.robot.mapper.ContentArticleMapper;
import com.timerec.robot.service.IContentArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ContentArticleServiceImpl extends ServiceImpl<ContentArticleMapper, ContentArticle> implements IContentArticleService {

    @Autowired
    ContentArticleMapper contentArticleMapper;

    @Override
    public void addArticle(ContentArticle article){

        // 查看 article 数据条
        System.out.println(article);

        // 判断文章是否存在于数据库
        if (contentArticleMapper.checkArticle(article) == 0){
            article.setPending(1);
            this.save(article);
            System.out.println("Article Uploaded!");
        }else{
            System.out.println("Article Already Exist!");
        }
    }

    @Override
    public ContentArticle selectArticle(int randId) {
        return contentArticleMapper.selectArticle(randId);
    }

    @Override
    public int getCount() {
        return contentArticleMapper.getCount();
    }

}
