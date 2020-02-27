package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_topic")
public class Topic implements Serializable {

    @TableId(value = "TOPIC_ID", type = IdType.AUTO)
    private long topicId;

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
