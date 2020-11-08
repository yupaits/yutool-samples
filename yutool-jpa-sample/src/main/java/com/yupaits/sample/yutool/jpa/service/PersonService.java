package com.yupaits.sample.yutool.jpa.service;

import com.yupaits.sample.yutool.jpa.query.PersonQuery;
import com.yupaits.sample.yutool.jpa.vo.PersonVo;
import com.yupaits.yutool.orm.jpa.support.IAggregatePage;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.orm.commons.support.Aggregates;
import org.springframework.data.domain.Pageable;

/**
 * @author ts495
 * @date 2020/11/8
 */
public interface PersonService {
    IAggregatePage<PersonVo> aggregatePage(Pageable pageable, Aggregates aggregates, PersonQuery personQuery) throws BusinessException;
}
