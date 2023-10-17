package com.aijiahao.blog.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客标签表
 * 
 * @author aijiahao
 * @email aijiahao2020@outlook.com
 * @date 2023-09-13 14:37:23
 */
@Data
@TableName("tag")
public class TagEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 标签id
	 */
	@TableId
	private Integer tagId;
	/**
	 * 标签名
	 */
	private String tagName;
	/**
	 * 标签别名
	 */
	private String tagAlias;
	/**
	 * 标签描述
	 */
	private String tagDescription;

}
