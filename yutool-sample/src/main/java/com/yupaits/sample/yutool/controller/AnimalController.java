package com.yupaits.sample.yutool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yupaits.sample.yutool.dto.AnimalDto;
import com.yupaits.sample.yutool.query.AnimalQuery;
import com.yupaits.sample.yutool.service.AnimalService;
import com.yupaits.sample.yutool.vo.AnimalVo;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.metadata.base.DtoWrapper;
import com.yupaits.yutool.metadata.base.VoWrapper;
import com.yupaits.yutool.orm.annotation.PageQueryDefault;
import com.yupaits.yutool.orm.support.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动物Controller
 * @author yupaits
 * @date 2020-05-13
 */
@RestController
@RequestMapping("/Animal")
@Api(tags = "动物接口")
public class AnimalController {
	private final AnimalService animalService;

	@Autowired
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@ApiOperation("获取动物分页信息")
	@GetMapping("/page")
	public Result<IPage<VoWrapper<AnimalVo>>> page(@PageQueryDefault PageQuery<AnimalQuery> pageQuery,
												   AnimalQuery query) throws BusinessException {
		pageQuery.setQuery(query);
		return animalService.page(pageQuery);
	}

	@ApiOperation("获取动物列表")
	@GetMapping("/list")
	public Result<List<VoWrapper<AnimalVo>>> list(AnimalQuery query) throws BusinessException {
		return animalService.list(query);
	}

	@ApiOperation("保存动物")
	@PostMapping("")
	public Result save(@RequestBody DtoWrapper<AnimalDto> dto) throws BusinessException {
		return animalService.save(dto);
	}

	@ApiOperation("获取动物详情")
	@GetMapping("/{id}")
	public Result<VoWrapper<AnimalVo>> details(@ApiParam(value = "动物ID", required = true) @PathVariable Long id) throws BusinessException {
		return animalService.details(id);
	}

	@ApiOperation("删除动物")
	@DeleteMapping("/{id}")
	public Result delete(@ApiParam(value = "动物ID", required = true) @PathVariable Long id) throws BusinessException {
		return animalService.deleteById(id);
	}
}