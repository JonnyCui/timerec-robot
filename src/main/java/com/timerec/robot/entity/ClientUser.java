package com.timerec.robot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
public class ClientUser implements Serializable {

    // 用户状态：有效
    public static final String STATUS_VALID = "1";
    // 用户状态：锁定
    public static final String STATUS_LOCK = "0";
    // 默认头像
    public static final String DEFAULT_AVATAR = "http://i.mytimerec.com/default/avatar.png";

    public static final Integer CREATE_FROM_APP = 1;
    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";

    /**
     * 用户 ID
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    /**
     * 唯一ID
     */
    @TableField("GUID")
    private String guid;

    @TableField("COUNTRY_CODE")
    private String countryCode;

    @TableField("NICKNAME")
    private String nickname;

    /**
     * 从哪创建的
     * 0 后台
     * 1 App
     */
    @TableField("CREATE_FROM")
    private Integer createFrom;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 联系电话
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 性别 0男 1女 2 保密
     */
    @TableField("SSEX")
    //@ExcelField(value = "性别", writeConverterExp = "0=男,1=女,2=保密")
    private String sex;

    /**
     * 头像
     */
    @TableField("AVATAR")
    private String avatar;

    @TableField("REC_ID")
    private String recId;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    @TableField("BIRTHDAY")
    private String birthday;

    @TableField(exist = false)
    private String createTimeFrom;

    @TableField(exist = false)
    private String createTimeTo;

    @TableField(exist = false)
    private String channel;

    @TableField(exist = false)
    private Integer deviceType;

    @TableField(exist = false)
    private Integer fans;

    @TableField(exist = false)
    private Integer following;

    @TableField(exist = false)
    private Integer publish;

    @TableField(exist = false)
    private String country;

    @TableField(exist = false)
    private String city;

    @TableField(exist = false)
    private Double distance;

    @TableField(exist = false)
    private String searchText;

}

