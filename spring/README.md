# 项目运行前置条件
- 创建数据库 spring
- 用户名 admin 密码 root 可以在代码中更改为自己的数据库连接
- 执行以下sql
```sql
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `cardNo` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '银行卡号',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `money` int(255) DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`cardNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `account` VALUES ('6029621011000', '李大雷', 8800);
INSERT INTO `account` VALUES ('6029621011001', '韩梅梅', 11200);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `user` VALUES (34, '小名');
INSERT INTO `user` VALUES (35, '小红');
INSERT INTO `user` VALUES (36, '晓明');
INSERT INTO `user` VALUES (37, '小明');
INSERT INTO `user` VALUES (38, '张三');
INSERT INTO `user` VALUES (39, '李四');
INSERT INTO `user` VALUES (40, '小佩');
INSERT INTO `user` VALUES (41, '轩宇');
INSERT INTO `user` VALUES (42, '逍遥');
INSERT INTO `user` VALUES (43, '令狐冲');
INSERT INTO `user` VALUES (44, '小龙女');
INSERT INTO `user` VALUES (45, '杨过');
INSERT INTO `user` VALUES (46, '黄蓉');
INSERT INTO `user` VALUES (47, '名导');
INSERT INTO `user` VALUES (48, 'jack');

```