/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2019-07-11 22:39:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `add_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `cust_id` int(11) DEFAULT NULL COMMENT '客户id',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `district` varchar(255) DEFAULT NULL COMMENT '区/县',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收件人',
  `flag` varchar(255) DEFAULT '1',
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`add_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('23', '13', '123123123', '河南省', '南阳市', '宛城区', '请问', 'ikun', '1', null, null);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏夹id',
  `cust_id` int(11) DEFAULT NULL COMMENT '顾客id',
  `prod_id` int(11) DEFAULT NULL COMMENT '商品id',
  `flag` varchar(255) DEFAULT '1' COMMENT '1存在 0删除',
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='商品收藏表';

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `sup_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '生产厂家编号',
  `sup_name` char(30) DEFAULT NULL COMMENT '生产厂家名',
  `flag` varchar(255) DEFAULT '1',
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sup_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='生产厂家表(现是营销活动表)';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '秒杀', '1', null, null);
INSERT INTO `company` VALUES ('2', '今日推荐', '1', null, null);
INSERT INTO `company` VALUES ('3', '超值', '1', null, null);
INSERT INTO `company` VALUES ('4', '优惠', '1', null, null);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `cust_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '注册号(id)',
  `cust_code` char(255) CHARACTER SET cp1256 DEFAULT NULL COMMENT '客户密码',
  `email` varchar(255) DEFAULT NULL COMMENT '客户邮箱',
  `regis_date` datetime DEFAULT NULL COMMENT '客户注册时间',
  `zip` char(255) DEFAULT NULL COMMENT '邮编',
  `tel_no` char(11) DEFAULT NULL COMMENT '电话',
  `sex` char(2) DEFAULT '3' COMMENT '性别(1.男2女3秘密,默认3)',
  `cust_name` char(255) DEFAULT NULL COMMENT '账号',
  `cust_level` char(8) DEFAULT '0' COMMENT '客户等级',
  `cust_sco` int(11) DEFAULT NULL COMMENT '客户积分',
  `username` varchar(255) DEFAULT NULL COMMENT '昵称',
  `createtime` datetime DEFAULT NULL COMMENT '注册时间',
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='客户表';

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery` (
  `deliv_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '配送单编号',
  `cust_id` int(11) DEFAULT NULL COMMENT '客户id',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `prod_id` int(11) DEFAULT NULL COMMENT '商品编号',
  `qty` int(11) DEFAULT NULL COMMENT '商品数量',
  `unit_price` decimal(7,2) DEFAULT NULL COMMENT '商品单价',
  `tot_amt` decimal(9,2) DEFAULT NULL COMMENT '订单总额',
  `zip` char(6) DEFAULT NULL COMMENT '邮编',
  `addr` char(40) DEFAULT NULL COMMENT '地址',
  `tel_no` char(11) DEFAULT NULL COMMENT '电话',
  `deliv_date` datetime DEFAULT NULL COMMENT '配送日期',
  `cust_name` char(8) DEFAULT NULL COMMENT '客户姓名',
  `extend1` varchar(255) DEFAULT NULL,
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deliv_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品配送单表';

-- ----------------------------
-- Records of delivery
-- ----------------------------

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `cust_level` char(8) NOT NULL COMMENT '客户等级',
  `discount` decimal(7,2) NOT NULL COMMENT '优惠率',
  `sco_re` char(20) NOT NULL DEFAULT '0' COMMENT '积分要求',
  `extend1` varchar(255) DEFAULT NULL,
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_level`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠表';

-- ----------------------------
-- Records of discount
-- ----------------------------

-- ----------------------------
-- Table structure for kind
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `kind_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别编号',
  `kind_name` char(15) DEFAULT NULL COMMENT '商品类别名',
  `flag` varchar(255) DEFAULT '1',
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kind_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品类别表';

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('1', '水果', '1', null, null);
INSERT INTO `kind` VALUES ('2', '零食', '1', null, null);
INSERT INTO `kind` VALUES ('3', '小吃', '1', null, null);
INSERT INTO `kind` VALUES ('6', '主食', '1', null, null);
INSERT INTO `kind` VALUES ('7', '甜品', '1', null, null);
INSERT INTO `kind` VALUES ('8', '饮料', '1', null, null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` char(20) DEFAULT NULL COMMENT '商品名称',
  `kind_no` char(6) DEFAULT NULL COMMENT '商品类别编号',
  `sup_no` char(6) DEFAULT NULL COMMENT '商品品牌编号',
  `status` int(4) DEFAULT '1' COMMENT '商品状态（1在售，2下架，3售空）默认在售',
  `is_hot` int(4) DEFAULT '4' COMMENT '营销方式(默认为正常销售0、1秒杀、2今日推荐、3超值)',
  `storage` int(11) DEFAULT NULL COMMENT '商品库存量(暂时未用)',
  `prod_desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `image` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `pro_date` datetime DEFAULT NULL COMMENT '商品生产日期',
  `keep_date` datetime DEFAULT NULL COMMENT '商品保质期',
  `unit_price` decimal(7,2) DEFAULT NULL COMMENT '商品单价',
  `supply` int(11) DEFAULT NULL COMMENT '库存量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `extend1` varchar(255) DEFAULT '' COMMENT '已使用',
  `extend2` varchar(255) DEFAULT NULL COMMENT '已使用',
  `extend3` varchar(255) DEFAULT '' COMMENT '已使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('2', '苹果', '1', '2', '1', '3', '200', '苹果', '/uploaded/155470557805411.jpg', null, null, '10.00', '198', null, '2019-02-28 10:23:03', null, null, '');
INSERT INTO `product` VALUES ('3', '鲟鱼肠', '2', '3', '1', '2', '300', '鲟鱼肠', '/images/tj.png', null, null, '50.00', '300', null, '2019-02-28 10:23:10', null, null, '');
INSERT INTO `product` VALUES ('4', '牛肉干', '2', '1', '1', '2', '400', '牛肉干', '/images/activity3.jpg', null, null, '49.00', '400', null, '2019-02-28 10:23:17', null, null, '');
INSERT INTO `product` VALUES ('5', '千层饼', '6', '2', '1', '2', '10', '千层饼', '/images/3.jpg', null, null, '52.00', '6', null, '2019-02-28 10:23:23', null, null, '');
INSERT INTO `product` VALUES ('6', '龙眼', '3', '3', '1', '3', '111', '龙眼', '/images/2.jpg', null, null, '20.00', '100', null, '2019-02-28 10:23:23', null, null, '');
INSERT INTO `product` VALUES ('7', '橙子', '1', '3', '2', '3', '0', '橙子', '/images/5.jpg', null, null, '5.00', '0', null, '2019-02-28 10:23:24', null, null, '');
INSERT INTO `product` VALUES ('8', '武式千层饼', '6', '1', '2', '2', '0', '武式千层饼', '/images/3.jpg', null, null, '58.00', '0', null, '2019-02-28 10:23:24', null, null, '');
INSERT INTO `product` VALUES ('10', '荔枝', '1', '1', '1', '3', '321', '荔枝', '/images/1.jpg', null, null, '10.00', '318', null, '2019-02-28 10:23:25', null, null, '');
INSERT INTO `product` VALUES ('16', '可可球', '2', '2', '1', '1', '100', '可可球', '/images/activity1.jpg', '2019-03-06 20:05:21', '2019-03-04 20:05:48', '78.00', '94', '2019-03-04 20:06:25', '2019-03-04 20:05:19', null, null, '');
INSERT INTO `product` VALUES ('17', '牛奶', '3', '2', '1', '1', '100', '牛奶', '/images/activity.jpg', '2019-03-04 08:46:36', '2019-06-27 08:46:39', '65.00', '96', '2019-03-06 08:46:59', '2019-03-06 08:46:00', null, null, '');
INSERT INTO `product` VALUES ('18', '牛肉干', '1', '2', '1', '2', '100', '牛肉干', '/images/activity3.jpg', '2019-03-05 10:55:00', '2019-03-22 10:55:05', '89.00', '92', '2019-03-06 10:55:12', '2019-03-06 10:54:43', null, null, '');
INSERT INTO `product` VALUES ('20', '菠萝', '1', '3', '1', '1', '100', '菠萝', '/images/activity2.jpg', '2019-02-26 10:56:12', '2019-03-14 10:56:17', '15.00', '100', '2019-03-05 10:56:25', '2019-03-06 10:56:49', null, null, '');
INSERT INTO `product` VALUES ('21', '坚果', '2', '2', '1', '2', '100', '坚果', '/uploaded/155436428753801.jpg', null, null, '20.00', '100', null, '2019-03-06 11:58:02', null, null, '');
INSERT INTO `product` VALUES ('22', '板栗', '2', '1', '1', '2', '100', '板栗', '/images/tj1.png', null, null, '17.00', '100', null, '2019-03-06 11:59:09', null, null, '');
INSERT INTO `product` VALUES ('23', '巧克力', '3', '1', '1', '2', '100', '巧克力', '/images/tj2.png', null, null, '100.00', '94', null, '2019-03-06 12:00:03', null, null, '');
INSERT INTO `product` VALUES ('25', '荔枝', '1', '1', '1', '3', '100', '荔枝', '/images/1.jpg', null, null, '10.00', '99', null, '2019-03-06 13:25:07', null, null, '');
INSERT INTO `product` VALUES ('26', '荔枝', '1', '1', '1', '3', '100', '荔枝', '/images/1.jpg', null, null, '12.00', '99', null, '2019-03-06 13:25:46', null, null, '');
INSERT INTO `product` VALUES ('27', '龙眼', '1', '1', '1', '3', '100', '龙眼', '/images/2.jpg', null, null, '13.00', '96', null, '2019-03-06 13:26:44', null, null, '');
INSERT INTO `product` VALUES ('30', '甜饼', '3', '2', '1', '3', '100', '甜饼', '/images/3.jpg', null, null, '65.00', '96', null, '2019-03-06 13:28:46', null, null, '');
INSERT INTO `product` VALUES ('31', '桂圆', '3', '3', '1', '3', '100', '桂圆', '/images/4.jpg', null, null, '5.00', '100', null, '2019-03-06 13:29:29', null, null, '');
INSERT INTO `product` VALUES ('36', '燕麦', '6', '3', '1', '4', '100', '燕麦', '/uploaded/155592171112810.jpg', null, null, '75.00', '99', null, '2019-04-22 16:28:32', '', null, '');
INSERT INTO `product` VALUES ('37', '粘糕', '7', '1', '1', '2', '100', '粘糕', '/uploaded/1555922061083scoll1.png', null, null, '64.00', '100', null, '2019-04-22 16:34:22', '', null, '');
INSERT INTO `product` VALUES ('39', '牛肉干', '3', '1', '1', '1', '123', '牛肉干', '/uploaded/1557476282788activity3.jpg', null, null, '56.00', '123', null, '2019-05-10 16:18:35', '', null, '');

-- ----------------------------
-- Table structure for returns
-- ----------------------------
DROP TABLE IF EXISTS `returns`;
CREATE TABLE `returns` (
  `chan_no` varchar(255) NOT NULL COMMENT '退货单编号',
  `cust_id` int(11) DEFAULT NULL COMMENT '客户id',
  `order_no` char(255) DEFAULT NULL COMMENT '订单编号',
  `deliv_date` varchar(255) DEFAULT NULL COMMENT '配送日期',
  `chan_reason` char(50) DEFAULT NULL COMMENT '退货原因',
  `prod_id` char(6) DEFAULT NULL COMMENT '商品编号',
  `qty` int(11) DEFAULT NULL COMMENT '商品数量',
  `status` char(8) DEFAULT '0' COMMENT '退款状态（0，申请中    1退款成功   2退款失败）',
  `flag` varchar(255) DEFAULT '1',
  `content` varchar(255) DEFAULT NULL COMMENT '退货说明',
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`chan_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品退货表';

-- ----------------------------
-- Records of returns
-- ----------------------------

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `order_no` int(255) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cust_id` int(11) DEFAULT NULL COMMENT '客户注册号(id)',
  `tot_amt` decimal(7,2) DEFAULT NULL COMMENT '订单总额',
  `order_date` datetime DEFAULT NULL COMMENT '订货日期',
  `invoice_no` char(20) DEFAULT NULL COMMENT '发票号码',
  `order_status` char(2) DEFAULT '0' COMMENT '订单状态(0.未付款 1.已付款，2正在退款， 3已退款,4退款失败)',
  `deliv_date` datetime DEFAULT NULL COMMENT '配送日期',
  `flag` varchar(255) DEFAULT '1' COMMENT '删除标志(1存在，0删除）',
  `extend2` varchar(255) DEFAULT NULL COMMENT '下单时间',
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单总表';

-- ----------------------------
-- Records of sales
-- ----------------------------

-- ----------------------------
-- Table structure for sale_item
-- ----------------------------
DROP TABLE IF EXISTS `sale_item`;
CREATE TABLE `sale_item` (
  `order_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `prod_id` int(11) DEFAULT NULL COMMENT '商品编号',
  `unit_price` decimal(7,2) DEFAULT NULL COMMENT '商品单价',
  `dis_price` decimal(7,2) DEFAULT NULL COMMENT '商品折后价',
  `qty` int(11) DEFAULT NULL COMMENT '商品数量',
  `order_date` datetime DEFAULT NULL COMMENT '订货日期',
  `invoice_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `extend2` varchar(255) DEFAULT NULL COMMENT '暂存商品图片',
  `extend3` varchar(255) DEFAULT NULL COMMENT '暂存商品描述',
  PRIMARY KEY (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单明细表';

-- ----------------------------
-- Records of sale_item
-- ----------------------------

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `shop_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车编号',
  `cust_id` int(6) DEFAULT NULL COMMENT '客户id',
  `prod_id` int(6) DEFAULT NULL COMMENT '商品id',
  `unit_price` decimal(7,2) DEFAULT NULL COMMENT '商品单价',
  `dis_price` decimal(9,2) DEFAULT NULL COMMENT '商品折后价',
  `qty` int(11) DEFAULT NULL COMMENT '商品数量',
  `buy` char(4) DEFAULT '0' COMMENT '是否购买',
  `pro_totamt` decimal(7,2) DEFAULT NULL COMMENT '商品总金额',
  `flag` varchar(255) DEFAULT '1' COMMENT '1是存在  2是删除',
  `image` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL COMMENT '暂存商品描述',
  PRIMARY KEY (`shop_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品暂存表';

-- ----------------------------
-- Records of shopcart
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `identity` varchar(255) DEFAULT NULL COMMENT '三员身份(安全员1、管理员2、审计员3)',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '用户登录名',
  `salt` varchar(255) DEFAULT NULL COMMENT '用户密码（加盐,md5之后）',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `login_time` bigint(20) DEFAULT '0' COMMENT '登录次数',
  `extend1` varchar(255) DEFAULT NULL,
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员表';

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('1', '1', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', '2j24H1SFCDjzgZdAnIej9A==', null, null, null, null, '0', null, null, null);

-- ----------------------------
-- Table structure for sys_authority_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority_question`;
CREATE TABLE `sys_authority_question` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `authority_id` int(30) DEFAULT NULL COMMENT '用户id',
  `authority_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `question1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '问题1',
  `question2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `question3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `answer1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '答案1',
  `answer2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `answer3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `flag` varchar(255) COLLATE utf8_bin DEFAULT '1',
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '三员身份(安全员1、管理员2、审计员3)',
  `updatetime` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `extend1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '拓展字段1',
  `extend2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `extend3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `extend4` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `extend5` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `extend6` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='管理员密保问题与答案表';

-- ----------------------------
-- Records of sys_authority_question
-- ----------------------------
INSERT INTO `sys_authority_question` VALUES ('1', '1', 'admin', '你的真实姓名', '你的工号', '你的手机号', '', null, null, '1', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名字',
  `sort` int(20) DEFAULT NULL COMMENT '菜单排序',
  `href` varchar(50) DEFAULT NULL COMMENT '菜单链接',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `target` varchar(50) DEFAULT NULL COMMENT '菜单目标',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '菜单父ID',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '菜单所有父ID',
  `status` tinyint(4) DEFAULT '1' COMMENT '菜单状态（启用1禁用2）',
  `type` tinyint(4) DEFAULT '1' COMMENT '菜单类型(1菜单2按钮）',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标记（1表示可用，0表示不可用，2表示待授予，3表示待撤回）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_btn` varchar(2) DEFAULT '0' COMMENT '默认为0  1代表按钮',
  `note` text COMMENT '备注',
  `extend1` varchar(255) DEFAULT NULL,
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `wh_no` int(5) NOT NULL AUTO_INCREMENT COMMENT '仓库编号',
  `wh_name` char(10) DEFAULT NULL COMMENT '仓库名称',
  `flag` varchar(255) DEFAULT '1',
  `extend2` varchar(255) DEFAULT NULL,
  `extend3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wh_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='仓库表(商品品牌表)';

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('1', '三只松鼠', '1', null, null);
INSERT INTO `warehouse` VALUES ('2', '百草味', '1', null, null);
INSERT INTO `warehouse` VALUES ('3', '良品铺子', '1', null, null);
