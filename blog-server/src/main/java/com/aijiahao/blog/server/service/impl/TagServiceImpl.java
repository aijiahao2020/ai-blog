package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aijiahao.blog.server.dao.TagDao;
import com.aijiahao.blog.server.entity.TagEntity;
import com.aijiahao.blog.server.service.TagService;


@Service("tagService")
public class TagServiceImpl implements TagService {

    // @Override
    // public PageUtils queryPage(Map<String, Object> params) {
    //     IPage<TagEntity> page = this.page(
    //             new Query<TagEntity>().getPage(params),
    //             new QueryWrapper<TagEntity>()
    //     );
    //
    //     return new PageUtils(page);
    // }

}
