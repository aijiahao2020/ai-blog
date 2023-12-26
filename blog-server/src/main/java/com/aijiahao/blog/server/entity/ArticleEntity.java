package com.aijiahao.blog.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博文表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Data
@TableName("article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 博文id
	 */
	@TableId
	private Long articleId;
	/**
	 * 博文标题
	 */
	private String title;
	/**
	 * 博文摘要
	 */
	private String description;
	/**
	 * 博文内容
	 */
	private String content;
	/**
	 * 发布状态
	 */
	private Integer published;
	/**
	 * 浏览量
	 */
	private Integer views;
	/**
	 * 点赞数
	 */
	private Integer thumbs;
	/**
	 * 评论数
	 */
	private Integer comments;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
