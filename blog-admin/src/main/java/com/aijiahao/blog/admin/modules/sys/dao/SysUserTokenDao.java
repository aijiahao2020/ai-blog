package com.aijiahao.blog.admin.modules.sys.dao;


import com.aijiahao.blog.admin.modules.sys.entity.SysUserTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aijiahao
 * @create 2023/8/22  22:29
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {
    SysUserTokenEntity queryByToken(String token);
}
