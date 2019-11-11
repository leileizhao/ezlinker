/*
Navicat MySQL Data Transfer

Source Server         : Zhaolei
Source Server Version : 50724
Source Host           : 112.74.44.130:3306
Source Database       : ezlinker

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-11-11 15:42:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ez_component
-- ----------------------------
DROP TABLE IF EXISTS `ez_component`;
CREATE TABLE `ez_component` (
  `id` int(11) NOT NULL COMMENT 'PK',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `type` int(4) NOT NULL COMMENT '类型',
  `state` int(11) unsigned NOT NULL COMMENT '状态',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `protocol` varchar(20) NOT NULL COMMENT '协议',
  `model` varchar(50) DEFAULT NULL COMMENT '型号',
  `tag` varchar(50) DEFAULT NULL COMMENT '标签',
  `sn` varchar(64) DEFAULT NULL COMMENT '序列号',
  `token` varchar(200) NOT NULL COMMENT '密钥',
  `is_superuser` int(4) NOT NULL COMMENT '是否是超级管理员',
  `data_area` json DEFAULT NULL COMMENT '数据域',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='设备上面的模块，和设备是多对一关系';

-- ----------------------------
-- Table structure for ez_component_type
-- ----------------------------
DROP TABLE IF EXISTS `ez_component_type`;
CREATE TABLE `ez_component_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `value` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='模块的类型，系统内部使用';

-- ----------------------------
-- Table structure for ez_device
-- ----------------------------
DROP TABLE IF EXISTS `ez_device`;
CREATE TABLE `ez_device` (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL COMMENT '项目',
  `product_id` int(11) NOT NULL COMMENT '产品',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `logo` varchar(200) NOT NULL COMMENT 'Logo',
  `location` varchar(20) DEFAULT NULL COMMENT '地理位置',
  `model` varchar(20) DEFAULT NULL COMMENT '型号',
  `tag` varchar(20) DEFAULT NULL COMMENT '标签',
  `industry` varchar(50) DEFAULT NULL COMMENT '厂家',
  `sn` varchar(64) NOT NULL COMMENT '序列号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `parameter` json NOT NULL COMMENT '参数',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='实际设备，是产品的一个实例。';

-- ----------------------------
-- Table structure for ez_feature
-- ----------------------------
DROP TABLE IF EXISTS `ez_feature`;
CREATE TABLE `ez_feature` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` varchar(4) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `cmd_key` varchar(20) NOT NULL,
  `cmd_value` json NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='产品的功能（特性），和设备是多对多的关系，通过中间表关联起来';

-- ----------------------------
-- Table structure for ez_internal_message
-- ----------------------------
DROP TABLE IF EXISTS `ez_internal_message`;
CREATE TABLE `ez_internal_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `type` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `status` int(4) NOT NULL,
  `user_id` int(11) NOT NULL,
  `record_version` int(11) unsigned NOT NULL COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='站内信';

-- ----------------------------
-- Table structure for ez_mqtt_topic
-- ----------------------------
DROP TABLE IF EXISTS `ez_mqtt_topic`;
CREATE TABLE `ez_mqtt_topic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `topic` varchar(200) DEFAULT NULL,
  `access` int(4) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  `allow` int(4) DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='MQTT的TOPIC记录';

-- ----------------------------
-- Table structure for ez_permission
-- ----------------------------
DROP TABLE IF EXISTS `ez_permission`;
CREATE TABLE `ez_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `label` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `resource` varchar(60) NOT NULL,
  `type` int(4) NOT NULL,
  `parent` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户权限';

-- ----------------------------
-- Table structure for ez_product
-- ----------------------------
DROP TABLE IF EXISTS `ez_product`;
CREATE TABLE `ez_product` (
  `id` int(11) NOT NULL COMMENT 'PK',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `name` varchar(20) NOT NULL COMMENT '产品名称',
  `logo` varchar(200) NOT NULL COMMENT '产品logo',
  `tag` varchar(20) DEFAULT NULL COMMENT '标签',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `parameter` json NOT NULL COMMENT '参数',
  `description` varchar(200) DEFAULT NULL COMMENT '描述文字',
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='产品（设备的抽象模板）';

-- ----------------------------
-- Table structure for ez_product_future
-- ----------------------------
DROP TABLE IF EXISTS `ez_product_future`;
CREATE TABLE `ez_product_future` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `future_id` int(11) NOT NULL COMMENT '功能ID',
  `version` int(11) unsigned NOT NULL,
  `x` int(4) unsigned zerofill NOT NULL DEFAULT '0000',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关联功能和产品的关系表：\r\n产品：\r\n---功能A \r\n---功能B\r\n---功能C';

-- ----------------------------
-- Table structure for ez_product_type
-- ----------------------------
DROP TABLE IF EXISTS `ez_product_type`;
CREATE TABLE `ez_product_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `value` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='产品类型';

-- ----------------------------
-- Table structure for ez_project
-- ----------------------------
DROP TABLE IF EXISTS `ez_project`;
CREATE TABLE `ez_project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `logo` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='项目';

-- ----------------------------
-- Table structure for ez_role
-- ----------------------------
DROP TABLE IF EXISTS `ez_role`;
CREATE TABLE `ez_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `label` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `parent` int(11) unsigned zerofill NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

-- ----------------------------
-- Table structure for ez_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ez_role_permission`;
CREATE TABLE `ez_role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色和权限关联表';

-- ----------------------------
-- Table structure for ez_user
-- ----------------------------
DROP TABLE IF EXISTS `ez_user`;
CREATE TABLE `ez_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `avatar` varchar(100) NOT NULL COMMENT '头像地址',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `real_name` varchar(20) DEFAULT NULL COMMENT '实名',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_type` int(4) NOT NULL COMMENT '用户类型：普通用户【1】，企业用户【2】，VIP用户【3】',
  `status` int(4) NOT NULL COMMENT '账户状态：正常【1】，冻结【2】，过期【3】',
  `user_profile_id` int(4) NOT NULL COMMENT '扩展信息',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `last_login_ip` varchar(16) DEFAULT NULL COMMENT '上次登录IP',
  `x` tinyint(1) unsigned zerofill NOT NULL COMMENT '是否删除',
  `record_version` int(11) unsigned NOT NULL COMMENT '记录版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Table structure for ez_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_login_log`;
CREATE TABLE `ez_user_login_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户登录日志';

-- ----------------------------
-- Table structure for ez_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_profile`;
CREATE TABLE `ez_user_profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `region` varchar(30) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `domain` varchar(50) DEFAULT NULL,
  `personal_remark` varchar(200) DEFAULT NULL,
  `certification` varchar(255) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `wechat` varchar(15) DEFAULT NULL,
  `x` tinyint(1) unsigned zerofill DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户的详情';

-- ----------------------------
-- Table structure for ez_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_role`;
CREATE TABLE `ez_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) unsigned zerofill NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户-角色关联';

-- ----------------------------
-- View structure for ez_user_detail_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_user_detail_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`easylinker`@`%` SQL SECURITY DEFINER VIEW `ez_user_detail_view` AS select distinct `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`avatar` AS `avatar`,`a`.`phone` AS `phone`,`a`.`email` AS `email`,`a`.`real_name` AS `real_name`,`a`.`nick_name` AS `nick_name`,`a`.`user_type` AS `user_type`,`a`.`status` AS `status`,`a`.`user_profile_id` AS `user_profile_id`,`a`.`last_login_time` AS `last_login_time`,`a`.`last_login_ip` AS `last_login_ip`,`b`.`region` AS `region`,`b`.`province` AS `province`,`b`.`area` AS `area`,`b`.`city` AS `city`,`b`.`address` AS `address`,`b`.`domain` AS `domain`,`b`.`certification` AS `certification`,`b`.`personal_remark` AS `personal_remark`,`b`.`qq` AS `qq`,`b`.`wechat` AS `wechat`,`a`.`x` AS `x`,`a`.`record_version` AS `record_version`,`a`.`create_time` AS `create_time` from (`ez_user` `a` left join `ez_user_profile` `b` on((`a`.`user_profile_id` = `b`.`id`))) order by `a`.`id` desc ;
