/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.27 : Database - aiblog_bms
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aiblog_bms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `aiblog_bms`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '博文id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '博文标题',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '博文摘要',
  `content` longtext COLLATE utf8mb4_general_ci COMMENT '博文内容',
  `published` tinyint DEFAULT NULL COMMENT '发布状态',
  `views` int DEFAULT NULL COMMENT '浏览量',
  `thumbs` int DEFAULT NULL COMMENT '点赞数',
  `comments` int DEFAULT NULL COMMENT '评论数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博文表';

/*Data for the table `article` */

/*Table structure for table `article_tag` */

DROP TABLE IF EXISTS `article_tag`;

CREATE TABLE `article_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` bigint DEFAULT NULL COMMENT '博文id',
  `tag_id` int DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博文标签对应表';

/*Data for the table `article_tag` */

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` bigint DEFAULT NULL COMMENT '发表评论id',
  `article_id` bigint DEFAULT NULL COMMENT '博文id',
  `thumbs` int DEFAULT NULL COMMENT '评论点赞数',
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  `parent_comment_id` bigint DEFAULT NULL COMMENT '父评论id',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='评论表';

/*Data for the table `comment` */

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `tag_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签名',
  `tag_alias` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签别名',
  `tag_description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签描述',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客标签表';

/*Data for the table `tag` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL UNIQUE COMMENT '用户名',
  `nickname` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户别名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '盐',
  `status` tinyint DEFAULT NULL COMMENT '状态 0禁用1正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建用户id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`nickname`,`password`,`mobile`,`email`,`avatar`,`salt`,`status`,`create_time`,`update_time`,`create_user_id`) values (1,'aijiahao','hao','9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d','13612345678','aijiahao2020@outlook.com',NULL,'YzcmCZNvbXocrsz9dm8e',1,'2023-06-18 11:11:11','2023-06-18 11:11:11',1);

/*Table structure for table `user_article` */

DROP TABLE IF EXISTS `user_article`;

CREATE TABLE `user_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint DEFAULT NULL COMMENT '用户Id',
  `article_id` bigint DEFAULT NULL COMMENT '博文id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与文章对应表';


CREATE TABLE `user_token` (
    `user_id` BIGINT NOT NULL,
    `token` VARCHAR(100) NOT NULL COMMENT 'token',
    `expire_time` DATETIME DEFAULT NULL COMMENT '过期时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `token` (`token`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户Token';
/*Data for the table `user_article` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
