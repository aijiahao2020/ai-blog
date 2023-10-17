package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aijiahao.blog.server.dao.UserArticleDao;
import com.aijiahao.blog.server.entity.UserArticleEntity;
import com.aijiahao.blog.server.service.UserArticleService;


@Service("userArticleService")
public class UserArticleServiceImpl extends ServiceImpl<UserArticleDao, UserArticleEntity> implements UserArticleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserArticleEntity> page = this.page(
                new Query<UserArticleEntity>().getPage(params),
                new QueryWrapper<UserArticleEntity>()
        );

        return new PageUtils(page);
    }

}
