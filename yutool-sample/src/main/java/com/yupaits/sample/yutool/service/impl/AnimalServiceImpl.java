package com.yupaits.sample.yutool.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.sample.yutool.mapper.AnimalMapper;
import com.yupaits.sample.yutool.dto.AnimalDto;
import com.yupaits.sample.yutool.entity.Animal;
import com.yupaits.sample.yutool.query.AnimalQuery;
import com.yupaits.sample.yutool.service.AnimalService;
import com.yupaits.sample.yutool.vo.AnimalVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.base.BaseResultServiceImpl;
import com.yupaits.yutool.orm.support.AuditLogger;
import com.yupaits.yutool.orm.support.PageQuery;
import com.yupaits.yutool.orm.support.VoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 动物Service实现
 * @author yupaits
 * @date 2020-05-13
 */
@Service
public class AnimalServiceImpl extends BaseResultServiceImpl<Long, Animal, AnimalMapper> implements AnimalService {

	@Autowired
	public AnimalServiceImpl(OptService optService, AuditLogger auditLogger) {
		super(Animal.class, optService, auditLogger);
	}

	@Override
	public void setDefaultVoConfig() {
		setDefaultVoClass(AnimalVo.class);
		setDefaultVoBuilder((VoBuilder<AnimalVo, Animal>) (vo, model) -> {

		});
	}

	@Override
	public void setDefaultModelBuilder() {
	}

	@Override
	public Result<IPage<AnimalVo>> page(PageQuery<AnimalQuery> pageQuery) throws BusinessException {
		return resultPage(pageQuery);
	}

	@Override
	public Result<List<AnimalVo>> list(AnimalQuery query) throws BusinessException {
		return resultList(query.buildNewLambdaQuery());
	}

	@Override
	public Result<AnimalVo> details(Long id) throws BusinessException {
		return resultById(id);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result save(AnimalDto dto) throws BusinessException {
		return resultSaveDto(dto);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result deleteById(Long id) {
		return resultDeleteById(id);
	}
}