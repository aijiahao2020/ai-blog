package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.UserArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与文章对应表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Mapper
public interface UserArticleDao {
    
    void insertOne(UserArticleEntity userArticleEntity);
	
}
