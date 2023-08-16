/*
 Navicat Premium Data Transfer

 Source Server         : laoayu
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : parking

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 13/05/2023 11:17:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info`  (
  `car_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `belong_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属于哪个车主的姓名',
  `belong_num` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车主手机号',
  `plate_color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌颜色',
  `plate_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '到期日期',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '固定车信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_info
-- ----------------------------
INSERT INTO `car_info` VALUES (1, '张三', '13812345678', '蓝色', '川C28888', '2023-05-09 12:41:33', '2023-06-09 12:41:36', '0');
INSERT INTO `car_info` VALUES (2, '李四', '13923456789', '蓝色', '鲁B995EQ', '2023-03-18 01:00:00', '2023-04-18 10:00:00', '0');
INSERT INTO `car_info` VALUES (3, '王五', '13634567890', '蓝色', '京C24680', '2023-03-18 01:00:00', '2023-04-18 10:00:00', '0');
INSERT INTO `car_info` VALUES (4, 'AAB', '18888888888', '黄色', '鲁A098AS', '2023-03-17 09:00:00', '2023-03-17 09:00:00', '0');
INSERT INTO `car_info` VALUES (5, 'BBB', '19999999999', '蓝色', '赣A09IUY', '2023-03-17 09:00:00', '2023-03-17 09:00:00', '1');
INSERT INTO `car_info` VALUES (6, 'BBB', '17777777777', '蓝色', '赣D8UGH0', '2023-03-17 17:00:00', '2023-04-18 02:00:00', '0');
INSERT INTO `car_info` VALUES (7, 'CCC', '13978292002', '蓝色', '鲁B995EQ', '2023-04-05 16:00:00', '2023-04-05 16:00:00', '0');
INSERT INTO `car_info` VALUES (8, '0512日测试', '19999999999', '蓝色', '鲁B995EQ', '2023-05-11 16:00:00', '2023-05-30 16:00:00', '0');

-- ----------------------------
-- Table structure for car_scan
-- ----------------------------
DROP TABLE IF EXISTS `car_scan`;
CREATE TABLE `car_scan`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_id` bigint(0) NULL DEFAULT NULL COMMENT '停车场ID',
  `plate_color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌颜色',
  `plate_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `type` int(0) NULL DEFAULT NULL COMMENT '是否固定车（0不是 1是）',
  `direction` int(0) NULL DEFAULT NULL COMMENT '方向（0驶入 1驶出）',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扫面图片地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '扫描时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '车辆出入信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_scan
-- ----------------------------
INSERT INTO `car_scan` VALUES (1, 1, 'blue', '鲁B995EQ', 0, 0, 'http://localhost:9999/pic/947268989512417c8e11c4727bdbd3eb56bd7ce4e17746b9a997e50a9377ff44test1.jpg', '2023-04-06 14:46:26');
INSERT INTO `car_scan` VALUES (2, 1, 'blue', '鲁B995EQ', 0, 1, 'http://localhost:9999/pic/947268989512417c8e11c4727bdbd3eb56bd7ce4e17746b9a997e50a9377ff44test1.jpg', '2023-04-06 15:55:43');
INSERT INTO `car_scan` VALUES (3, 2, 'blue', '鄂CD3098', 0, 0, 'http://localhost:9999/pic/6adf030a5ec54a23a813dbb251d920f3test9.jpg', '2023-04-06 15:21:38');
INSERT INTO `car_scan` VALUES (4, 2, 'blue', '鄂CD3098', 0, 1, 'http://localhost:9999/pic/6adf030a5ec54a23a813dbb251d920f3test9.jpg', '2023-04-06 15:37:05');
INSERT INTO `car_scan` VALUES (8, 1, 'blue', '川C28888', 1, 1, 'http://localhost:9999/pic/975b67da940c4446910c371514f18745川C28888.jpg', '2023-04-14 11:27:01');
INSERT INTO `car_scan` VALUES (9, 1, 'blue', '川C28888', 1, 0, 'http://localhost:9999/pic/df028793673744968550eaec5eb8d9c6川C28888.jpg', '2023-04-14 11:29:20');
INSERT INTO `car_scan` VALUES (10, 1, 'blue', '鲁B995EQ', 0, 0, 'http://localhost:9999/pic/c1ddb0c918e04949923f558e6cbc421ctest1.jpg', '2023-04-14 11:33:42');
INSERT INTO `car_scan` VALUES (11, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/9ac117e9d9ca460b8be7ab0fd11f1348test1.jpg', '2023-04-14 11:34:34');
INSERT INTO `car_scan` VALUES (12, 1, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/6c11460c0e37488e8e76bc2becfb6e28test2.jpg', '2023-04-15 02:23:11');
INSERT INTO `car_scan` VALUES (13, 1, 'blue', '川C28888', 1, 0, 'http://localhost:9999/pic/b4536d66263a4f3fbf35cc380a740ba0川C28888.jpg', '2023-04-15 13:57:45');
INSERT INTO `car_scan` VALUES (14, 1, 'blue', '鲁KK5555', 0, 0, 'http://localhost:9999/pic/faf44afe1d4a425ea28bc1448feab7a6test16.jpg', '2023-04-16 02:35:08');
INSERT INTO `car_scan` VALUES (15, 1, 'blue', '京CX8888', 0, 0, 'http://localhost:9999/pic/6e04807ec37e4655b08d05c3b36b29aatest18.jpg', '2023-04-16 03:05:45');
INSERT INTO `car_scan` VALUES (16, 1, 'blue', '京CX8888', 0, 0, 'http://localhost:9999/pic/6e04807ec37e4655b08d05c3b36b29aatest18.jpg', '2023-04-16 03:05:45');
INSERT INTO `car_scan` VALUES (17, 1, 'blue', '鲁A88888', 0, 0, 'http://localhost:9999/pic/63b5a595d3f04aca8cce674415571706test12.jpg', '2023-04-16 06:33:48');
INSERT INTO `car_scan` VALUES (18, 1, 'blue', '京H99999', 0, 0, 'http://localhost:9999/pic/2f97ce4042d24e84b3eb1bb65cb0c7a4test14.jpg', '2023-04-16 08:51:17');
INSERT INTO `car_scan` VALUES (19, 1, 'blue', '京H99999', 0, 0, 'http://localhost:9999/pic/367a77c3049c4fee905f1e35edaed934test14.jpg', '2023-04-16 08:52:46');
INSERT INTO `car_scan` VALUES (20, 1, 'blue', '鲁A88888', 0, 0, 'http://localhost:9999/pic/7a9044732b224cfea65fe2ac0a3f6cd6test12.jpg', '2023-04-16 09:22:27');
INSERT INTO `car_scan` VALUES (21, 1, 'blue', '鲁KK5555', 0, 0, 'http://localhost:9999/pic/bdb38d5a1515477ba3849623157c8868test16.jpg', '2023-04-16 13:01:51');
INSERT INTO `car_scan` VALUES (22, 1, 'blue', '闽HB1508', 0, 0, 'http://localhost:9999/pic/f213d55b23734eb4b12e9f4c26712dd9test6.jpg', '2023-04-17 01:23:16');
INSERT INTO `car_scan` VALUES (23, 1, 'blue', '京P8BK60', 0, 0, 'http://localhost:9999/pic/295a87c4832441e5a4dced4eb3944740test5.jpg', '2023-04-17 01:34:08');
INSERT INTO `car_scan` VALUES (24, 1, 'blue', '闽HB1508', 0, 0, 'http://localhost:9999/pic/5cecd4dff10c4d2091acea679fb7563ftest6.jpg', '2023-04-17 01:39:14');
INSERT INTO `car_scan` VALUES (25, 1, 'blue', '京P8BK60', 0, 1, 'http://localhost:9999/pic/93231103da7949b0ad5b309879ed7af5test5.jpg', '2023-04-17 01:41:37');
INSERT INTO `car_scan` VALUES (26, 1, 'blue', '鲁JRW350', 0, 0, 'http://localhost:9999/pic/85fae62f3a214ed5bdd21bcccf0e67b0test7.jpg', '2023-04-17 01:44:52');
INSERT INTO `car_scan` VALUES (27, 1, 'blue', '鲁JRW350', 0, 1, 'http://localhost:9999/pic/5eef9a9bb5524278b2469e52abba18e8test7.jpg', '2023-04-17 01:45:40');
INSERT INTO `car_scan` VALUES (28, 1, 'blue', '鲁A88888', 0, 0, 'http://localhost:9999/pic/8643e1c3d61a41c98f21a06f0badcca0test12.jpg', '2023-04-17 06:08:32');
INSERT INTO `car_scan` VALUES (29, 1, 'blue', '鲁A88888', 0, 1, 'http://localhost:9999/pic/9940086bfa024c8c8a8a617fa37a7e0ftest12.jpg', '2023-04-17 06:08:49');
INSERT INTO `car_scan` VALUES (30, 1, 'blue', '浙A88888', 0, 0, 'http://localhost:9999/pic/5e56813815d34c9fb5414493bdf6db0btest11.jpg', '2023-04-17 06:10:14');
INSERT INTO `car_scan` VALUES (31, 1, 'blue', '浙A88888', 0, 1, 'http://localhost:9999/pic/66e3af99e30646588465b28ea450f952test11.jpg', '2023-04-17 06:10:40');
INSERT INTO `car_scan` VALUES (32, 1, 'blue', '黑A16341', 0, 0, 'http://localhost:9999/pic/414c9c6c332b46068fbbbc8e6a3330d1test4.jpg', '2023-04-19 02:09:27');
INSERT INTO `car_scan` VALUES (33, 1, 'blue', '黑A16341', 0, 1, 'http://localhost:9999/pic/4e3cb47b30704f3b83ed54a506ab0ec4test4.jpg', '2023-04-19 16:00:00');
INSERT INTO `car_scan` VALUES (34, 1, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/ac4f6f9953804df9a98876e269d5c080test2.jpg', '2023-04-19 02:19:37');
INSERT INTO `car_scan` VALUES (35, 1, 'blue', '苏E6HX29', 0, 1, 'http://localhost:9999/pic/de4ef03887c14e6b97826f4a2e14f945test2.jpg', '2023-05-02 02:59:00');
INSERT INTO `car_scan` VALUES (36, 1, 'blue', '苏E6HX29', 0, 1, 'http://localhost:9999/pic/de4ef03887c14e6b97826f4a2e14f945test2.jpg', '2023-04-19 02:59:00');
INSERT INTO `car_scan` VALUES (37, 1, 'blue', '京H99999', 0, 0, 'http://localhost:9999/pic/6ad654ea2eaf4fa4bec68fd05a0fa29dtest14.jpg', '2023-04-19 02:22:39');
INSERT INTO `car_scan` VALUES (38, 1, 'blue', '京H99999', 0, 1, 'http://localhost:9999/pic/f8ff7787504f45eeab1b3f97f14157d0test14.jpg', '2023-04-19 02:59:54');
INSERT INTO `car_scan` VALUES (39, 1, 'blue', '粤BTM529', 0, 0, 'http://localhost:9999/pic/990dd4901043475a9f84caa983c0e77ctest10.jpg', '2023-04-19 02:27:00');
INSERT INTO `car_scan` VALUES (40, 1, 'blue', '粤BTM529', 0, 1, 'http://localhost:9999/pic/44abfc7eb42a4ec2a0f1276a8baa10cftest10.jpg', '2023-04-19 02:27:42');
INSERT INTO `car_scan` VALUES (43, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/8e869f84093149f893799ce279130e66test1.jpg', '2023-04-19 07:09:48');
INSERT INTO `car_scan` VALUES (44, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/fff90a0b40234e4f8fb36f5aab20ba98test1.jpg', '2023-04-19 07:10:16');
INSERT INTO `car_scan` VALUES (45, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/77459bdcbd38424688c1fbc810b8b5ebtest1.jpg', '2023-04-19 07:46:56');
INSERT INTO `car_scan` VALUES (46, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/e8b6ccb80e3b49818ff8e71eea77705ftest1.jpg', '2023-05-02 10:14:05');
INSERT INTO `car_scan` VALUES (47, 1, 'blue', '京A88731', 0, 0, 'http://localhost:9999/pic/e3e7e94a14014ed8bc606f3aae9efa67test17.jpg', '2023-05-02 10:16:00');
INSERT INTO `car_scan` VALUES (48, 1, 'blue', '京A88731', 0, 1, 'http://localhost:9999/pic/189e8d572a054e028efb3581eae279dftest17.jpg', '2023-05-02 10:17:09');
INSERT INTO `car_scan` VALUES (49, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/7a7bee7010c04c59ad752b0eb7c0f6b4test1.jpg', '2023-05-09 10:22:58');
INSERT INTO `car_scan` VALUES (50, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/aaebc98de85d4ee5b7f1dd23b65db3c2test1.jpg', '2023-05-09 10:23:05');
INSERT INTO `car_scan` VALUES (51, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/35fea9ab5e89404f812f82341887e73dtest1.jpg', '2023-05-09 12:42:19');
INSERT INTO `car_scan` VALUES (52, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/7d85b933fc274770b0b0ef1753a665bdtest1.jpg', '2023-05-09 12:42:29');
INSERT INTO `car_scan` VALUES (53, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/1dae1e7796c344a8b40ea9b2730cdb47test1.jpg', '2023-05-09 12:45:45');
INSERT INTO `car_scan` VALUES (54, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/4bcfd2e24c6a4b28bf33da9a50949c2ftest1.jpg', '2023-05-09 12:46:03');
INSERT INTO `car_scan` VALUES (55, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/913565ea4b4f4547a69be263c005b476test1.jpg', '2023-05-09 12:47:51');
INSERT INTO `car_scan` VALUES (56, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/97470f43dfb641b0a1002f5e5c3461f5test1.jpg', '2023-05-09 12:47:57');
INSERT INTO `car_scan` VALUES (57, 1, 'blue', '川C28888', 1, 0, 'http://localhost:9999/pic/525ae1b2f3bc4589b68c8bd971fb2d72川C28888.jpg', '2023-05-09 12:50:16');
INSERT INTO `car_scan` VALUES (58, 1, 'blue', '川C28888', 1, 1, 'http://localhost:9999/pic/d4cc3ed4bf914f24aca6fb6faf9622ce川C28888.jpg', '2023-05-09 12:50:24');
INSERT INTO `car_scan` VALUES (59, 1, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/0e1c931bc8da4601a810f3e7192231fetest2.jpg', '2023-05-09 12:52:02');
INSERT INTO `car_scan` VALUES (65, 1, 'blue', '苏E6HX29', 0, 1, '', '2023-05-09 13:18:30');
INSERT INTO `car_scan` VALUES (66, 1, 'blue', '苏E6HX29', 0, 0, '', '2023-05-09 13:26:37');
INSERT INTO `car_scan` VALUES (67, 1, 'blue', '苏E6HX29', 0, 1, '', '2023-05-09 13:26:55');
INSERT INTO `car_scan` VALUES (68, 1, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/54aab57cedaf4dcab197877d5918995ftest2.jpg', '2023-05-09 14:33:08');
INSERT INTO `car_scan` VALUES (69, 1, 'blue', '苏E6HX29', 0, 1, 'http://localhost:9999/pic/8b12db5c8dd340eabf8087cf2ee740f0test2.jpg', '2023-05-09 14:41:03');
INSERT INTO `car_scan` VALUES (70, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/fef8521c3066403981dfeed58fa69378test1.jpg', '2023-05-09 15:13:17');
INSERT INTO `car_scan` VALUES (71, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/43da10a241244069be043084daa1ae06test1.jpg', '2023-05-09 15:13:25');
INSERT INTO `car_scan` VALUES (72, 1, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/46c2d20c84c94103920c085dff4eeafatest2.jpg', '2023-05-09 15:15:35');
INSERT INTO `car_scan` VALUES (73, 1, 'blue', '苏E6HX29', 0, 1, 'http://localhost:9999/pic/f53047b9266547699f58e7d6ff5f1c4atest2.jpg', '2023-05-09 15:15:50');
INSERT INTO `car_scan` VALUES (74, 1, 'blue', '川C28888', 1, 0, 'http://localhost:9999/pic/eb9d983ae6624816acb01d37ff4aeaf5川C28888.jpg', '2023-05-09 15:16:42');
INSERT INTO `car_scan` VALUES (75, 1, 'blue', '川C28888', 1, 1, 'http://localhost:9999/pic/be321ad71e204d19893fddcfa3110f4b川C28888.jpg', '2023-05-09 15:16:52');
INSERT INTO `car_scan` VALUES (76, 1, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/559e4fad27004930a6782d8414ab59e3test1.jpg', '2023-05-10 01:00:56');
INSERT INTO `car_scan` VALUES (77, 1, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/98eace500949422bb6fbbaeef366d22etest1.jpg', '2023-05-10 01:01:03');
INSERT INTO `car_scan` VALUES (78, 1, 'blue', '川C28888', 1, 0, 'http://localhost:9999/pic/882101bd8b614ea795cd6d7d11b78af3川C28888.jpg', '2023-05-10 01:04:20');
INSERT INTO `car_scan` VALUES (79, 1, 'blue', '川C28888', 1, 1, 'http://localhost:9999/pic/998475edf797434eb2592bf8efba6b26川C28888.jpg', '2023-05-10 01:04:30');
INSERT INTO `car_scan` VALUES (80, 4, 'blue', '鲁B995EQ', 0, 0, 'http://localhost:9999/pic/371cccc9e5934f6380bd7b642d36b764test1.jpg', '2023-05-10 01:48:44');
INSERT INTO `car_scan` VALUES (81, 4, 'blue', '鲁B995EQ', 0, 1, 'http://localhost:9999/pic/28bb14c2130a4af3adaafb34bed93932test1.jpg', '2023-05-10 01:49:03');
INSERT INTO `car_scan` VALUES (82, 4, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/3288a90ecc1a4c16a9817a22f342f9e5test2.jpg', '2023-05-10 02:28:25');
INSERT INTO `car_scan` VALUES (83, 4, 'blue', '苏E6HX29', 0, 1, 'http://localhost:9999/pic/aaf975932b134c479a911a8f40aac557test2.jpg', '2023-05-10 02:28:51');
INSERT INTO `car_scan` VALUES (84, 4, 'blue', '鲁BQG527', 0, 0, 'http://localhost:9999/pic/573b7d6a67d94122923c295a9b4f44eetest3.jpg', '2023-05-10 02:35:59');
INSERT INTO `car_scan` VALUES (85, 4, 'blue', '鲁BQG527', 0, 1, 'http://localhost:9999/pic/74a2dd04cb724f2484f0930c9d6e83batest3.jpg', '2023-05-10 02:36:13');
INSERT INTO `car_scan` VALUES (86, 4, 'blue', '黑A16341', 0, 0, 'http://localhost:9999/pic/2406b215cb25423c852d8258620856aftest4.jpg', '2023-05-10 05:31:55');
INSERT INTO `car_scan` VALUES (87, 4, 'blue', '黑A16341', 0, 1, 'http://localhost:9999/pic/45334802c7b449729ef7e77214b5a96atest4.jpg', '2023-05-10 05:32:14');
INSERT INTO `car_scan` VALUES (88, 4, 'blue', '京P8BK60', 0, 0, 'http://localhost:9999/pic/457ec7068e934f598bdc44695d302c5atest5.jpg', '2023-05-11 02:31:00');
INSERT INTO `car_scan` VALUES (89, 4, 'blue', '京P8BK60', 0, 1, 'http://localhost:9999/pic/55553fac3a6d48b0bb998c5390f8b956test5.jpg', '2023-05-11 02:31:25');
INSERT INTO `car_scan` VALUES (90, 4, 'blue', '鲁B995EQ', 0, 0, 'http://localhost:9999/pic/5aeb11644685400cb2dba63e24e1b131test1.jpg', '2023-05-11 02:37:28');
INSERT INTO `car_scan` VALUES (93, 4, 'blue', '鲁B995EQ', 0, 1, 'http://localhost:9999/pic/929b3394e1fe45e089b0dcdc5d578e00test1.jpg', '2023-05-11 02:48:04');
INSERT INTO `car_scan` VALUES (94, 7, 'blue', '鲁B995EQ', 1, 0, 'http://localhost:9999/pic/f65ae9be54034aa7b6ba1b548f797b47test1.jpg', '2023-05-12 09:51:05');
INSERT INTO `car_scan` VALUES (95, 7, 'blue', '苏E6HX29', 0, 0, 'http://localhost:9999/pic/a41f040b646c4201be1a75150f2c77batest2.jpg', '2023-05-12 09:51:26');
INSERT INTO `car_scan` VALUES (96, 7, 'blue', '鲁B995EQ', 1, 1, 'http://localhost:9999/pic/2a0a247c52ff4969be053c307d180065test1.jpg', '2023-05-12 09:51:40');
INSERT INTO `car_scan` VALUES (97, 7, 'blue', '苏E6HX29', 0, 1, 'http://localhost:9999/pic/d50943f7a0ce4d89ae8c516fc902bc98test2.jpg', '2023-05-12 09:52:04');

-- ----------------------------
-- Table structure for park_car
-- ----------------------------
DROP TABLE IF EXISTS `park_car`;
CREATE TABLE `park_car`  (
  `car_id` bigint(0) NOT NULL COMMENT '固定车ID',
  `park_id` bigint(0) NOT NULL COMMENT '停车场ID',
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '固定车和停车场关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of park_car
-- ----------------------------
INSERT INTO `park_car` VALUES (1, 1);
INSERT INTO `park_car` VALUES (2, 1);
INSERT INTO `park_car` VALUES (3, 2);
INSERT INTO `park_car` VALUES (4, 4);
INSERT INTO `park_car` VALUES (6, 5);
INSERT INTO `park_car` VALUES (7, 2);
INSERT INTO `park_car` VALUES (8, 7);

-- ----------------------------
-- Table structure for park_info
-- ----------------------------
DROP TABLE IF EXISTS `park_info`;
CREATE TABLE `park_info`  (
  `park_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_amount` bigint(0) NULL DEFAULT NULL COMMENT '车位数',
  `park_spare` bigint(0) NULL DEFAULT NULL COMMENT '剩余车位数',
  `park_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '停车场地址',
  `park_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '停车场名字',
  `park_rule` decimal(10, 2) NULL DEFAULT NULL COMMENT '收费规则',
  `park_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '停车场照片地址',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '停车场信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of park_info
-- ----------------------------
INSERT INTO `park_info` VALUES (1, 100, 100, '主街1号', '中央停车场', 6.00, 'https://images.pexels.com/photos/257416/pexels-photo-257416.jpeg?auto=compress&cs=tinysrgb&w=600', '0');
INSERT INTO `park_info` VALUES (2, 50, 50, '北街2号', '北停车场', 6.00, 'https://images.pexels.com/photos/753865/pexels-photo-753865.jpeg?auto=compress&cs=tinysrgb&w=600', '0');
INSERT INTO `park_info` VALUES (3, 200, 200, '南街3号', '南停车场', 3.00, 'https://images.pexels.com/photos/1756957/pexels-photo-1756957.jpeg?auto=compress&cs=tinysrgb&w=600', '0');
INSERT INTO `park_info` VALUES (4, 100, 100, '测试1', '测试1', 5.00, 'http://localhost:9999/pic/a10c56c68c2e4a3789be817b58e0779b3.jpeg', '0');
INSERT INTO `park_info` VALUES (5, 1000, 1000, '测试2', '测试2', 4.00, 'http://localhost:9999/pic/52d252d0635c488e8bd5c4ade65256672.jpeg', '0');
INSERT INTO `park_info` VALUES (6, 70, 70, '测试3', '测试3', 6.00, 'https://images.pexels.com/photos/1756957/pexels-photo-1756957.jpeg?auto=compress&cs=tinysrgb&w=600', '1');
INSERT INTO `park_info` VALUES (7, 400, 400, '西海岸', '0512日测试停车场', 10.00, 'http://localhost:9999/pic/700bdd7578a441c4ac16baee64d25a2c1.jpeg', '0');
INSERT INTO `park_info` VALUES (8, 666, 666, '图传', '测试图片上传', 666.00, 'http://localhost:9999/pic/496ceb394b634772b25929e7d775ede71.jpeg', '0');
INSERT INTO `park_info` VALUES (9, 66, 66, '测试手动上传', '测试手动上传', 66.00, NULL, '1');
INSERT INTO `park_info` VALUES (10, 66, 66, '测试手动上传', '测试手动上传', 6.00, NULL, '1');
INSERT INTO `park_info` VALUES (11, 77, 77, '测试手动上传', '测试手动上传', 7.00, NULL, '1');
INSERT INTO `park_info` VALUES (12, 666, 666, '传！', '测试上传', 6.00, NULL, '1');
INSERT INTO `park_info` VALUES (13, 2, 2, 'd', 'd', 2.00, 'http://localhost:9999/pic/a2666e8eba3b4b6b8cfe191df2474c43粤B0K999.jpg', '1');
INSERT INTO `park_info` VALUES (14, 666, 666, 'v', 'v', 6.00, 'http://localhost:9999/pic/e40924c082f44a55956d52474fe6715b粤B0K999.jpg', '1');

-- ----------------------------
-- Table structure for park_order
-- ----------------------------
DROP TABLE IF EXISTS `park_order`;
CREATE TABLE `park_order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(0) NULL DEFAULT NULL COMMENT '车辆ID',
  `park_id` bigint(0) NULL DEFAULT NULL COMMENT '停车场ID',
  `type` int(0) NULL DEFAULT NULL COMMENT '是否固定车（0不是 1是）',
  `plate_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌颜色',
  `plate_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `entry_time` datetime(0) NULL DEFAULT NULL COMMENT '进入停车场时间',
  `exit_time` datetime(0) NULL DEFAULT NULL COMMENT '离开停车场时间',
  `parking_duration` int(0) NULL DEFAULT NULL COMMENT '停车时长',
  `park_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '停车收费金额',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标识（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of park_order
-- ----------------------------
INSERT INTO `park_order` VALUES (1, 1, 1, 1, 'blue', '京A12345', '/parking/picture/1.jpg', '2021-01-01 09:00:00', '2021-01-01 11:00:00', 2, 10.00, '1');
INSERT INTO `park_order` VALUES (2, 2, 1, 1, 'blue', '京B67890', '/parking/picture/2.jpg', '2021-02-01 08:00:00', '2021-02-01 14:00:00', 6, 30.00, '1');
INSERT INTO `park_order` VALUES (3, 3, 2, 0, 'blue', '京C24680', '/parking/picture/3.jpg', '2021-03-01 10:00:00', '2021-03-01 12:00:00', 2, 10.00, '1');
INSERT INTO `park_order` VALUES (4, NULL, 1, 0, 'blue', '京CX8888', NULL, '2023-04-16 03:05:45', '2023-04-16 09:44:35', NULL, 54.00, '1');
INSERT INTO `park_order` VALUES (5, NULL, 1, 0, 'blue', '鲁A88888', NULL, '2023-04-16 06:33:48', '2023-04-16 09:44:35', NULL, 36.00, '1');
INSERT INTO `park_order` VALUES (6, NULL, 1, 0, 'blue', '京H99999', NULL, '2023-04-16 08:52:46', '2023-04-16 09:44:35', NULL, 24.00, '1');
INSERT INTO `park_order` VALUES (8, NULL, 1, 0, 'blue', '鲁KK5555', NULL, '2023-04-16 13:01:51', '2023-04-25 16:00:00', NULL, 3.00, '1');
INSERT INTO `park_order` VALUES (9, NULL, 1, 0, 'blue', '闽HB1508', NULL, '2023-04-17 01:23:16', '2023-04-17 01:39:14', 0, 3.00, '0');
INSERT INTO `park_order` VALUES (10, NULL, 1, 0, 'blue', '京P8BK60', NULL, '2023-04-17 01:34:08', '2023-04-17 01:41:37', 0, 3.00, '0');
INSERT INTO `park_order` VALUES (11, NULL, 1, 0, 'blue', '鲁JRW350', NULL, '2023-04-17 01:44:52', '2023-04-17 01:45:40', 0, 3.00, '0');
INSERT INTO `park_order` VALUES (12, NULL, 1, 0, 'blue', '鲁A88888', NULL, '2023-04-17 06:08:32', '2023-04-17 06:08:49', 0, 3.00, '0');
INSERT INTO `park_order` VALUES (13, NULL, 1, 0, 'blue', '浙A88888', NULL, '2023-04-17 06:10:14', '2023-04-17 06:10:40', 0, 3.00, '0');
INSERT INTO `park_order` VALUES (14, NULL, 1, 0, 'blue', '黑A16341', NULL, '2023-04-19 02:09:27', '2023-04-19 16:00:00', 13, 78.00, '0');
INSERT INTO `park_order` VALUES (15, NULL, 1, 0, 'blue', '苏E6HX29', NULL, '2023-04-19 02:19:37', '2023-05-02 02:59:00', 312, 1000.00, '0');
INSERT INTO `park_order` VALUES (16, NULL, 1, 0, 'blue', '京H99999', NULL, '2023-04-19 02:22:39', '2023-04-19 02:59:54', 0, 6.00, '0');
INSERT INTO `park_order` VALUES (17, NULL, 1, 0, 'blue', '粤BTM529', NULL, '2023-04-19 02:27:00', '2023-04-19 02:27:42', 0, 6.00, '0');
INSERT INTO `park_order` VALUES (18, NULL, 1, 0, 'black', '黑AB4444', NULL, '2023-04-19 02:29:55', '2023-04-19 02:30:06', 0, 6.00, '0');
INSERT INTO `park_order` VALUES (21, NULL, 1, 0, 'blue', '京A88731', NULL, '2023-05-02 10:16:00', '2023-05-02 10:17:09', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (22, NULL, 1, 1, 'blue', '鲁B995EQ', NULL, '2023-05-09 10:22:58', '2023-05-09 10:23:05', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (23, NULL, 1, 1, 'blue', '鲁B995EQ', NULL, '2023-05-09 12:42:19', '2023-05-09 12:42:29', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (24, NULL, 1, 1, 'blue', '鲁B995EQ', NULL, '2023-05-09 12:45:45', '2023-05-09 12:46:03', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (25, NULL, 1, 1, 'blue', '鲁B995EQ', NULL, '2023-05-09 12:47:51', '2023-05-09 12:47:57', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (26, NULL, 1, 1, 'blue', '川C28888', NULL, '2023-05-09 12:50:16', '2023-05-09 12:50:24', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (27, NULL, 1, 0, 'blue', '苏E6HX29', NULL, '2023-05-09 12:52:02', '2023-05-09 13:18:30', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (28, NULL, 1, 0, 'blue', '苏E6HX29', NULL, '2023-05-09 13:26:37', '2023-05-09 13:26:55', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (29, NULL, 1, 0, 'blue', '苏E6HX29', NULL, '2023-05-09 14:33:08', '2023-05-09 14:41:03', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (30, NULL, 1, 1, 'blue', '鲁B995EQ', NULL, '2023-05-09 15:13:17', '2023-05-09 15:13:25', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (31, NULL, 1, 0, 'blue', '苏E6HX29', NULL, '2023-05-09 15:15:35', '2023-05-09 15:15:50', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (32, NULL, 1, 1, 'blue', '川C28888', NULL, '2023-05-09 15:16:42', '2023-05-09 15:16:52', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (33, NULL, 1, 1, 'blue', '鲁B995EQ', NULL, '2023-05-10 01:00:56', '2023-05-10 01:01:03', 1, 6.00, '0');
INSERT INTO `park_order` VALUES (34, NULL, 1, 1, 'blue', '川C28888', NULL, '2023-05-10 01:04:20', '2023-05-10 01:04:30', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (35, NULL, 4, 0, 'blue', '鲁B995EQ', NULL, '2023-05-10 01:48:44', '2023-05-10 01:49:03', 1, 5.00, '0');
INSERT INTO `park_order` VALUES (36, NULL, 4, 0, 'blue', '苏E6HX29', NULL, '2023-05-10 02:28:25', '2023-05-10 02:28:51', 1, 5.00, '0');
INSERT INTO `park_order` VALUES (37, NULL, 4, 0, 'blue', '鲁BQG527', NULL, '2023-05-10 02:35:59', '2023-05-10 02:36:13', 1, 5.00, '0');
INSERT INTO `park_order` VALUES (38, NULL, 4, 0, 'blue', '黑A16341', NULL, '2023-05-10 05:31:55', '2023-05-10 05:32:14', 1, 5.00, '0');
INSERT INTO `park_order` VALUES (39, NULL, 4, 0, 'blue', '京P8BK60', NULL, '2023-05-11 02:31:00', '2023-05-11 02:31:25', 1, 5.00, '0');
INSERT INTO `park_order` VALUES (40, NULL, 4, 0, 'blue', '鲁B995EQ', NULL, '2023-05-11 02:37:28', '2023-05-11 02:48:04', 1, 5.00, '0');
INSERT INTO `park_order` VALUES (43, NULL, 7, 1, 'blue', '鲁B995EQ', NULL, '2023-05-12 09:51:05', '2023-05-12 09:51:40', 1, 0.00, '0');
INSERT INTO `park_order` VALUES (44, NULL, 7, 0, 'blue', '苏E6HX29', NULL, '2023-05-12 09:51:26', '2023-05-12 09:52:04', 1, 10.00, '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路由',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由',
  `redirect` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '重定向',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父菜单id',
  `is_leaf` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否为叶子节点',
  `hidden` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'Layout', '/inAndOut', '/inAndOut/carIn', 'inAndOut', '进出识别', 'car', 0, 'N', 0);
INSERT INTO `sys_menu` VALUES (2, 'inAndOut/carIn/index', '/carIn/index', NULL, 'carIn', '入场识别', 'carP', 1, 'Y', 0);
INSERT INTO `sys_menu` VALUES (3, 'inAndOut/carOut/index', '/carOut/index', NULL, 'carOut', '出场识别', 'carP', 1, 'Y', 0);
INSERT INTO `sys_menu` VALUES (4, 'Layout', '/parking', '/parking/parkingInfo', 'parking', '停车场管理', 'form', 0, 'N', 0);
INSERT INTO `sys_menu` VALUES (5, 'parking/parkingInfo/index', 'parkingInfo/index', '', 'parkingInfo', '停车场管理', 'parking', 4, 'Y', 0);
INSERT INTO `sys_menu` VALUES (6, 'parking/vipCar/index', 'vipCar/index', NULL, 'vipCar', '固定车管理', 'car1', 4, 'Y', 0);
INSERT INTO `sys_menu` VALUES (7, 'Layout', '/record', '/record/entryAndExit', 'record', '进出记录管理', 'record', 0, 'N', 0);
INSERT INTO `sys_menu` VALUES (8, 'record/entryAndExit/index', 'entryAndExit/index', NULL, 'entryAndExit', '进出记录', 'record1', 7, 'Y', 0);
INSERT INTO `sys_menu` VALUES (9, 'record/order/index', 'order/index', NULL, 'order', '订单记录', 'order', 7, 'Y', 0);
INSERT INTO `sys_menu` VALUES (10, 'Layout', '/system', '/system/user', 'sysManage', '系统管理', 'sys', 0, 'N', 0);
INSERT INTO `sys_menu` VALUES (11, 'system/user/index', 'user/index', NULL, 'userList', '用户管理', 'user', 10, 'Y', 0);
INSERT INTO `sys_menu` VALUES (12, 'system/role/index', 'role/index', NULL, 'roleList', '角色管理', 'roleManage', 10, 'Y', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（1正常 0停用）',
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识（超级管理员(root) 管理员(admin) 用户(user)）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'root', '1', '超级管理员', '0', '2023-03-02 14:34:17');
INSERT INTO `sys_role` VALUES (2, 'admin', '1', '管理员', '0', '2023-03-06 06:34:55');
INSERT INTO `sys_role` VALUES (157, 'test', '1', '测试员', '0', NULL);
INSERT INTO `sys_role` VALUES (158, 'test1', '1', '测试员', '1', NULL);
INSERT INTO `sys_role` VALUES (159, 'test2', '0', '测试员', '1', NULL);
INSERT INTO `sys_role` VALUES (160, 'test3', '1', '测试员', '1', NULL);
INSERT INTO `sys_role` VALUES (161, 'user', '1', '普通用户', '1', NULL);
INSERT INTO `sys_role` VALUES (162, 'test1', '1', '测试1', '1', '2023-03-22 07:14:34');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (157, 1);
INSERT INTO `sys_role_menu` VALUES (157, 2);
INSERT INTO `sys_role_menu` VALUES (157, 3);
INSERT INTO `sys_role_menu` VALUES (157, 4);
INSERT INTO `sys_role_menu` VALUES (157, 5);
INSERT INTO `sys_role_menu` VALUES (157, 6);
INSERT INTO `sys_role_menu` VALUES (157, 7);
INSERT INTO `sys_role_menu` VALUES (157, 8);
INSERT INTO `sys_role_menu` VALUES (157, 9);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号，用户名(工号)',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '暂无昵称' COMMENT '用户昵称，姓名',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '用户性别（0女 1男 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（1正常 0停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '超级管理员', 'laoayu@163.com', '13970628992', '1', 'http://localhost:9999/pic/a5a5384070984efb8f9c96f08beab658QQ图片20220625142710.jpg', '$2a$10$18n60xxjaVe8eNDVFAjQ6eIUM/vndZm1ddFC.lQiJvT5.qqHLHTta', '1', '0', '2023-03-05 21:54:21', 1);
INSERT INTO `sys_user` VALUES (2, 'admin', '管理员', 'llll@qq.com', '12782936738', '1', 'http://localhost:9999/pic/a5a5384070984efb8f9c96f08beab658QQ图片20220625142710.jpg', '$2a$10$18n60xxjaVe8eNDVFAjQ6eIUM/vndZm1ddFC.lQiJvT5.qqHLHTta', '1', '0', '2023-03-07 13:55:15', 2);
INSERT INTO `sys_user` VALUES (3, 'test', '测试', 'test@163.com', '12677862782', '0', 'http://localhost:9999/pic/a5a5384070984efb8f9c96f08beab658QQ图片20220625142710.jpg', '$2a$10$18n60xxjaVe8eNDVFAjQ6eIUM/vndZm1ddFC.lQiJvT5.qqHLHTta', '1', '0', '2023-03-09 00:13:46', 3);
INSERT INTO `sys_user` VALUES (146, 'test1', '测试员1号', 'test1@qq.com', '12345685389', '1', 'http://localhost:9999/pic/a5a5384070984efb8f9c96f08beab658QQ图片20220625142710.jpg', '$2a$10$18n60xxjaVe8eNDVFAjQ6eIUM/vndZm1ddFC.lQiJvT5.qqHLHTta', '0', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (147, 'aaa', 'aa', 'aaa@163.com', '11111111111', '0', '', '$2a$10$18n60xxjaVe8eNDVFAjQ6eIUM/vndZm1ddFC.lQiJvT5.qqHLHTta', '0', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (149, 'test2', '测试2', '2222@qq.com', '11111111111', '0', '', '$2a$10$etcLe8ELLNu8fLBECqtHPee3c34jMlsrz2JZRpog3ne3UkWd9QAqW', '0', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (150, 'test3', '测试3', 'test3@qq.com', '12345643211', '0', '', '$2a$10$PDNlAoe4WQdvU0qB9FrusObfwqPH2oWglOeLLnm7vmtnJgyfFE2We', '1', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (151, '111', '111', '1111@test.com', '1111111111', '1', '', '$2a$10$E6CjtBcIY.J0xljlLs.RqejEQa7Uj0OVs/jrMjhLgz4zCMkBtLc8W', '1', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (152, 'xxx', 'xxx', 'xxx@qq.com', '12345444444', '1', '', '$2a$10$Upj6hwDeNkOXTqI89Ml1auqNPPXdhHT0lP1VDZTmYH5cNo187JeLm', '1', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (153, 'bbb', 'bbb', 'bbb@qq.com', '18888888888', '0', '', '$2a$10$00lqrddMaoUFUfhyKu0XTOmqyXMNxT7CBIdV/Je.9KzC77gGZtZve', '1', '1', '2023-03-14 06:29:56', NULL);
INSERT INTO `sys_user` VALUES (154, 'bbb', 'bbb', 'bbb@163.com', '', '0', '', '$2a$10$ffIjPnLBVM8ux/LVypW6r.0H1dF5Jj001OGDKhSQ5QjLBt3HE9Dhm', '0', '1', '2023-05-10 06:49:39', NULL);
INSERT INTO `sys_user` VALUES (155, 'bbbb', NULL, 'bbb@163.com', '', '1', '', '$2a$10$v9HbRDStgiJ6sNd0gxGkSuzkwynwqAh9E9FahIB2.g2fgSoJSJo96', '0', '1', '2023-05-10 06:55:02', NULL);
INSERT INTO `sys_user` VALUES (156, 'xxx1', '暂无昵称', 'xxx@163.com', '', '1', '', '$2a$10$ioYDkPhBMHJdVtzVjBuXWeuoUMR/bP9LR/A7BCVN53IzJj.fJ.ESu', '0', '1', '2023-05-10 06:57:34', NULL);
INSERT INTO `sys_user` VALUES (157, 'test0512', '0512日测试', 'test0512@163.com', '1888888888', '1', '', '$2a$10$HSWcOL1.Nu5Rg3mlxkbr6enFQ9pz1KKdjMl.FIrQltR3e7WN.GemO', '1', '0', '2023-05-12 09:47:04', NULL);

-- ----------------------------
-- Table structure for sys_user_park
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_park`;
CREATE TABLE `sys_user_park`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `park_id` bigint(0) NOT NULL COMMENT '停车场ID',
  PRIMARY KEY (`user_id`, `park_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户停车场关联表，用于连接管理员所管理的停车场' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_park
-- ----------------------------
INSERT INTO `sys_user_park` VALUES (1, 1);
INSERT INTO `sys_user_park` VALUES (1, 2);
INSERT INTO `sys_user_park` VALUES (1, 3);
INSERT INTO `sys_user_park` VALUES (1, 4);
INSERT INTO `sys_user_park` VALUES (1, 5);
INSERT INTO `sys_user_park` VALUES (1, 7);
INSERT INTO `sys_user_park` VALUES (2, 1);
INSERT INTO `sys_user_park` VALUES (3, 4);
INSERT INTO `sys_user_park` VALUES (157, 7);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (2, 157);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (150, 2);
INSERT INTO `sys_user_role` VALUES (150, 157);
INSERT INTO `sys_user_role` VALUES (157, 157);

SET FOREIGN_KEY_CHECKS = 1;
