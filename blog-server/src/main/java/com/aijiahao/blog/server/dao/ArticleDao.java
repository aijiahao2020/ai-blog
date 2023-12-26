package com.aijiahao.blog.server.dao;

import com.aijiahao.blog.server.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 博文表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Mapper
public interface ArticleDao {
	/**
	 * 获取所有article信息，不包括content
	 * @return
	 */
	List<ArticleEntity> selectAll();

	/**
	 * insert 一条article 记录
	 * @param articleEntity
	 */
	void insertArticle(ArticleEntity articleEntity);

	/**
	 * count 统计发表的文章数
	 * @return
	 */
	Integer selectNumberByPublished();

	/**
	 * select 根据articleId 查询 article
	 * @param articleId
	 * @return
	 */
	ArticleEntity selectByArticleId(Long articleId);
}
