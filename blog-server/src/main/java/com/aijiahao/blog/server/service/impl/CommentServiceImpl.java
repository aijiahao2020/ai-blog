package com.aijiahao.blog.server.service.impl;

import com.aijiahao.blog.common.utils.PageUtils;
import com.aijiahao.blog.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aijiahao.blog.server.dao.CommentDao;
import com.aijiahao.blog.server.entity.CommentEntity;
import com.aijiahao.blog.server.service.CommentService;


@Service("commentService")
public class CommentServiceImpl implements CommentService {

    // @Override
    // public PageUtils queryPage(Map<String, Object> params) {
    //     IPage<CommentEntity> page = this.page(
    //             new Query<CommentEntity>().getPage(params),
    //             new QueryWrapper<CommentEntity>()
    //     );
    //
    //     return new PageUtils(page);
    // }

}
