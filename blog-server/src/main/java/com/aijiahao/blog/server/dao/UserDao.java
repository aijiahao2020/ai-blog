package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 用户表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Mapper
public interface UserDao {

    /**
     * 查询所有用户,不包括密码和salt(未分页)
     */
    List<UserEntity> selectAll();
    

    /**
     * 根据用户名，查询用户
     */
    UserEntity selectByUserName(String username);

    /**
     * 根据用户ID，查询用户
     */
    UserEntity selectByUserId(Long userId);

    /**
     * 插入用户
     */
    boolean insertUser(UserEntity userEntity);

    /**
     * 更新用户
     */
    boolean updateByUserId(UserEntity userEntity);

    /**
     * 封禁用户
     */
    boolean disableByUserId(Long userId, Date updateTime);
    /**
     * 解禁用户
     */
    boolean enableByUserId(Long userId, Date updateTime);
    /**
     * 删除用户
     */
    boolean deleteByUserId(Long userId);
}
