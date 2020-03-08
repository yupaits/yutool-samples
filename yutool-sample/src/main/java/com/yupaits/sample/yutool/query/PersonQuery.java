package com.yupaits.sample.yutool.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupaits.sample.yutool.model.Person;
import com.yupaits.yutool.commons.exception.BusinessException;
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
public class PersonQuery implements BaseQuery<Person> {
    private static final long serialVersionUID = 1L;

    @ApiParam("姓名")
    private String name;

    @Override
    public void buildLambdaQuery(LambdaQueryWrapper<Person> queryWrapper) throws BusinessException {
        queryWrapper.eq(StringUtils.isNotBlank(name), Person::getName, name);
    }
}
