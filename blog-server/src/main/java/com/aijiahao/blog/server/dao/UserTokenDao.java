package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.UserTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

/**
 * @author aijiahao
 * @create 2023/12/13  22:16
 * @description
 */
@Mapper
public interface UserTokenDao {

    /**
     * 根据userId 查询
     * @param userId
     * @return
     */
    UserTokenEntity selectByUserId(Long userId);

    /**
     * 根据userId 更新
     */
    void updateByUserId(UserTokenEntity userTokenEntity);

    /**
     * insert 一条token记录
     * @param userTokenEntity
     */
    void insertToken(UserTokenEntity userTokenEntity);

    /**
     * 根据userId 删除token记录
     * @param userId
     */
    void deleteByUserId(Long userId);

}
