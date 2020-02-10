package com.timerec.robot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Entity Capsule
 */

@Data
@TableName("t_capsule")
public class Capsule {
    //所有人
    public static final int WHO_SEE_ALL = 1;

    //指定人
    public static final int WHO_SEE_ASSIGN = 2;

    //未来的自己
    public static final int WHO_SEE_FUTURE_ME = 3;

    //胶囊类型
    public static final int TYPE_CAPSULE = 4;

    //逻辑删除
    public static final int DISABLE = 1;


    /**
     *
     */
    @TableId(value = "CAPSULE_ID", type = IdType.AUTO)
    private Long capsuleId;

    /**
     *
     */
    @TableField("CAPSULE_GUID")
    private String capsuleGuid;

    @TableField("TITLE")
    private String title;

    /**
     *
     */
    @TableField("USER_GUID")
    private String userGuid;

    /**
     *
     */
    @TableField("COVER_IMAGE_ID")
    private String coverImageId;

    /**
     *
     */
    @TableField("CONTENT_STR")
    private String contentStr;

    /**
     *
     */
    @TableField("COMMENT_NUM")
    private Integer commentNum;

    /**
     *
     */
    @TableField("VIEW_NUM")
    private Integer viewNum;

    /**
     *
     */
    @TableField("PRAISE_NUM")
    private Integer praiseNum;

    /**
     *
     */
    @TableField("COLLECT_NUM")
    private Integer collectNum;

    /**
     *
     */
    @TableField("FULL_ADDRESS")
    private String fullAddress;

    /**
     *
     */
    @TableField("GPS_LAT")
    private Float gpsLat;
    /**
     *
     */
    @TableField("GPS_LNG")
    private Float gpsLng;

    @TableField("CONDITION_ADDRESS")
    private String conditionAddress;

    /**
     *
     */
    @TableField("CONDITION_LAT")
    private Float conditionLat;
    /**
     *
     */
    @TableField("CONDITION_LNG")
    private Float conditionLng;

    /**
     * 谁可以收到
     */
    @TableField("WHO_SEE")
    private Integer whoSee;

    @TableField("CAN_COMMENT")
    private Integer canComment;

    @TableField("DELETED")
    private Integer deleted;

    @TableField("TIME_ZONE_OFFSET_MIN")
    private Integer timeZoneOffsetMin;

    /**
     *
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("FUTURE_TIME")
    private Date futureTime;

    //用于做SQL查询的时间，如果是时间胶囊则就是设置的时间胶囊时间，否则就是胶囊创建时间
    @TableField("QUERY_TIME")
    private Date queryTime;

    //胶囊类型，0 普通 1 位置 2 时间
    @TableField("CAPSULE_TYPE")
    private Integer capsuleType;

    //资源内容类型 0.text 1.image 2.video 3.audit
    @TableField("RES_TYPE")
    private Integer resType;

    //打开位置胶囊的距离
    @TableField("SCOPE_DISTANCE")
    private Integer scopeDistance;

    @TableField("LANGUAGE")
    private String language;

    //点赞时间
    @TableField(exist = false)
    private Date praiseTime;

    @TableField(exist = false)
    private Date commentTime;

    @TableField(exist = false)
    private String createTimeFrom;

    @TableField(exist = false)
    private String createTimeTo;

    @TableField(exist = false)
    private String nickname;

    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private String searchText;

    @TableField(exist = false)
    private String commentContent;

    @TableField(exist = false)
    private String country;

    @TableField(exist = false)
    private String city;

    @TableField(exist = false)
    private Integer complaintCount;

    //判断是否是位置胶囊
    public boolean isLocationCapsule(){

        return StringUtils.isNotBlank(getConditionAddress());
    }

    //判断是否是时间胶囊
    public boolean isTimeCapsule(){

        return (null != getFutureTime() && getFutureTime().getTime() > 0);
    }
}
