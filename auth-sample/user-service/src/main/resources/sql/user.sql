DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT(19) NOT NULL COMMENT '用户ID',
    `username` VARCHAR(40) DEFAULT NULL COMMENT '用户名',
    `password` VARCHAR(68) DEFAULT NULL COMMENT '密码密文',
    `salt` VARCHAR(20) DEFAULT NULL COMMENT '加盐',
    `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `created_by` VARCHAR(19) DEFAULT NULL COMMENT '创建ID',
    `last_modified_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    `last_modified_by` VARCHAR(19) DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` BIGINT(19) NOT NULL COMMENT '角色ID',
    `role` VARCHAR(100) DEFAULT NULL COMMENT '角色标识',
    `role_name` VARCHAR(40) DEFAULT NULL COMMENT '角色名称',
    `description` VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
    `is_enabled` BIT(1) DEFAULT NULL COMMENT '启用标记',
    `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `created_by` VARCHAR(19) DEFAULT NULL COMMENT '创建ID',
    `last_modified_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    `last_modified_by` VARCHAR(19) DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '角色';

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
    `id` BIGINT(19) NOT NULL COMMENT '用户角色关联ID',
    `user_id` VARCHAR(100) DEFAULT NULL COMMENT '用户ID',
    `role_id` VARCHAR(40) DEFAULT NULL COMMENT '角色ID',
    `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `created_by` VARCHAR(19) DEFAULT NULL COMMENT '创建ID',
    `last_modified_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    `last_modified_by` VARCHAR(19) DEFAULT NULL COMMENT '更新人ID',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户角色关联信息';