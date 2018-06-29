/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-05-01 21:38:12
*/

SET FOREIGN_KEY_CHECKS=0;

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
