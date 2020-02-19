package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_topic")
public class Topic implements Serializable {

    @TableField("TOPIC_ID")
    private int topicId;

    @TableField("TOPIC_GUID")
    private String topicGuid;

    @TableField("TOPIC_NAME")
    private String topicName;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("TOPIC_WEIGHT")
    private int topicWeight;

    @TableField("CAPSULE_NUM")
    private int capsuleNum;
}
