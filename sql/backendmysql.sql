drop table user;
create table user
(
    id         bigint primary key auto_increment comment '用户ID',
    nickName   varchar(255) comment '昵称',
    avatar     varchar(1024) comment '头像',
    email      varchar(255) comment '邮箱',
    password   varchar(255) comment '密码',
    integral   int comment '积分',
    createTime timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete   int       default 0 comment '是否删除'
);
drop table topicbank;

CREATE TABLE `topicbank`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `type`        int(1)       NOT NULL COMMENT '类型',
    `answer`      varchar(255) NOT NULL COMMENT '答案',
    `analysis`    text COMMENT '解析',
    `belongLevel` int(10)      NOT NULL COMMENT '所属关卡',
    createTime    timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime    timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete      int       default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='题库';


drop table userTopicbank;
CREATE TABLE `userTopicbank`
(
    `id`         bigint     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `userId`     bigint     NOT NULL COMMENT '用户ID',
    `questionId` bigint     NOT NULL COMMENT '题目ID',
    `status`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0-错误 1-正确',
    createTime   timestamp           default CURRENT_TIMESTAMP comment '创建时间',
    updateTime   timestamp           default CURRENT_TIMESTAMP comment '更新时间',
    isDelete     int                 default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '用户-题库表';



CREATE TABLE `animal`
(
    `id`                      bigint        NOT NULL COMMENT '动物编号',
    `name`                    varchar(50)   NOT NULL COMMENT '动物名称',
    `categoryId`              bigint        NOT NULL COMMENT '分类编号',
    `introduction`            varchar(1024) NOT NULL COMMENT '动物简介',
    `picture`                 varchar(1024) NOT NULL COMMENT '图片',
    `endangeredLevel`         varchar(255)  NOT NULL COMMENT '濒危等级',
    `morphologyDescription`   varchar(1024) NOT NULL COMMENT '形态描述',
    `habit`                   varchar(1024) NOT NULL COMMENT '生活习性',
    `geographicalEnvironment` varchar(255)  NOT NULL COMMENT '生活地理环境',
    `distributionRange`       varchar(1024) NOT NULL COMMENT '分布范围',
    `report`                  varchar(1024) NOT NULL COMMENT '各界报道',
    createTime                timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime                timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete                  int       default 0 comment '是否删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='动物';


create table `news`
(
    `id`          bigint not null comment '新闻编号',
    `name`        varchar(255) comment '新闻名称',
    `content`     text comment '内容',
    `releaseTime` datetime comment '发布时间',
    `coverImg`    varchar(1024) comment '封面图片',
    `newsContent` text comment '新闻内容',
    primary key (`id`),
    createTime    timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime    timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete      int       default 0 comment '是否删除'
) engine = InnoDB
  default charset = utf8mb4;


drop table document;
CREATE TABLE `document`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '文献id',
    `title`       varchar(255)  DEFAULT NULL COMMENT '标题',
    `animalId`    bigint        DEFAULT NULL COMMENT '动物id',
    `url`         varchar(1024) DEFAULT NULL COMMENT '文献url',
    `publishTime` datetime      DEFAULT NULL COMMENT '发布时间',
    `author`      varchar(255)  DEFAULT NULL COMMENT '作者',
    `periodical`  varchar(255)  DEFAULT NULL COMMENT '期刊',
    createTime    timestamp     default CURRENT_TIMESTAMP comment '创建时间',
    updateTime    timestamp     default CURRENT_TIMESTAMP comment '更新时间',
    isDelete      int           default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='文献表';


CREATE TABLE `payRecord`
(
    `id`        bigint      NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `userId`    bigint      NOT NULL COMMENT '用户id',
    `payNum`    varchar(50) NOT NULL COMMENT '支付编号',
    `payAmount` numeric     NOT NULL COMMENT '支付金额',
    createTime  timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime  timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete    int       default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='支付记录';



drop table exchange;
CREATE TABLE `exchange`
(
    `id`            bigint        NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`          varchar(255)  NOT NULL COMMENT '名称',
    `requiredScore` int(11)       NOT NULL COMMENT '所需积分',
    `picture`       varchar(1024) NOT NULL COMMENT '图片',
    createTime      timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime      timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete        int       default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='兑换';


drop table userExchange;
CREATE TABLE `userExchange`
(
    `id`       bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `userId`   bigint unsigned NOT NULL COMMENT '用户id',
    `goodsId`  bigint unsigned NOT NULL COMMENT '商品id',
    `address`  varchar(255)    NOT NULL COMMENT '地址',
    phone      varchar(20)     not null comment '电话',
    `status`   tinyint(4)      NOT NULL DEFAULT '0' COMMENT '邮寄状态 0——未邮寄 1——邮寄',
    createTime timestamp                default CURRENT_TIMESTAMP comment '创建时间',
    updateTime timestamp                default CURRENT_TIMESTAMP comment '更新时间',
    isDelete   int                      default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户兑换表';



use ead;
CREATE TABLE `posts` (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
                         `title` varchar(255) NOT NULL COMMENT '帖子标题',
                         `userId` int(11) NOT NULL COMMENT '发布者ID',
                         `content` text NOT NULL COMMENT '帖子内容',
                         `likeNum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数量',
                         `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1-已发布，2-草稿',
                         createTime timestamp                default CURRENT_TIMESTAMP comment '创建时间',
                         updateTime timestamp                default CURRENT_TIMESTAMP comment '更新时间',
                         isDelete   int                      default 0 comment '是否删除',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子表';




drop table posts;
CREATE TABLE `posts`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
    `title`    varchar(255) NOT NULL COMMENT '帖子标题',
    `userId`   int(11)      NOT NULL COMMENT '发布者ID',
    `content`  text         NOT NULL COMMENT '帖子内容',
    `likeNum`  int(11)      NOT NULL DEFAULT '0' COMMENT '点赞数量',
    `status`   tinyint(1)   NOT NULL DEFAULT '1' COMMENT '状态：1-已发布，2-草稿',
    createTime timestamp             default CURRENT_TIMESTAMP comment '创建时间',
    updateTime timestamp             default CURRENT_TIMESTAMP comment '更新时间',
    isDelete   int                   default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='帖子表';


CREATE TABLE IF NOT EXISTS `comment`
(
    `id`       bigint NOT NULL AUTO_INCREMENT COMMENT '评论表id',
    `user_id`  bigint NOT NULL COMMENT '用户id',
    `post_id`  bigint NOT NULL COMMENT '帖子id',
    `content`  text      DEFAULT NULL COMMENT '评论内容',
    createTime timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete   int       default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='评论表';


CREATE TABLE `liketable`
(
    `id`       bigint NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
    `userId`   bigint NOT NULL COMMENT '用户ID',
    `postId`   bigint NOT NULL COMMENT '帖子ID',
    createTime timestamp default CURRENT_TIMESTAMP comment '创建时间',
    updateTime timestamp default CURRENT_TIMESTAMP comment '更新时间',
    isDelete   int       default 0 comment '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='点赞表';







