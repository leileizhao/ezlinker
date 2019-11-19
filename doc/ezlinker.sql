/*
 Navicat Premium Data Transfer

 Source Server         : 赵雷的服务器
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 112.74.44.130:3306
 Source Schema         : ezlinker

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 18/11/2019 23:09:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ez_component
-- ----------------------------
DROP TABLE IF EXISTS `ez_component`;
CREATE TABLE `ez_component`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `protocol` int(11) NOT NULL COMMENT '协议',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MQTT的clientID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(4) NOT NULL DEFAULT 0 COMMENT '类型',
  `state` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '状态',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号',
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密钥，基于计算生成的一个Base64字符串',
  `is_superuser` int(4) NULL DEFAULT 0 COMMENT '是否是超级管理员',
  `data_area` json NULL COMMENT '数据域',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备上面的模块，和设备是多对一关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_device
-- ----------------------------
DROP TABLE IF EXISTS `ez_device`;
CREATE TABLE `ez_device`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL COMMENT '项目',
  `product_id` int(11) NOT NULL COMMENT '产品',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `logo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Logo',
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `model` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '型号',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `industry` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '厂家',
  `sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列号',
  `type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `parameter` json NOT NULL COMMENT '参数',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '实际设备，是产品的一个实例。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_dictionary_key
-- ----------------------------
DROP TABLE IF EXISTS `ez_dictionary_key`;
CREATE TABLE `ez_dictionary_key`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `label` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示的文本',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典的项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_dictionary_value
-- ----------------------------
DROP TABLE IF EXISTS `ez_dictionary_value`;
CREATE TABLE `ez_dictionary_value`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `key_id` int(11) NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示的文本',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典的值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_feature
-- ----------------------------
DROP TABLE IF EXISTS `ez_feature`;
CREATE TABLE `ez_feature`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cmd_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cmd_value` json NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(4) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品的功能（特性），和设备是多对多的关系，通过中间表关联起来' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_internal_message
-- ----------------------------
DROP TABLE IF EXISTS `ez_internal_message`;
CREATE TABLE `ez_internal_message`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `type` int(11) NOT NULL COMMENT '类型：1系统产生，2设备产生',
  `marked` int(4) NOT NULL COMMENT '是否阅读',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息正文',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息标题',
  `record_version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '删除',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站内信' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_mqtt_topic
-- ----------------------------
DROP TABLE IF EXISTS `ez_mqtt_topic`;
CREATE TABLE `ez_mqtt_topic`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `allow` int(4) NULL DEFAULT NULL COMMENT '是否允许连接: 0=拒绝1=允许',
  `access` int(4) NULL DEFAULT NULL COMMENT '行为类型: 1=订阅2=发布3=订阅+发布',
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MQTT客户端ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MQTT用户名',
  `topic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'MQTT的TOPIC记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_permission
-- ----------------------------
DROP TABLE IF EXISTS `ez_permission`;
CREATE TABLE `ez_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `sort` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '排序值',
  `type` int(4) NOT NULL COMMENT '类型：1目录，2动作',
  `visible` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否显示在UI上',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'UI显示的标签',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称（英文）',
  `resource` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求路径',
  `methods` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '允许的方法',
  `parent` int(11) NOT NULL COMMENT '父资源，如果是0则为顶级',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_product
-- ----------------------------
DROP TABLE IF EXISTS `ez_product`;
CREATE TABLE `ez_product`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `logo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品logo',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `parameter` json NULL COMMENT '参数',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述文字',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品（设备的抽象模板）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_project
-- ----------------------------
DROP TABLE IF EXISTS `ez_project`;
CREATE TABLE `ez_project`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `logo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目' ROW_FORMAT = Dynamic;

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
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ez_role_permission`;
CREATE TABLE `ez_role_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `role_id` int(11) NOT NULL,
  `allow` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和权限关联表' ROW_FORMAT = Dynamic;

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
  `x` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `record_version` int(11) UNSIGNED NOT NULL COMMENT '记录版本',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

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
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录日志' ROW_FORMAT = Dynamic;

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
  `x` tinyint(1) UNSIGNED NULL DEFAULT 0,
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户的详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ez_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ez_user_role`;
CREATE TABLE `ez_user_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for ez_role_permission_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_role_permission_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `easylinker`@`%` SQL SECURITY INVOKER VIEW `ez_role_permission_view` AS select `d`.`id` AS `id`,`c`.`role_id` AS `role_id`,`d`.`label` AS `label`,`d`.`name` AS `name`,`d`.`resource` AS `resource`,`d`.`type` AS `type`,`d`.`methods` AS `methods`,`d`.`parent` AS `parent`,`d`.`description` AS `description` from (((select `a`.`id` AS `id`,`a`.`role_id` AS `role_id`,`a`.`permission_id` AS `permission_id`,`a`.`record_version` AS `record_version`,`a`.`x` AS `x`,`a`.`create_time` AS `create_time` from (`ezlinker`.`ez_role_permission` `a` left join `ezlinker`.`ez_role` `b` on((`a`.`role_id` = `b`.`id`))))) `c` left join `ezlinker`.`ez_permission` `d` on((`d`.`id` = `c`.`permission_id`)));

-- ----------------------------
-- View structure for ez_user_info_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_user_info_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `easylinker`@`%` SQL SECURITY INVOKER VIEW `ez_user_info_view` AS select distinct `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`avatar` AS `avatar`,`a`.`phone` AS `phone`,`a`.`email` AS `email`,`a`.`real_name` AS `real_name`,`a`.`nick_name` AS `nick_name`,`a`.`user_type` AS `user_type`,`a`.`status` AS `status`,`a`.`user_profile_id` AS `user_profile_id`,`a`.`last_login_time` AS `last_login_time`,`a`.`last_login_ip` AS `last_login_ip`,`b`.`region` AS `region`,`b`.`province` AS `province`,`b`.`area` AS `area`,`b`.`city` AS `city`,`b`.`address` AS `address`,`b`.`domain` AS `domain`,`b`.`certification` AS `certification`,`b`.`personal_remark` AS `personal_remark`,`b`.`qq` AS `qq`,`b`.`wechat` AS `wechat`,`a`.`x` AS `x`,`a`.`record_version` AS `record_version`,`a`.`create_time` AS `create_time` from (`ez_user` `a` left join `ez_user_profile` `b` on((`a`.`user_profile_id` = `b`.`id`))) order by `a`.`id` desc;

-- ----------------------------
-- View structure for ez_user_permission_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_user_permission_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `easylinker`@`%` SQL SECURITY DEFINER VIEW `ez_user_permission_view` AS select `f`.`user_id` AS `user_id`,`e`.`role_id` AS `role_id`,`e`.`permission_id` AS `permission_id`,`e`.`allow` AS `allow`,`e`.`resource` AS `resource`,`e`.`methods` AS `methods` from (((select `c`.`role_id` AS `role_id`,`c`.`permission_id` AS `permission_id`,`c`.`allow` AS `allow`,`d`.`resource` AS `resource`,`d`.`methods` AS `methods` from (((select `a`.`permission_id` AS `permission_id`,`a`.`allow` AS `allow`,`a`.`role_id` AS `role_id` from (`ezlinker`.`ez_role_permission` `a` left join `ezlinker`.`ez_role` `b` on((`a`.`role_id` = `b`.`id`))))) `c` left join `ezlinker`.`ez_permission` `d` on((`d`.`id` = `c`.`permission_id`))))) `e` left join `ezlinker`.`ez_user_role` `f` on((`f`.`role_id` = `e`.`role_id`)));

-- ----------------------------
-- View structure for ez_user_role_permission_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_user_role_permission_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `easylinker`@`%` SQL SECURITY DEFINER VIEW `ez_user_role_permission_view` AS select `f`.`user_id` AS `user_id`,`e`.`role_id` AS `role_id`,`e`.`permission_id` AS `permission_id`,`e`.`allow` AS `allow`,`e`.`resource` AS `resource`,`e`.`methods` AS `methods` from (((select `c`.`role_id` AS `role_id`,`c`.`permission_id` AS `permission_id`,`c`.`allow` AS `allow`,`d`.`resource` AS `resource`,`d`.`methods` AS `methods` from (((select `a`.`permission_id` AS `permission_id`,`a`.`allow` AS `allow`,`a`.`role_id` AS `role_id` from (`ezlinker`.`ez_role_permission` `a` left join `ezlinker`.`ez_role` `b` on((`a`.`role_id` = `b`.`id`))))) `c` left join `ezlinker`.`ez_permission` `d` on((`d`.`id` = `c`.`permission_id`))))) `e` left join `ezlinker`.`ez_user_role` `f` on((`f`.`role_id` = `e`.`role_id`)));

-- ----------------------------
-- View structure for ez_user_role_view
-- ----------------------------
DROP VIEW IF EXISTS `ez_user_role_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `easylinker`@`%` SQL SECURITY INVOKER VIEW `ez_user_role_view` AS select `d`.`id` AS `id`,`c`.`user_id` AS `user_id`,`d`.`label` AS `label`,`d`.`name` AS `name`,`d`.`parent` AS `parent`,`d`.`description` AS `description` from (((select `a`.`id` AS `id`,`a`.`user_id` AS `user_id`,`a`.`role_id` AS `role_id`,`a`.`record_version` AS `record_version`,`a`.`x` AS `x`,`a`.`create_time` AS `create_time` from (`ezlinker`.`ez_user_role` `a` left join `ezlinker`.`ez_user` `b` on((`a`.`user_id` = `b`.`id`))))) `c` left join `ezlinker`.`ez_role` `d` on((`d`.`id` = `c`.`role_id`)));

SET FOREIGN_KEY_CHECKS = 1;
