DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
    `id` BIGINT(19) NOT NULL COMMENT '文章ID',
    `title` VARCHAR(255) DEFAULT NULL COMMENT '文章标题',
    `content` TEXT DEFAULT NULL COMMENT '文章内容',
    `tags` VARCHAR(255) DEFAULT NULL COMMENT '文章标签',
    `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `created_by` VARCHAR(19) DEFAULT NULL COMMENT '创建ID',
    `last_modified_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    `last_modified_by` VARCHAR(19) DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章';