package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_capsule_topic")
public class CapsuleTopic implements Serializable {

    @TableField("CAPSULE_TOPIC_ID")
    private int capsuleTopicId;

    @TableField("TOPIC_GUID")
    private String topicGuid;

    @TableField("CAPSULE_GUID")
    private String capsuleGuid;

}
