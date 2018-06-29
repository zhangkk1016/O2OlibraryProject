/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-05-01 21:37:53
*/

SET FOREIGN_KEY_CHECKS=0;

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
