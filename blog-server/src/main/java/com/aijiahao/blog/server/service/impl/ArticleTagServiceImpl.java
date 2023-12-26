package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aijiahao.blog.server.dao.ArticleTagDao;
import com.aijiahao.blog.server.entity.ArticleTagEntity;
import com.aijiahao.blog.server.service.ArticleTagService;


@Service("articleTagService")
public class ArticleTagServiceImpl implements ArticleTagService {

    // @Override
    // public PageUtils queryPage(Map<String, Object> params) {
    //     IPage<ArticleTagEntity> page = this.page(
    //             new Query<ArticleTagEntity>().getPage(params),
    //             new QueryWrapper<ArticleTagEntity>()
    //     );
    //
    //     return new PageUtils(page);
    // }

}
