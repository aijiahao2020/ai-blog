package com.aijiahao.blog.admin.modules.sys.service;


import com.aijiahao.blog.admin.modules.sys.entity.SysUserRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author aijiahao
 * @create 2023/8/21  13:06
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 保存用户与角色关系
     *
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
