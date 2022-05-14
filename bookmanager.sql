/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : bookmanager

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 12/05/2022 12:20:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (7, '论语', '孔子', '百花文艺', '111111');
INSERT INTO `book` VALUES (8, '诗经', '佚名', '十月文艺', '22222');
INSERT INTO `book` VALUES (9, '庄子', '庄周', '人民文学', '33333');
INSERT INTO `book` VALUES (10, '韩非子', '韩非', '中华书局', '44444');
INSERT INTO `book` VALUES (11, '三体', '刘慈欣', '机械工业', '666666');
INSERT INTO `book` VALUES (12, '边城', '沈从文', '武汉文学', '99999');
INSERT INTO `book` VALUES (14, '民主的细节', '刘瑜', '上海三联', '62563256');
INSERT INTO `book` VALUES (15, '从一到无穷大', '伽莫夫', '科学出版社', '95864125');
INSERT INTO `book` VALUES (16, '苏东坡传', '林语堂', '湖南女流', '9866412');
INSERT INTO `book` VALUES (17, '苏东坡传', '林语堂', '湖南女流', '555444455');

-- ----------------------------
-- Table structure for lend
-- ----------------------------
DROP TABLE IF EXISTS `lend`;
CREATE TABLE `lend`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lended` tinyint(1) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `date_lend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date_return` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lend
-- ----------------------------
INSERT INTO `lend` VALUES ('Atira', 0, 2, '20160304', '20180502');
INSERT INTO `lend` VALUES ('Conse', 1, 1, '20220506', '20220605');
INSERT INTO `lend` VALUES ('Nova', 0, 1, '19860504', '19990301');
INSERT INTO `lend` VALUES ('Shiira', 1, 2, '20220611', '20230612');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `sex` tinyint(1) NOT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(20) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', '张三', 22, 'b@163.com', '1234', 0);
INSERT INTO `user` VALUES (0, '2', '李四', 23, 'a@163.com', '1234', 0);
INSERT INTO `user` VALUES (0, '3', '王五', 24, 'c@163.com', '1234', 0);
INSERT INTO `user` VALUES (0, '4', '赵六', 25, 'd@163.com', '1234', 0);

SET FOREIGN_KEY_CHECKS = 1;
