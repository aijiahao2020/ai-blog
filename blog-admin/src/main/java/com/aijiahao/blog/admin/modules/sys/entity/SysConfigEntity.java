package com.aijiahao.blog.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author aijiahao
 * @create 2023/8/21  11:49
 */
@Data
@TableName("sys_captcha")
public class SysConfigEntity {
    @TableId(type = IdType.INPUT)
    private String uuid;

    // 验证码
    private String code;

    //过期时间
    private Date expireTime;

}
