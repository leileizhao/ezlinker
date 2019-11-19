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

 Date: 19/11/2019 22:14:20
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备上面的模块，和设备是多对一关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ez_component
-- ----------------------------
INSERT INTO `ez_component` VALUES (1, 1, 1, '46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d', '52f06890e35873e4ed3d770fce0f3f0083901f2b', 'd0b48e14df2d9a35def6e292f7050a1f9557776b', 1, 0, 'ESP32', '12F', 'iot', '356422966724845568', 'GVB0FUjjPvT2YsqaksUxWox5eZTf4dqxmkUXH0wRPzZWGO+l9XgHEEujqM/gm90QVXqivGdmaOpwosqk3uyBT9bN2WwfEUQQm+enFsOaNFBZob4vuhAoFDTQjQpLoaivkW2XvW8wtIsCxk+DJ7EECsOLdwPFhHZnVqA5hL59ZGk=', 0, '[{\"name\": \"temp\", \"label\": \"温度\"}, {\"name\": \"hum\", \"label\": \"湿度\"}]', 'ESP32通用模块', 0, 0, '2019-11-18 23:04:28');
INSERT INTO `ez_component` VALUES (2, 1, 1, '6d6912387a642f2326d47804644706b0428d5713', 'd681e771dedeb70c6c5fb1571fed4118b469ad8d', '08d1e66a347ac941d8a5e2f222006af6f1234bea', 1, 0, 'ESP32', '12F', 'iot', '356423295801548800', 'ZTPBCYpkRjaSzkUevAOUNnuhwn3L1E2GGhGBCMb6tV7iHhzhbUjPzbkxwOcnsi9yxbTw2iz+gP/MjS1ylaCJvpJ+Hs+5DV8tRpSFiQ1AuE+Nvj1X/Lr3ptBrnr9ilnbunQdatMSXyY5dwirErbbrEvEKOqy7hNV0fIJZos78sfU=', 0, '[{\"name\": \"temp\", \"label\": \"温度\"}, {\"name\": \"hum\", \"label\": \"湿度\"}]', 'ESP32通用模块', 0, 0, '2019-11-18 23:05:46');
INSERT INTO `ez_component` VALUES (3, 1, 1, '157f44d23dbf9d42b3797287338e4f46631dc95d', '72961e888fcf1364a85d280a479bc469ca5bb58c', 'dcabe07c5c7fcb39caacd3e8c6bd5592a248c783', 1, 0, 'ESP32', '12F', 'iot', '356670700788092928', 'AWb4K54RQzWeH7dH/TAvi5VVT3TBfNqBIfPb8g3m4HNIKeh2QbU8NreAgfczO19YWJo4pEbuxeLGyS1hag2cUjC8Ya+911cdeCXojRf4HSvz5OPxnL/ABUmrc3om1+7cxkKN+Wnyb4iGkdVheHkdnlNy6xlF0nc5k6EtxxpJvMY=', 0, '[{\"field\": \"temp\", \"label\": \"温度\"}, {\"field\": \"hum\", \"label\": \"湿度\"}]', 'ESP32通用模块', 0, 0, '2019-11-19 15:30:33');

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
-- Records of ez_feature
-- ----------------------------
INSERT INTO `ez_feature` VALUES (4, 1, '家里的排插2', 'SW2', NULL, 'state', '{\"state\": [1, 2, 3, 4, 5, 6, 7, 8, 9]}', 0, 0000, '2019-11-18 14:08:34');
INSERT INTO `ez_feature` VALUES (5, 1, '家里的排插【update】', 'SW1', NULL, 'state', '{\"state\": 1}', 0, 0000, '2019-11-18 14:08:34');
INSERT INTO `ez_feature` VALUES (6, 1, '开关111', 'SW1', NULL, 'k', '{\"state\": [1]}', 0, 0000, '2019-11-18 14:28:21');
INSERT INTO `ez_feature` VALUES (7, 1, '开关111', 'SW1', NULL, 'k', '{\"state\": [1]}', 0, 0000, '2019-11-18 14:29:04');

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
-- Records of ez_internal_message
-- ----------------------------
INSERT INTO `ez_internal_message` VALUES (1, 1, 1, 0, '欢迎进入EZLinker', '欢迎新用户', 0, 0, '2019-11-15 16:46:32');
INSERT INTO `ez_internal_message` VALUES (2, 1, 1, 0, '你中了100000W', '你中奖了', 0, 0, '2019-11-15 16:48:48');

-- ----------------------------
-- Table structure for ez_mqtt_topic
-- ----------------------------
DROP TABLE IF EXISTS `ez_mqtt_topic`;
CREATE TABLE `ez_mqtt_topic`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `allow` int(4) NULL DEFAULT 1 COMMENT '是否允许连接: 0=拒绝1=允许',
  `access` int(4) NULL DEFAULT 1 COMMENT '行为类型: 1=订阅2=发布3=订阅+发布',
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MQTT客户端ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MQTT用户名',
  `topic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `record_version` int(11) NOT NULL DEFAULT 0 COMMENT '记录版本',
  `x` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'MQTT的TOPIC记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ez_mqtt_topic
-- ----------------------------
INSERT INTO `ez_mqtt_topic` VALUES (1, 1, 1, '0.0.0.0', '46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d', '52f06890e35873e4ed3d770fce0f3f0083901f2b', 'mqtt/out/46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d/s2c', '服务端指令接收入口', 0, 0, '2019-11-18 23:04:28');
INSERT INTO `ez_mqtt_topic` VALUES (2, 1, 1, '0.0.0.0', '46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d', '52f06890e35873e4ed3d770fce0f3f0083901f2b', 'mqtt/in/46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d/c2s', '服务端数据入口', 0, 0, '2019-11-18 23:04:28');
INSERT INTO `ez_mqtt_topic` VALUES (3, 1, 1, '0.0.0.0', '46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d', '52f06890e35873e4ed3d770fce0f3f0083901f2b', 'mqtt/in/46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d/status', '状态上报入口', 0, 0, '2019-11-18 23:04:28');
INSERT INTO `ez_mqtt_topic` VALUES (4, 1, 1, '0.0.0.0', '46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d', '52f06890e35873e4ed3d770fce0f3f0083901f2b', 'mqtt/out/c4ca4238a0b923820dcc509a6f75849b46f26ab0c245e25ea44fd128d1b8d8ae5abc9d9d/group', '分组', 0, 0, '2019-11-18 23:04:28');
INSERT INTO `ez_mqtt_topic` VALUES (5, 1, 1, '0.0.0.0', '6d6912387a642f2326d47804644706b0428d5713', 'd681e771dedeb70c6c5fb1571fed4118b469ad8d', 'mqtt/out/6d6912387a642f2326d47804644706b0428d5713/s2c', NULL, 0, 0, '2019-11-18 23:05:46');
INSERT INTO `ez_mqtt_topic` VALUES (6, 1, 1, '0.0.0.0', '6d6912387a642f2326d47804644706b0428d5713', 'd681e771dedeb70c6c5fb1571fed4118b469ad8d', 'mqtt/in/6d6912387a642f2326d47804644706b0428d5713/c2s', NULL, 0, 0, '2019-11-18 23:05:46');
INSERT INTO `ez_mqtt_topic` VALUES (7, 1, 1, '0.0.0.0', '6d6912387a642f2326d47804644706b0428d5713', 'd681e771dedeb70c6c5fb1571fed4118b469ad8d', 'mqtt/in/6d6912387a642f2326d47804644706b0428d5713/status', NULL, 0, 0, '2019-11-18 23:05:46');
INSERT INTO `ez_mqtt_topic` VALUES (8, 1, 1, '0.0.0.0', '6d6912387a642f2326d47804644706b0428d5713', 'd681e771dedeb70c6c5fb1571fed4118b469ad8d', 'mqtt/out/c4ca4238a0b923820dcc509a6f75849b6d6912387a642f2326d47804644706b0428d5713/group', NULL, 0, 0, '2019-11-18 23:05:46');
INSERT INTO `ez_mqtt_topic` VALUES (9, 1, 1, '0.0.0.0', '54a9b218f551393119d68e64287f306d37dd29ee', '3199d49f7011bf2a2b437441b5036dc1baab1e8e', 'mqtt/out/54a9b218f551393119d68e64287f306d37dd29ee/s2c', '服务端消息入口', 0, 0, '2019-11-19 15:25:08');
INSERT INTO `ez_mqtt_topic` VALUES (10, 1, 1, '0.0.0.0', '157f44d23dbf9d42b3797287338e4f46631dc95d', '72961e888fcf1364a85d280a479bc469ca5bb58c', 'mqtt/out/157f44d23dbf9d42b3797287338e4f46631dc95d/s2c', '服务端消息入口', 0, 0, '2019-11-19 15:30:25');
INSERT INTO `ez_mqtt_topic` VALUES (11, 1, 1, '0.0.0.0', '157f44d23dbf9d42b3797287338e4f46631dc95d', '72961e888fcf1364a85d280a479bc469ca5bb58c', 'mqtt/in/157f44d23dbf9d42b3797287338e4f46631dc95d/c2s', '服务端消息出口', 0, 0, '2019-11-19 15:30:28');
INSERT INTO `ez_mqtt_topic` VALUES (12, 1, 1, '0.0.0.0', '157f44d23dbf9d42b3797287338e4f46631dc95d', NULL, 'mqtt/in/157f44d23dbf9d42b3797287338e4f46631dc95d/status', '状态上报入口', 0, 0, '2019-11-19 15:30:30');
INSERT INTO `ez_mqtt_topic` VALUES (13, 1, 1, '0.0.0.0', '157f44d23dbf9d42b3797287338e4f46631dc95d', '72961e888fcf1364a85d280a479bc469ca5bb58c', 'mqtt/out/c4ca4238a0b923820dcc509a6f75849b157f44d23dbf9d42b3797287338e4f46631dc95d/group', '分组接收消息入口', 0, 0, '2019-11-19 15:30:33');

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
-- Records of ez_permission
-- ----------------------------
INSERT INTO `ez_permission` VALUES (1, 0, 2, 1, '首页', 'INDEX', '/home', '[GET,POST]', 0, '系统首页', 0, 0, '2019-11-11 22:30:48');
INSERT INTO `ez_permission` VALUES (2, 0, 2, 1, '产品管理', 'PRODUCE_LIST', '/products', '[GET]', 0, '产品列表', 0, 0, '2019-11-11 22:30:58');
INSERT INTO `ez_permission` VALUES (3, 0, 2, 1, '项目管理', 'PROJECT_LIST', '/projects', '[POST]', 0, '项目列表', 0, 0, '2019-11-12 18:00:23');
INSERT INTO `ez_permission` VALUES (4, 0, 2, 1, '获取菜单', 'MENU_LIST', '/users/menu', '[GET]', 0, '获取菜单', 0, 0, '2019-11-12 18:01:39');
INSERT INTO `ez_permission` VALUES (5, 0, 2, 1, '个人信息', 'PERSONAL_INFO', '/users/userInfo', '[GET]', 0, '个人信息', 0, 0, '2019-11-12 22:17:21');
INSERT INTO `ez_permission` VALUES (6, 0, 2, 1, '系统参数', 'SYSTEM_PEOPERTIES', '/system/properties', '[GET]', 0, '系统参数', 0, 0, '2019-11-13 10:25:56');
INSERT INTO `ez_permission` VALUES (7, 0, 2, 1, '运行时状态', 'SYSTEM_RUNNING', '/system/running', '[GET]', 0, '运行时状态', 0, 0, '2019-11-13 10:26:13');
INSERT INTO `ez_permission` VALUES (8, 0, 2, 1, '登录日志', 'LOGIN_LOG_LIST', '/user/loginLog', '[GET]', 0, '登录日志', 0, 0, '2019-11-13 23:27:44');
INSERT INTO `ez_permission` VALUES (9, 0, 2, 1, '查看字典项列表', 'DICT_KEY_LIST', '/dictionaries/keys', '[GET]', 0, '查看字典项列表', 0, 0, '2019-11-14 21:27:29');
INSERT INTO `ez_permission` VALUES (10, 0, 2, 1, '查看字典值列表', 'DICT_VALUE_LIST', '/dictionaries/values', '[GET]', 0, '查看字典值列表', 0, 0, '2019-11-14 21:42:43');

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
-- Records of ez_product
-- ----------------------------
INSERT INTO `ez_product` VALUES (1, 1, 'IphoneXXX', 'xxx.png', 'XXX', '1', '{\"name\": \"状态\", \"field\": \"state\"}', '白色的IphoneXXX11222', 11, 0, '2019-11-14 10:52:40');
INSERT INTO `ez_product` VALUES (2, 1, 'IphoneXXX2', 'xxx.png', 'XXX', '1', '{\"name\": \"状态\", \"field\": \"state\"}', '白色的IphoneXXX', 0, 0, '2019-11-14 10:55:54');
INSERT INTO `ez_product` VALUES (3, 1, 'IphoneXXX3', 'xxx.png', 'XXX', '1', '{\"name\": \"状态\", \"field\": \"state\"}', '白色的IphoneXXX', 0, 0, '2019-11-14 10:57:04');
INSERT INTO `ez_product` VALUES (4, 1, 'IphoneXXX', 'xxx.png', 'XXX', '1', '{\"name\": \"状态\", \"field\": \"state\"}', '白色的IphoneXXX', 0, 0, '2019-11-18 14:30:00');

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
-- Records of ez_project
-- ----------------------------
INSERT INTO `ez_project` VALUES (2, '家庭物联网更新名称', '1111111.jpg', 1, '家庭物联网项目', '福建省福州市', 2, 0, '2019-11-11 15:32:15');
INSERT INTO `ez_project` VALUES (3, '家庭物联网', 'a.jpg', 1, '家庭物联网项目', '福建省福州市', 0, 0, '2019-11-13 14:57:21');

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
-- Records of ez_role
-- ----------------------------
INSERT INTO `ez_role` VALUES (1, '系统管理员', 'SYS_ADMIN', 00000000000, '系统最高权限管理员', 0, 0, '2019-11-11 22:28:45');
INSERT INTO `ez_role` VALUES (2, '普通用户', 'USER', 00000000000, '系统普通用户', 0, 0, '2019-11-11 22:29:36');

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
-- Records of ez_role_permission
-- ----------------------------
INSERT INTO `ez_role_permission` VALUES (1, 1, '[POST,GET]', 1, 0, 0, '2019-11-11 22:33:10');
INSERT INTO `ez_role_permission` VALUES (2, 1, '[ALL]', 2, 0, 0, '2019-11-11 22:33:30');
INSERT INTO `ez_role_permission` VALUES (3, 1, '[ALL]', 3, 0, 0, '2019-11-12 18:03:27');
INSERT INTO `ez_role_permission` VALUES (4, 1, '[ALL]', 4, 0, 0, '2019-11-12 18:03:34');
INSERT INTO `ez_role_permission` VALUES (5, 1, '[ALL]', 5, 0, 0, '2019-11-12 22:16:33');
INSERT INTO `ez_role_permission` VALUES (6, 1, '[ALL]', 6, 0, 0, '2019-11-13 10:24:33');
INSERT INTO `ez_role_permission` VALUES (7, 1, '[ALL]', 7, 0, 0, '2019-11-13 10:24:39');
INSERT INTO `ez_role_permission` VALUES (8, 1, '[ALL]', 8, 0, 0, '2019-11-13 23:28:21');
INSERT INTO `ez_role_permission` VALUES (9, 1, '[ALL]', 9, 0, 0, '2019-11-14 21:27:51');
INSERT INTO `ez_role_permission` VALUES (10, 1, '[ALL]', 10, 0, 0, '2019-11-14 21:41:44');

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
-- Records of ez_user
-- ----------------------------
INSERT INTO `ez_user` VALUES (1, 'ezlinker', '5F4DCC3B5AA765D61D8327DEB882CF99', 'ezlinker.png', '18059150204', '123@qq.com', '张三', 'BigDick', 1, 1, 0, NULL, NULL, 0, 2, '2019-11-11 22:23:02');

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
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ez_user_login_log
-- ----------------------------
INSERT INTO `ez_user_login_log` VALUES (1, 0, 'WARNING', '10.168.1.194', '未知用户尝试登陆失败', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-12 10:27:58');
INSERT INTO `ez_user_login_log` VALUES (2, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-12 10:28:33');
INSERT INTO `ez_user_login_log` VALUES (3, 1, 'INFO', '0:0:0:0:0:0:0:1', '登陆成功', '未知', 0, 0, '2019-11-12 10:33:01');
INSERT INTO `ez_user_login_log` VALUES (4, 1, 'INFO', '0:0:0:0:0:0:0:1', '登陆成功', '未知', 0, 0, '2019-11-12 10:38:49');
INSERT INTO `ez_user_login_log` VALUES (5, 1, 'INFO', '0:0:0:0:0:0:0:1', '登陆成功', '未知', 0, 0, '2019-11-12 10:41:13');
INSERT INTO `ez_user_login_log` VALUES (6, 1, 'INFO', '192.168.240.1', '登陆成功', '内网登陆,IP:192.168.240.1', 0, 0, '2019-11-12 10:44:30');
INSERT INTO `ez_user_login_log` VALUES (7, 1, 'INFO', '192.168.240.1', '登陆成功', '内网登陆,IP:192.168.240.1', 0, 0, '2019-11-12 10:45:57');
INSERT INTO `ez_user_login_log` VALUES (8, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-12 11:22:24');
INSERT INTO `ez_user_login_log` VALUES (9, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-12 17:26:43');
INSERT INTO `ez_user_login_log` VALUES (10, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-12 22:14:05');
INSERT INTO `ez_user_login_log` VALUES (11, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-12 22:17:28');
INSERT INTO `ez_user_login_log` VALUES (12, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-13 10:20:37');
INSERT INTO `ez_user_login_log` VALUES (13, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 10:28:15');
INSERT INTO `ez_user_login_log` VALUES (14, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 10:58:25');
INSERT INTO `ez_user_login_log` VALUES (15, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 11:01:17');
INSERT INTO `ez_user_login_log` VALUES (16, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 11:12:35');
INSERT INTO `ez_user_login_log` VALUES (17, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 11:37:23');
INSERT INTO `ez_user_login_log` VALUES (18, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 14:54:48');
INSERT INTO `ez_user_login_log` VALUES (19, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 14:56:11');
INSERT INTO `ez_user_login_log` VALUES (20, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-13 14:57:19');
INSERT INTO `ez_user_login_log` VALUES (21, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-13 16:21:55');
INSERT INTO `ez_user_login_log` VALUES (22, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-13 16:24:52');
INSERT INTO `ez_user_login_log` VALUES (23, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-13 16:27:18');
INSERT INTO `ez_user_login_log` VALUES (24, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-13 16:29:43');
INSERT INTO `ez_user_login_log` VALUES (25, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-13 16:51:42');
INSERT INTO `ez_user_login_log` VALUES (26, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-13 23:13:49');
INSERT INTO `ez_user_login_log` VALUES (27, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-13 23:28:04');
INSERT INTO `ez_user_login_log` VALUES (28, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-13 23:28:25');
INSERT INTO `ez_user_login_log` VALUES (29, 1, 'INFO', '192.168.5.22', '登陆成功', '内网登陆,IP:192.168.5.22', 0, 0, '2019-11-14 11:19:48');
INSERT INTO `ez_user_login_log` VALUES (30, 1, 'INFO', '127.0.0.1', '登陆成功', 'IP:null,国家:null,地区:null,运营商:null', 0, 0, '2019-11-14 14:06:38');
INSERT INTO `ez_user_login_log` VALUES (31, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-14 21:29:08');
INSERT INTO `ez_user_login_log` VALUES (32, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-14 21:44:02');
INSERT INTO `ez_user_login_log` VALUES (33, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-14 21:44:28');
INSERT INTO `ez_user_login_log` VALUES (34, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-14 21:46:16');
INSERT INTO `ez_user_login_log` VALUES (35, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-15 14:04:53');
INSERT INTO `ez_user_login_log` VALUES (36, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-15 14:04:56');
INSERT INTO `ez_user_login_log` VALUES (37, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-15 20:35:01');
INSERT INTO `ez_user_login_log` VALUES (38, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-17 22:14:53');
INSERT INTO `ez_user_login_log` VALUES (39, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-17 22:25:34');
INSERT INTO `ez_user_login_log` VALUES (40, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-17 22:31:36');
INSERT INTO `ez_user_login_log` VALUES (41, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-18 11:42:57');
INSERT INTO `ez_user_login_log` VALUES (42, 1, 'INFO', '192.168.116.1', '登陆成功', '内网登陆,IP:192.168.116.1', 0, 0, '2019-11-18 20:09:25');
INSERT INTO `ez_user_login_log` VALUES (43, 1, 'INFO', '10.168.1.194', '登陆成功', '内网登陆,IP:10.168.1.194', 0, 0, '2019-11-19 15:09:36');

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
-- Records of ez_user_role
-- ----------------------------
INSERT INTO `ez_user_role` VALUES (1, 1, 1, 0, 0, '2019-11-11 22:32:17');
INSERT INTO `ez_user_role` VALUES (2, 1, 2, 0, 0, '2019-11-11 22:32:29');

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
