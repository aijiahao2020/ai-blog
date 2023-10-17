package com.aijiahao.blog.server.service;

import com.aijiahao.blog.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import com.aijiahao.blog.server.entity.TagEntity;

import java.util.Map;

/**
 * 博客标签表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
public interface TagService extends IService<TagEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

