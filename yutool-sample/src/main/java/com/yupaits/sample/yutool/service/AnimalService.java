package com.yupaits.sample.yutool.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.sample.yutool.dto.AnimalDto;
import com.yupaits.sample.yutool.query.AnimalQuery;
import com.yupaits.sample.yutool.vo.AnimalVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.metadata.base.DtoWrapper;
import com.yupaits.yutool.metadata.base.VoWrapper;
import com.yupaits.yutool.orm.support.PageQuery;

import java.util.List;

/**
 * 动物Service
 * @author yupaits
 * @date 2020-05-13
 */
public interface AnimalService {
	
  	/**
     * 获取分页数据
	 */
  	Result<IPage<VoWrapper<AnimalVo>>> page(PageQuery<AnimalQuery> pageQuery) throws BusinessException;
	

  	/**
     * 根据Query对象获取Vo列表
	 */
  	Result<List<VoWrapper<AnimalVo>>> list(AnimalQuery query) throws BusinessException;
  
  	/**
     * 根据ID获取详情Vo
	 */
  	Result<VoWrapper<AnimalVo>> details(Long id) throws BusinessException;
  	
  
  	/**
     * 保存Dto对象
	 */
  	Result save(DtoWrapper<AnimalDto> dtoWrap) throws BusinessException;
  
  	/**
     * 根据ID删除
	 */
  	Result deleteById(Long id) throws BusinessException;
}