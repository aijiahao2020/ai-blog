package com.aijiahao.blog.admin.modules.sys.service;


import com.aijiahao.blog.admin.modules.sys.entity.SysUserEntity;
import com.aijiahao.blog.admin.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * @author aijiahao
 * @create 2023/8/22  22:11
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(Long userId);

    /**
     * 通过token查询用户token信息
     *
     * @param token
     * @return
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
