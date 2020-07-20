--创建自增数据库和数据库表
CREATE DATABASE `seqid`;
CREATE TABLE sequence_id ( id BIGINT ( 20 ) UNSIGNED NOT NULL auto_increment, VALUE CHAR ( 10 ) NOT NULL DEFAULT '', PRIMARY KEY ( id ) ) ENGINE = INNODB;
--多数据源数据库
--数据库一
CREATE DATABASE `multiseq1`;
CREATE TABLE sequence_id ( id BIGINT ( 20 ) UNSIGNED NOT NULL auto_increment, VALUE CHAR ( 10 ) NOT NULL DEFAULT '', PRIMARY KEY ( id ) ) ENGINE = INNODB;
set @@global.auto_increment_increment = 2;
set @@global.auto_increment_offset=1;
--数据库二
CREATE DATABASE `multiseq2`;
CREATE TABLE sequence_id ( id BIGINT ( 20 ) UNSIGNED NOT NULL auto_increment, VALUE CHAR ( 10 ) NOT NULL DEFAULT '', PRIMARY KEY ( id ) ) ENGINE = INNODB;
set @@global.auto_increment_increment = 2;
set @@global.auto_increment_offset=2;
--基于数据库号段模式
CREATE TABLE id_generator (
  id int(10) NOT NULL,
  max_id bigint(20) NOT NULL COMMENT '当前最大id',
  step int(20) NOT NULL COMMENT '号段的步长',
  biz_type    int(20) NOT NULL COMMENT '业务类型',
  version int(20) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE = INNODB;
