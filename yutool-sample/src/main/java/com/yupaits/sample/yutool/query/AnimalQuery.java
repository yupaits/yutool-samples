package com.yupaits.sample.yutool.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yupaits.sample.yutool.entity.Animal;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.orm.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 动物查询对象
 * @author yupaits
 * @date 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "动物查询对象")
public class AnimalQuery implements BaseQuery<Animal> {
	private static final long serialVersionUID = 1L;

	@ApiParam(value = "查找关键字")
	private String keyword;

	@Override
	public void buildLambdaQuery(LambdaQueryWrapper<Animal> queryWrapper) throws BusinessException {
		queryWrapper.like(StringUtils.isNotBlank(keyword), Animal::getNickname, keyword);
	}
}