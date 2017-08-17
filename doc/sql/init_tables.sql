CREATE TABLE `zx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` char(64) NOT NULL,
  `salt` char(6) NOT NULL,
  `frozen` int(11) NOT NULL DEFAULT '0' COMMENT '冻结时间，单位秒。0:不冻结, -1：永远冻结。',
  `last_login_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '上次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS zx_account;
CREATE TABLE zx_account(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL COMMENT '所属用户',
  nickname VARCHAR(32) NOT NULL DEFAULT '' COMMENT '昵称',
  phone VARCHAR(16) NOT NULL DEFAULT '' COMMENT '手机号',
  email VARCHAR(64) NOT NULL DEFAULT '' COMMENT '邮箱',
  create_time DATETIME DEFAULT NULL,
  update_time DATETIME DEFAULT NULL,
  INDEX idx_user(user_id)
) COMMENT='账户表';

DROP TABLE IF EXISTS jb_user_coin;
CREATE TABLE jb_user_coin(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  coin VARCHAR(16) NOT NULL,
  priority INT NOT NULL DEFAULT 0 COMMENT '优先级，越大优先级越高',
  INDEX idx_user_coin(user_id, coin)
);