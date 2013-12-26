/*
Navicat MySQL Data Transfer

Source Server         : huangyunan
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : billiards

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2011-06-07 20:24:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `billiards`
-- ----------------------------
DROP TABLE IF EXISTS `billiards`;
CREATE TABLE `billiards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start` varchar(500) DEFAULT NULL,
  `end` varchar(500) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of billiards
-- ----------------------------
