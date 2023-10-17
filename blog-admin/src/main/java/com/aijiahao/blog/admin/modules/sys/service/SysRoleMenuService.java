package com.aijiahao.blog.admin.modules.sys.service;

import com.aijiahao.blog.admin.modules.sys.entity.SysRoleMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author aijiahao
 * @create 2023/8/23  16:39
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 保存或更新都是先删除再添加
     *
     * @param roleId
     * @param menuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
