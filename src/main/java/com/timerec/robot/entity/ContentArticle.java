package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_content_article")
public class ContentArticle {

    // 文章ID
    @TableId(value = "CONTENT_ARTICLE_ID", type = IdType.AUTO)
    private Long ContentArticleId;

    // 文章唯一GUID
    @TableField("ARTICLE_GUID")
    private String articleGuid;

    // 文章内容
    @TableField("CONTENT_STR")
    private  String contentStr;

    // 素材链接
    @TableField("RESOURCE_URL")
    private String resourceUrl;

    // 素材类型
    @TableField("RESOURCE_TYPE")
    private int resourceType;

    // 指定发布人
    @TableField("ASSIGNED_USER_GUID")
    private String assignedUserGuid;

    // 待发布
    @TableField("PENDING")
    private int pending;
}
