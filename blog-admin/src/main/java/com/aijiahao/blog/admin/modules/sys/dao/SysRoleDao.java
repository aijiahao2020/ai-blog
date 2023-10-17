package com.aijiahao.blog.admin.modules.sys.dao;

import com.aijiahao.blog.admin.modules.sys.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author aijiahao
 * @create 2023/8/21  13:15
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
