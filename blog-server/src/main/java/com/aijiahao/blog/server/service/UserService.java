package com.aijiahao.blog.server.service;

import com.aijiahao.blog.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import com.aijiahao.blog.server.entity.UserEntity;

import java.util.Map;

/**
 * 用户表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
public interface UserService {

    /**
     * 分页查询
     */
    // PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户名，查询系统用户
     */
    UserEntity queryByUserName(String username);

    /**
     * 根据用户名，查询系统用户
     */
    UserEntity queryByUserId(Long userId);

    /**
     * 保存用户
     */
    boolean saveUser(UserEntity user);

    /**
     * 更新用户
     */
    boolean updateByUserId(UserEntity user);

    /**
     * 删除用户
     */
    boolean deleteByUserId(Long userId);

    /**
     * 封禁用户
     */
    boolean disableByUserId(Long userId);


    /**
     * 解禁用户
     */
    boolean enableByUserId(Long userId);

}

