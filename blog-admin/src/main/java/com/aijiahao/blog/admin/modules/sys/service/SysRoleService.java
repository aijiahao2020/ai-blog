package com.aijiahao.blog.admin.modules.sys.service;


import com.aijiahao.blog.admin.modules.common.utils.PageUtils;
import com.aijiahao.blog.admin.modules.sys.entity.SysRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author aijiahao
 * @create 2023/8/21  12:46
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveRole(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
