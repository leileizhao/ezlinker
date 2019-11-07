/*
 Navicat Premium Data Transfer

 Source Server         : Zhaolei
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 112.74.44.130:3306
 Source Schema         : ezlinker

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 07/11/2019 15:37:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ez_component
-- ----------------------------
DROP TABLE IF EXISTS `ez_component`;
CREATE TABLE `ez_component`  (
  `id` int(11) NOT NULL COMMENT 'PK',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `type` int(4) NOT NULL COMMENT '类型',
  `state` int(11) UNSIGNED NOT NULL COMMENT '状态',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `protocol` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '协议',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号',
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密钥',
  `is_superuser` int(4) NOT NULL COMMENT '是否是超级管理员',
  `data_area` json NULL COMMENT '数据域',
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_component_type
-- ----------------------------
DROP TABLE IF EXISTS `ez_component_type`;
CREATE TABLE `ez_component_type`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `value` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_feature
-- ----------------------------
DROP TABLE IF EXISTS `ez_feature`;
CREATE TABLE `ez_feature`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `cmd_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cmd_value` json NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_internal_message
-- ----------------------------
DROP TABLE IF EXISTS `ez_internal_message`;
CREATE TABLE `ez_internal_message`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `type` int(11) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` int(4) NOT NULL,
  `user_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_mqtt_topic
-- ----------------------------
DROP TABLE IF EXISTS `ez_mqtt_topic`;
CREATE TABLE `ez_mqtt_topic`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `topic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access` int(4) NULL DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `allow` int(4) NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_permission
-- ----------------------------
DROP TABLE IF EXISTS `ez_permission`;
CREATE TABLE `ez_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int(4) NOT NULL,
  `parent` int(11) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_product
-- ----------------------------
DROP TABLE IF EXISTS `ez_product`;
CREATE TABLE `ez_product`  (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `logo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `model` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `industry` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parameter` json NOT NULL,
  `running_status` json NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_project
-- ----------------------------
DROP TABLE IF EXISTS `ez_project`;
CREATE TABLE `ez_project`  (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `logo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_role
-- ----------------------------
DROP TABLE IF EXISTS `ez_role`;
CREATE TABLE `ez_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent` int(11) UNSIGNED ZEROFILL NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ez_role_permission`;
CREATE TABLE `ez_role_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_user
-- ----------------------------
DROP TABLE IF EXISTS `ez_user`;
CREATE TABLE `ez_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像地址',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实名',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_type` int(4) NOT NULL COMMENT '用户类型：普通用户【1】，企业用户【2】，VIP用户【3】',
  `status` int(4) NOT NULL COMMENT '账户状态：正常【1】，冻结【2】，过期【3】',
  `user_profile_id` int(4) NOT NULL COMMENT '扩展信息',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登陆时间',
  `last_login_ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录IP',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除',
  `record_version` int(11) UNSIGNED NOT NULL COMMENT '记录版本',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_login_log`;
CREATE TABLE `ez_user_login_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_profile`;
CREATE TABLE `ez_user_profile`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `region` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `province` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `area` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `domain` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `personal_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `certification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qq` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `wechat` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `x` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_role`;
CREATE TABLE `ez_user_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT '0' COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for ez_user_detail_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_user_detail_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ez_user_detail_view` AS select distinct `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`avatar` AS `avatar`,`a`.`phone` AS `phone`,`a`.`email` AS `email`,`a`.`real_name` AS `real_name`,`a`.`nick_name` AS `nick_name`,`a`.`user_type` AS `user_type`,`a`.`status` AS `status`,`a`.`user_profile_id` AS `user_profile_id`,`a`.`last_login_time` AS `last_login_time`,`a`.`last_login_ip` AS `last_login_ip`,`b`.`region` AS `region`,`b`.`province` AS `province`,`b`.`area` AS `area`,`b`.`city` AS `city`,`b`.`address` AS `address`,`b`.`domain` AS `domain`,`b`.`certification` AS `certification`,`b`.`personal_remark` AS `personal_remark`,`b`.`qq` AS `qq`,`b`.`wechat` AS `wechat`,`a`.`x` AS `x`,`a`.`record_version` AS `record_version`,`a`.`create_time` AS `create_time` from (`ez_user` `a` left join `ez_user_profile` `b` on((`a`.`user_profile_id` = `b`.`id`))) order by `a`.`id` desc;

SET FOREIGN_KEY_CHECKS = 1;
