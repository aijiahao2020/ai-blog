package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {
	
}
