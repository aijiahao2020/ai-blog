package com.aijiahao.blog.server.service;

import com.aijiahao.blog.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import com.aijiahao.blog.server.entity.UserArticleEntity;

import java.util.Map;

/**
 * 用户与文章对应表
 *
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
public interface UserArticleService extends IService<UserArticleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

