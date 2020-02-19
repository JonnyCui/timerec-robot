package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("content_article")
public class ContentArticle {

    // 文章ID
    @TableId(value = "CONTENT_ARTICLE_ID", type = IdType.AUTO)
    private Long ContentArticleId;

    // 文章唯一GUID
    @TableId("ARTICLE_GUID")
    private String articleGuid;

    // 文章内容
    @TableId("CONTENT_STR")
    private  String contentStr;

    // 素材链接
    @TableId("RESOURCE_URL")
    private String resourceUrl;

    // 素材类型
    @TableId("RESOURCE_TYPE")
    private int resourceType;

    // 指定发布人
    @TableId("ASSIGNED_USER_GUID")
    private String assignedUserGuid;

    // 待发布
    @TableId("PENDING")
    private int pending;
}
