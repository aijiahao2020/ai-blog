package com.aijiahao.blog.admin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aijiahao
 * @create 2023/8/21  13:07
 */
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    // 用户ID
    private Long userId;

    // 角色ID
    private Long roleId;

}
