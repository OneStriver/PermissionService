/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 127.0.0.1:3306
 Source Schema         : organizationservice

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 19/11/2018 09:45:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button`  (
  `id` int(10) NOT NULL,
  `roleId` int(10) NULL DEFAULT NULL,
  `menuId` int(10) NULL DEFAULT NULL,
  `buttonId` int(10) NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_buttondict
-- ----------------------------
DROP TABLE IF EXISTS `sys_buttondict`;
CREATE TABLE `sys_buttondict`  (
  `id` int(10) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_buttondict
-- ----------------------------
INSERT INTO `sys_buttondict` VALUES (1, '添加');
INSERT INTO `sys_buttondict` VALUES (2, '批量添加');
INSERT INTO `sys_buttondict` VALUES (3, '修改');
INSERT INTO `sys_buttondict` VALUES (4, '批量修改');
INSERT INTO `sys_buttondict` VALUES (5, '删除');
INSERT INTO `sys_buttondict` VALUES (6, '批量删除');
INSERT INTO `sys_buttondict` VALUES (7, '导入Excel');
INSERT INTO `sys_buttondict` VALUES (8, '导出Excel');

-- ----------------------------
-- Table structure for sys_fhlog
-- ----------------------------
DROP TABLE IF EXISTS `sys_fhlog`;
CREATE TABLE `sys_fhlog`  (
  `FHLOG_ID` int(10) NOT NULL,
  `USERNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `CZTIME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件',
  PRIMARY KEY (`FHLOG_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_fhlog
-- ----------------------------
INSERT INTO `sys_fhlog` VALUES (1, 'admin', '2018-11-14 17:00:01', '登录系统');
INSERT INTO `sys_fhlog` VALUES (2, 'admin', '2018-11-14 17:05:50', '登录系统');
INSERT INTO `sys_fhlog` VALUES (3, 'admin', '2018-11-15 17:02:07', '登录系统');
INSERT INTO `sys_fhlog` VALUES (4, 'admin', '2018-11-15 17:11:37', '退出');
INSERT INTO `sys_fhlog` VALUES (5, 'admin', '2018-11-15 17:11:49', '登录系统');
INSERT INTO `sys_fhlog` VALUES (6, 'admin', '2018-11-15 17:27:26', '退出');
INSERT INTO `sys_fhlog` VALUES (7, 'admin', '2018-11-15 17:27:33', '登录系统');
INSERT INTO `sys_fhlog` VALUES (8, 'admin', '2018-11-15 17:27:51', '退出');
INSERT INTO `sys_fhlog` VALUES (9, 'admin', '2018-11-15 17:27:56', '登录系统');
INSERT INTO `sys_fhlog` VALUES (10, 'admin', '2018-11-15 17:28:21', '退出');
INSERT INTO `sys_fhlog` VALUES (11, '1', '2018-11-15 17:28:24', '登录系统');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `MENU_ID` int(10) NOT NULL,
  `MENU_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_ID` int(10) NULL DEFAULT NULL,
  `MENU_ORDER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_ICON` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_STATE` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '#', 0, '1', 'layui-icon layui-icon-set-fill', '2', 1);
INSERT INTO `sys_menu` VALUES (2, '用户管理', 'user/tolistUsersPage.do', 1, '1', '', '1', 1);
INSERT INTO `sys_menu` VALUES (3, '角色权限管理', 'role/toRoleListPage.do', 1, '2', '', '1', 1);
INSERT INTO `sys_menu` VALUES (4, '菜单管理', 'menu/listAllMenu.do', 1, '3', '', '1', 1);
INSERT INTO `sys_menu` VALUES (5, '菜单按钮管理', 'buttonRight/toButtonRightPage.do', 1, '4', '', '1', 1);
INSERT INTO `sys_menu` VALUES (6, '日志管理', 'fhlog/toListLogsPage.do', 1, '5', '', '1', 1);
INSERT INTO `sys_menu` VALUES (7, '组织管理', '#', 0, '2', 'layui-icon layui-icon-align-right', '2', 1);
INSERT INTO `sys_menu` VALUES (8, '单位管理', 'unitManagement/listUnitInfoAndSubUnitInfo.do', 7, '1', '', '1', 1);
INSERT INTO `sys_menu` VALUES (9, '人员管理', 'personalManagement/listUnitInfoAndSubUnitInfo.do', 7, '2', '', '1', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ROLE_ID` int(10) NOT NULL,
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RIGHTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_ID` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '1022', 0);
INSERT INTO `sys_role` VALUES (2, '普通管理员', '1022', 0);
INSERT INTO `sys_role` VALUES (3, '一级管理员', '1022', 2);
INSERT INTO `sys_role` VALUES (4, '二级管理员', '958', 2);
INSERT INTO `sys_role` VALUES (5, '三级管理员', '896', 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `USER_ID` int(10) NOT NULL,
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `NICKNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `ROLE_ID` int(10) NULL DEFAULT NULL COMMENT '角色',
  `LAST_LOGIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆时间',
  `IP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `STATUS` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `BZ` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '超级管理员', 'dd94709528bb1c83d08f3088d4043f4742891f4f', 1, '2018-11-15 17:27:33', '127.0.0.1', '0', '超级管理员');
INSERT INTO `sys_user` VALUES (2, 'admin', '普通管理员', 'e496507bafcbfa9673625c9bde37ecce799ee758', 2, '2018-11-15 17:27:56', '127.0.0.1', '0', '普通管理员');
INSERT INTO `sys_user` VALUES (3, '1', '1', '17ba0791499db908433b80f37c5fbc89b870084b', 3, '2018-11-15 17:28:24', '127.0.0.1', '0', '1');

-- ----------------------------
-- Table structure for unit_management
-- ----------------------------
DROP TABLE IF EXISTS `unit_management`;
CREATE TABLE `unit_management`  (
  `unit_id` int(10) NOT NULL,
  `unit_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit_parentid` int(10) NULL DEFAULT NULL,
  `unit_order` int(10) NULL DEFAULT NULL,
  `unit_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_management
-- ----------------------------
INSERT INTO `unit_management` VALUES (1, '部队列表', 0, 1, NULL);
INSERT INTO `unit_management` VALUES (2, '第一军', 1, 1, NULL);
INSERT INTO `unit_management` VALUES (3, '第二军', 1, 2, NULL);
INSERT INTO `unit_management` VALUES (4, '第三军', 1, 3, NULL);
INSERT INTO `unit_management` VALUES (5, '第一师', 2, 1, NULL);
INSERT INTO `unit_management` VALUES (6, '第二师', 2, 2, NULL);
INSERT INTO `unit_management` VALUES (7, '第三师', 2, 3, NULL);
INSERT INTO `unit_management` VALUES (8, '第四师', 3, 1, NULL);
INSERT INTO `unit_management` VALUES (9, '第五师', 3, 2, NULL);
INSERT INTO `unit_management` VALUES (10, '第六师', 4, 1, NULL);
INSERT INTO `unit_management` VALUES (11, '第七师', 4, 2, NULL);
INSERT INTO `unit_management` VALUES (12, '第一团', 5, 1, NULL);
INSERT INTO `unit_management` VALUES (13, '第二团', 6, 1, NULL);
INSERT INTO `unit_management` VALUES (14, '第一连', 12, 1, NULL);
INSERT INTO `unit_management` VALUES (15, '第二连', 13, 1, NULL);
INSERT INTO `unit_management` VALUES (16, '第一排', 14, 1, NULL);
INSERT INTO `unit_management` VALUES (17, '第二排', 15, 1, NULL);
INSERT INTO `unit_management` VALUES (18, '第一旅', 16, 1, NULL);
INSERT INTO `unit_management` VALUES (19, '第一班', 18, 1, NULL);
INSERT INTO `unit_management` VALUES (20, '第四军', 1, 4, NULL);
INSERT INTO `unit_management` VALUES (21, '第十八师', 20, 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
