package com.timerec.robot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private String caption;

    private String imgUrl;

    private String videoUrl;

    private Date createTime;

}
