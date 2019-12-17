package com.yupaits.sample.yutool.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupaits.sample.yutool.dto.PersonCreate;
import com.yupaits.sample.yutool.dto.PersonDto;
import com.yupaits.sample.yutool.dto.PersonUpdate;
import com.yupaits.sample.yutool.model.Person;
import com.yupaits.sample.yutool.service.PersonService;
import com.yupaits.sample.yutool.vo.HumanVo;
import com.yupaits.sample.yutool.vo.PersonVo;
import com.yupaits.yutool.cache.annotation.EvictCache;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.orm.support.AggregateProps;
import com.yupaits.yutool.orm.support.PageQuery;
import com.yupaits.yutool.orm.support.VoBuilder;
import com.yupaits.yutool.orm.support.VoProps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 人类 Controller
 * @author yupaits
 * @date 2019-07-30
 */
//@EnableCache
@RestController
@RequestMapping("/person")
@Api(tags = "人类接口")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation("获取人类分页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10", dataType = "int", paramType = "query")})
    @PostMapping("/page")
    public Result<IPage<PersonVo>> getPersonPage(@RequestParam(required = false, defaultValue = "1") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 @RequestBody(required = false) PageQuery pageQuery) {
        Page<Person> pager = new Page<>(page, size);
        pageQuery.collate();
        if (CollectionUtils.isNotEmpty(pageQuery.getOrders())) {
            pager.setOrders(pageQuery.getOrders());
        }
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        if (MapUtils.isNotEmpty(pageQuery.getQuery())) {
            pageQuery.getQuery().forEach((key, value) -> {
                if (StringUtils.equals(key, "name")) {
                    wrapper.eq("name", value);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(pageQuery.getAggregates())) {
            AggregateProps aggregateProps = new AggregateProps();
            aggregateProps.setAggregates(pageQuery.getAggregates());
            return personService.resultPage(pager, wrapper, aggregateProps);
        }
        return personService.resultPage(pager, wrapper);
    }

    @ApiOperation("获取人类列表")
    @PostMapping("/list")
    public Result<List<PersonVo>> getPersonList(@RequestBody(required = false) Map<String, Object> query) {
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        if (MapUtils.isNotEmpty(query)) {
            query.forEach((key, value) -> {
                if (StringUtils.equals(key, "name")) {
                    wrapper.eq("name", value);
                }
            });
        }
        return personService.resultList(wrapper);
    }

    @ApiOperation("根据ID获取人类")
    @GetMapping("/{personId}")
    public Result<PersonVo> getPersonById(@PathVariable Long personId) throws BusinessException {
        //不支持数据权限过滤
//        return personService.resultById(personId);
        //支持数据权限过滤
        return personService.resultOne(new QueryWrapper<Person>().eq("id", personId));
    }

    @EvictCache
    @ApiOperation("添加人类")
    @PostMapping("")
    public Result addPerson(@RequestBody PersonCreate personCreate) throws BusinessException {
        //动态设置Vo类
        personService.setVoClass(HumanVo.class);
        personService.setVoBuilder(new VoBuilder<HumanVo, Person>() {
            @Override
            public void buildVo(HumanVo vo, Person model) {
                vo.setDescription("name: " + model.getName() + ", age: " + model.getAge() + ", gender: " +
                        (model.getGender() != null && model.getGender() == 0 ? "男" : "女"));
            }
        });
        //保存Dto
//        return personService.resultSaveDto(personCreate);
        //保存Dto并返回对应的Vo信息
//        return personService.resultSaveDtoAndReturn(personCreate);
        //保存Dto并按需返回保存的Vo信息
        return personService.resultSaveDtoAndReturn(personCreate, VoProps.with(true, false));
    }

    @EvictCache
    @ApiOperation("更新人类")
    @PutMapping("/{personId}")
    public Result updatePerson(@RequestBody PersonUpdate personUpdate) throws BusinessException {
        //不支持数据权限过滤
        return personService.resultSaveDto(personUpdate);
        //支持数据权限过滤
//        return personService.resultUpdateDto(personUpdate, new QueryWrapper<>());
    }

    @EvictCache
    @ApiOperation("批量保存人类")
    @PostMapping("/batch-save")
    public Result batchSavePerson(@RequestBody List<PersonDto> persons) throws BusinessException {
        //不支持数据权限过滤
//        return personService.resultSaveBatchDto(persons);
        //批量保存Dto并返回对应的Vo列表
        return personService.resultSaveBatchDtoAndReturn(persons);

    }

    @EvictCache
    @ApiOperation("批量更新人类")
    @PutMapping("/batch-update")
    public Result batchUpdatePerson(@RequestBody List<PersonUpdate> persons) throws BusinessException {
        //不支持数据权限过滤
//        return personService.resultSaveBatchDto(persons);
        //支持数据权限过滤
        return personService.resultUpdateBatchDto(persons, new QueryWrapper<>());
    }

    @EvictCache
    @ApiOperation("删除人类")
    @DeleteMapping("/{personId}")
    public Result deletePerson(@PathVariable Long personId) {
        //不支持数据权限过滤
        return personService.resultDeleteById(personId);
        //支持数据权限过滤
//        return personService.resultDelete(new QueryWrapper<Person>().eq("id", personId));
    }

    @EvictCache
    @ApiOperation("批量删除人类")
    @PutMapping("/batch-delete")
    public Result batchDeletePerson(@RequestBody List<Long> personIds) {
        //不支持数据权限过滤
        return personService.resultDeleteBatchByIds(personIds);
        //支持数据权限过滤
//        return personService.resultDelete(new QueryWrapper<Person>().in("id", personIds));
    }
}
