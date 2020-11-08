package com.yupaits.sample.yutool.jpa.controller;

import com.yupaits.sample.yutool.jpa.query.PersonQuery;
import com.yupaits.sample.yutool.jpa.service.PersonService;
import com.yupaits.sample.yutool.jpa.vo.PersonVo;
import com.yupaits.yutool.orm.jpa.support.IAggregatePage;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.orm.commons.annotation.AggregateDefault;
import com.yupaits.yutool.orm.commons.support.Aggregates;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ts495
 * @date 2020/11/8
 */
@RestController
@RequestMapping("/person")
@Api(tags = "人类接口")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation("带聚合信息的分页查询")
    @GetMapping("/page")
    public IAggregatePage<PersonVo> page(@PageableDefault Pageable pageable, @AggregateDefault Aggregates aggregates, PersonQuery personQuery) throws BusinessException {
        return personService.aggregatePage(pageable, aggregates, personQuery);
    }
}
