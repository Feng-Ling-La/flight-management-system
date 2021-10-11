/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : flight

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 11/10/2021 16:29:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '1');

-- ----------------------------
-- Table structure for f_order
-- ----------------------------
DROP TABLE IF EXISTS `f_order`;
CREATE TABLE `f_order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT,
  `id` int(0) NULL DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_id` int(0) NULL DEFAULT NULL,
  `f_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `f_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_price` int(0) NULL DEFAULT NULL,
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk_1`(`id`) USING BTREE,
  INDEX `fk_2`(`f_id`) USING BTREE,
  CONSTRAINT `fk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_2` FOREIGN KEY (`f_id`) REFERENCES `flight` (`f_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_order
-- ----------------------------
INSERT INTO `f_order` VALUES (115, 12, '何信煌', 23, 'flight4', '2021-07-17 11:43:00', '头等舱', 900, '1111111122', '11223444');
INSERT INTO `f_order` VALUES (116, 12, '何信煌', 23, 'flight4', '2021-05-16 22:55:00', '头等舱', 2666, '15179116947', '11111124444444');
INSERT INTO `f_order` VALUES (118, 12, '何信煌', 27, 'flight333', '2021-07-17 22:04:00', '头等舱', 1500, '1111111122', '11111111111112');
INSERT INTO `f_order` VALUES (119, 12, '何信煌', 27, 'flight333', '2021-07-17 22:05:00', '头等舱', 3000, '233243', '11654424444444');
INSERT INTO `f_order` VALUES (123, 20, 'test1', 30, 'flight666', '2021-05-11 13:09:22', '头等舱', 2000, '1222222222', '1111222223333311');
INSERT INTO `f_order` VALUES (124, 20, 'test1', 22, 'flight4', '2021-05-11 13:13:24', '商务舱', 1500, '11111111', '1122');
INSERT INTO `f_order` VALUES (127, 20, 'test1', 21, 'flight77', '2021-05-11 13:22:00', '商务舱', 1300, '1222222222', '11112222233333');
INSERT INTO `f_order` VALUES (129, 20, 'test1', 21, 'flight77', '2021-05-11 13:23:10', '商务舱', 1300, '1122', '1122');
INSERT INTO `f_order` VALUES (131, 20, 'test1', 26, 'flight999', '2021-05-11 13:25:56', '商务舱', 1500, '1234', '1122');
INSERT INTO `f_order` VALUES (141, 20, 'test1', 27, 'flight333', '2021-05-11 13:33:56', '商务舱', 1500, '1222222222', '1111222223333311');
INSERT INTO `f_order` VALUES (142, 12, '何信煌', 23, 'flight4', '2021-05-11 14:42:19', '商务舱', 900, '', '');
INSERT INTO `f_order` VALUES (143, 12, '何信煌', 21, 'flight77', '2021-05-11 17:21:17', '商务舱', 1300, '1222222222', '1111222223333311');
INSERT INTO `f_order` VALUES (146, 12, '何信煌', 30, 'flight666', '2021-05-11 17:27:09', '商务舱', 1000, '1222222222', '11112222233333');
INSERT INTO `f_order` VALUES (147, 12, '何信煌', 30, 'flight666', '2021-05-11 19:31:16', '头等舱', 2000, '11111111', '1122');
INSERT INTO `f_order` VALUES (148, 12, '何信煌', 21, 'flight77', '2021-05-11 19:33:22', '商务舱', 1300, '1222222222', '11112222233333');
INSERT INTO `f_order` VALUES (149, 12, '何信煌', 21, 'flight77', '2021-05-12 21:15:08', '商务舱', 1300, '12345', '3601211111');
INSERT INTO `f_order` VALUES (150, 12, '何信煌', 21, 'flight77', '2021-07-17 10:09:00', '商务舱', 500, '12345', '3601211111');
INSERT INTO `f_order` VALUES (151, 12, '何信煌', 21, 'flight77', '2021-05-12 22:57:10', '头等舱', 2600, '1222222222', '1111222223333311');
INSERT INTO `f_order` VALUES (152, 12, '何信煌', 21, 'flight77', '2021-07-17 10:09:00', '商务舱', 2600, '1222222222', '1111222223333311');
INSERT INTO `f_order` VALUES (153, 12, '何信煌', 21, 'flight77', '2021-05-15 23:18:45', '经济舱', 500, '1222222222', '1111222223333311');
INSERT INTO `f_order` VALUES (154, 20, 'test1', 34, 'flight123', '2021-05-16 11:26:59', '商务舱', 1500, '15122222', '1111234422112121');
INSERT INTO `f_order` VALUES (155, 20, 'test1', 33, 'flight1111', '2021-05-16 22:48:37', '商务舱', 1500, '15122222', '1111234422112121');
INSERT INTO `f_order` VALUES (157, 20, 'test1', 27, 'flight333', '2021-05-17 17:36:31', '头等舱', 3000, '', '');
INSERT INTO `f_order` VALUES (158, 20, 'test1', 27, 'flight333', '2021-05-17 17:36:39', '头等舱', 3000, '', '');
INSERT INTO `f_order` VALUES (159, 20, 'test1', 21, 'flight77', '2021-05-18 22:50:13', '经济舱', 500, '122222222211', '11112222233333');

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
  `f_id` int(0) NOT NULL AUTO_INCREMENT,
  `f_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_start` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_end` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_dt` datetime(0) NULL DEFAULT NULL,
  `f_at` datetime(0) NULL DEFAULT NULL,
  `f_t` int(0) NULL DEFAULT NULL,
  `f_s` int(0) NULL DEFAULT NULL,
  `f_j` int(0) NULL DEFAULT NULL,
  `f_t_n` int(0) NULL DEFAULT NULL,
  `f_s_n` int(0) NULL DEFAULT NULL,
  `f_j_n` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES (21, 'flight77', '重庆', '天津', '2021-07-06 14:06:00', '2021-07-06 16:06:00', 2600, 1300, 500, 1, 0, 36);
INSERT INTO `flight` VALUES (22, 'flight4', '重庆', '天津', '2021-06-06 09:30:00', '2021-06-06 11:30:00', 2000, 1500, 500, 4, 19, 100);
INSERT INTO `flight` VALUES (23, 'flight4', '深圳', '杭州', '2021-05-16 07:30:00', '2021-05-16 09:30:00', 2666, 900, 400, 3, 18, 100);
INSERT INTO `flight` VALUES (25, 'flight9', '昆明', '西安', '2021-04-16 07:30:00', '2021-04-16 10:50:00', 2666, 1500, 400, 4, 10, 140);
INSERT INTO `flight` VALUES (26, 'flight999', '郑州', '深圳', '2021-05-01 18:03:00', '2021-05-01 20:04:00', 2600, 1500, 500, 4, 19, 100);
INSERT INTO `flight` VALUES (27, 'flight333', '重庆', '吉林', '2021-05-10 18:16:00', '2021-05-10 20:16:00', 3000, 1500, 300, 1, 8, 80);
INSERT INTO `flight` VALUES (28, 'flight666', '南昌', '深圳', '2021-05-21 11:28:00', '2021-05-21 14:28:00', 3000, 1300, 500, 2, 19, 100);
INSERT INTO `flight` VALUES (30, 'flight666', '重庆', '天津', '2021-05-30 17:24:41', '2021-05-30 19:24:47', 2000, 1000, 400, 4, 5, 108);
INSERT INTO `flight` VALUES (31, 'flight456', '青岛', '上海', '2021-07-12 22:44:00', '2021-07-13 00:44:00', 3000, 1500, 500, 10, 20, 80);
INSERT INTO `flight` VALUES (32, 'flight333', '青岛', '上海', '2021-07-15 01:46:00', '2021-07-12 03:35:00', 2600, 1300, 400, 4, 10, 50);
INSERT INTO `flight` VALUES (33, 'flight1111', '广州', '郑州', '2021-07-24 04:47:00', '2021-07-24 06:53:00', 3300, 1500, 600, 4, 19, 100);
INSERT INTO `flight` VALUES (34, 'flight123', '广州', '郑州', '2021-07-16 12:48:00', '2021-07-16 14:53:00', 3000, 1500, 400, 10, 19, 100);
INSERT INTO `flight` VALUES (35, 'flight888', '重庆', '天津', '2021-05-16 21:56:00', '2021-05-16 23:56:00', 2600, 1500, 500, 4, 20, 100);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identityNum` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (12, '何信煌', '123', '1111234444567', '12345', 'shashigay@qq.com', '何信煌');
INSERT INTO `user` VALUES (20, 'test1', '123', '1111234422112121', '15122222', 'test@qq.com', '宇智波佐助');
INSERT INTO `user` VALUES (23, '小红', '123', '112', '123', 'xiaohong@qq.com', '11');

SET FOREIGN_KEY_CHECKS = 1;
