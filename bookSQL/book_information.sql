/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-05-01 21:37:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_information`
-- ----------------------------
DROP TABLE IF EXISTS `book_information`;
CREATE TABLE `book_information` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `book_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `user_id` int(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_information
-- ----------------------------
INSERT INTO `book_information` VALUES ('6', '10006', '生物', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('7', '10007', '数学', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('8', '10001', '语文', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('9', '10002', '数学', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('10', '10003', '英语', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('11', '10004', '物理', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('12', '10005', '化学', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('13', '10008', '高数', '1', '0', '21', '21', '图书馆');
INSERT INTO `book_information` VALUES ('14', '10009', '软件测试', '1', '0', '21', '21', '图书馆');

-- ----------------------------
-- Table structure for `friend`
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `friend_A_id` int(20) DEFAULT NULL,
  `friend_A_name` varchar(10) DEFAULT NULL,
  `friend_B_id` int(20) DEFAULT NULL,
  `friend_B_name` varchar(10) DEFAULT NULL,
  `allow` varchar(255) DEFAULT 'apply',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------

-- ----------------------------
-- Table structure for `notices_information`
-- ----------------------------
DROP TABLE IF EXISTS `notices_information`;
CREATE TABLE `notices_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `start_user_id` int(20) NOT NULL,
  `receive_user_id` int(20) NOT NULL,
  `student_phone` varchar(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notices_information
-- ----------------------------

-- ----------------------------
-- Table structure for `personal_information`
-- ----------------------------
DROP TABLE IF EXISTS `personal_information`;
CREATE TABLE `personal_information` (
  `student_id` int(20) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_information
-- ----------------------------
INSERT INTO `personal_information` VALUES ('1405020300', '张三', '13132323232');
INSERT INTO `personal_information` VALUES ('1405020301', '李四', '15811111111');
INSERT INTO `personal_information` VALUES ('1405020302', '王五', '11111111111');
INSERT INTO `personal_information` VALUES ('1405020303', '赵六', '11010101101');
INSERT INTO `personal_information` VALUES ('1405020304', '钱七', '15465865657');
INSERT INTO `personal_information` VALUES ('1405020305', '丽水', '15463254875');

-- ----------------------------
-- Table structure for `transaction_information`
-- ----------------------------
DROP TABLE IF EXISTS `transaction_information`;
CREATE TABLE `transaction_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `origin_user_id` int(20) NOT NULL,
  `receive_user_id` int(20) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transaction_information
-- ----------------------------
