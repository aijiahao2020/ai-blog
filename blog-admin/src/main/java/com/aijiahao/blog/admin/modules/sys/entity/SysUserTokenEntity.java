package com.aijiahao.blog.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author aijiahao
 * @create 2023/8/22  22:13
 */
@Data
@TableName("sys_user_token")
public class SysUserTokenEntity implements Serializable {
    private static final long serialVersionID = 1L;

    //用户ID
    @TableId(type = IdType.INPUT)
    private Long userId;
    //token
    private String token;
    //过期时间
    private Date expireTime;
    //更新时间
    private Date updateTime;

}
