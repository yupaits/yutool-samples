package com.yupaits.sample.yutool.jpa.entity;

import com.yupaits.yutool.orm.jpa.base.BaseJpaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author ts495
 * @date 2020/11/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "person")
public class Person extends BaseJpaEntity<Long, Person> {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 所属部门ID
     */
    private Long orgId;

    /**
     * 删除标记
     */
    private Boolean deleted;

    /**
     * 版本号
     */
    @Version
    private Long version;

}
