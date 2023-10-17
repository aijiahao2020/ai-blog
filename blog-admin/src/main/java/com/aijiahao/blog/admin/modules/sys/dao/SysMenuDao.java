package com.aijiahao.blog.admin.modules.sys.dao;

import com.aijiahao.blog.admin.modules.sys.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author aijiahao
 * @create 2023/8/22  22:28
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();
}
