package com.yupaits.sample.yutool.jpa.query;

import com.yupaits.sample.yutool.jpa.entity.Person;
import com.yupaits.yutool.orm.jpa.base.BaseJpaQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author ts495
 * @date 2020/11/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "人类查询对象")
public class PersonQuery implements BaseJpaQuery<Person> {
    private static final long serialVersionUID = 1L;

    @ApiParam("姓名")
    private String name;

    @Override
    public Specification<Person> toSpec() {
        return (Specification<Person>) (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotBlank(name)) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
