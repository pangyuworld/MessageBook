/*
 Navicat Premium Data Transfer

 Source Server         : a
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : message

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 25/12/2018 14:37:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common
-- ----------------------------
DROP TABLE IF EXISTS `common`;
CREATE TABLE `common`  (
  `common_id` int(20) NOT NULL AUTO_INCREMENT,
  `common_time` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `common_content` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `common_to` int(20) NULL DEFAULT NULL,
  `common_from` int(20) NOT NULL,
  `message_id` int(20) NOT NULL,
  PRIMARY KEY (`common_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(20) NOT NULL AUTO_INCREMENT,
  `message_time` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `message_content` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密码\r\n',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `ss`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
