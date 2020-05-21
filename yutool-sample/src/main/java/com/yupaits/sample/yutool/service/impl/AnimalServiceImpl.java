package com.yupaits.sample.yutool.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.sample.yutool.dto.AnimalDto;
import com.yupaits.sample.yutool.entity.Animal;
import com.yupaits.sample.yutool.mapper.AnimalMapper;
import com.yupaits.sample.yutool.query.AnimalQuery;
import com.yupaits.sample.yutool.service.AnimalService;
import com.yupaits.sample.yutool.vo.AnimalVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.metadata.base.BaseMetaResultServiceImpl;
import com.yupaits.yutool.metadata.base.DtoWrapper;
import com.yupaits.yutool.metadata.base.VoWrapper;
import com.yupaits.yutool.metadata.service.MetaFieldDataService;
import com.yupaits.yutool.metadata.support.obj.AbstractObjInfoFilter;
import com.yupaits.yutool.metadata.support.obj.ObjInfoManager;
import com.yupaits.yutool.metadata.support.obj.ObjValidator;
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
@Service("animalService")
public class AnimalServiceImpl extends BaseMetaResultServiceImpl<Long, Animal, AnimalMapper> implements AnimalService {

	@Autowired
	protected AnimalServiceImpl(OptService optService, AuditLogger auditLogger, ObjInfoManager objInfoManager,
								ObjValidator objValidator, AbstractObjInfoFilter objInfoFilter, MetaFieldDataService metaFieldDataService) {
		super(Animal.class, optService, auditLogger, objInfoManager, objValidator, objInfoFilter, metaFieldDataService);
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
	public Result<IPage<VoWrapper<AnimalVo>>> page(PageQuery<AnimalQuery> pageQuery) throws BusinessException {
		return resultPageMeta(pageQuery);
	}

	@Override
	public Result<List<VoWrapper<AnimalVo>>> list(AnimalQuery query) throws BusinessException {
		return resultListMeta(query.buildNewLambdaQuery());
	}

	@Override
	public Result<VoWrapper<AnimalVo>> details(Long id) throws BusinessException {
		return resultMetaById(id);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result save(DtoWrapper<AnimalDto> dtoWrap) throws BusinessException {
		return resultSaveMetaDto(dtoWrap);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result deleteById(Long id) {
		return resultDeleteMetaById(id);
	}
}