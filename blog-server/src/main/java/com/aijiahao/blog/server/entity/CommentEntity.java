package com.aijiahao.blog.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Data
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论id
	 */
	@TableId
	private Long commentId;
	/**
	 * 发表评论id
	 */
	private Long userId;
	/**
	 * 博文id
	 */
	private Long articleId;
	/**
	 * 评论点赞数
	 */
	private Integer thumbs;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论时间
	 */
	private Date createTime;
	/**
	 * 父评论id
	 */
	private Long parentCommentId;

}
