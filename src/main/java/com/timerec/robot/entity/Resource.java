package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_resource")
public class Resource implements Serializable {
    //胶囊资源
    public static final int DATA_TYPE_CAPSULE = 1;

    //用户资源
    public static final int DATA_TYPE_USER = 2;

    /**
     *
     */
    @TableId(value = "RESOURCE_ID", type = IdType.AUTO)
    private Long resourceId;

    /**
     *
     */
    @TableField("GUID")
    private String guid;

    /**
     *
     */
    @TableField("MEDIA_TYPE")
    private String mediaType;

    /**
     * 域名
     */
    @TableField("DOMAIN")
    private String domain;

    /**
     *
     */
    @TableField("PATH_NAME")
    private String pathName;

    @TableField("WIDTH")
    private Integer width;

    @TableField("HEIGHT")
    private Integer height;

    @TableField("DATA_TYPE")
    private Integer dataType;

    @TableField("PLAY_TIME")
    private Integer playTime;

    @TableField("TYPE_ID")
    private Integer typeId;

    @TableField("DATA_GUID")
    private String dataGuid;

    /**
     *
     */
    @TableField("CREATE_TIME")
    private Date createTime;

}
