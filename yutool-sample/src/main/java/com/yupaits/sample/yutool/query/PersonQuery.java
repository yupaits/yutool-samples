package com.yupaits.sample.yutool.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yupaits.sample.yutool.model.Person;
import com.yupaits.yutool.orm.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yupaits
 * @date 2020/1/6
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "人类查询对象")
public class PersonQuery extends BaseQuery<Person> {
    private static final long serialVersionUID = 1L;

    @ApiParam("姓名")
    private String name;

    @Override
    public Wrapper<Person> buildNewQuery() {
        LambdaQueryWrapper<Person> queryWrapper = Wrappers.lambdaQuery();
        this.buildQuery(queryWrapper);
        return queryWrapper;
    }

    @Override
    public void buildQuery(Wrapper<Person> queryWrapper) {
        LambdaQueryWrapper<Person> wrapper = (LambdaQueryWrapper<Person>) queryWrapper;
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq(Person::getName, name);
        }
    }
}
