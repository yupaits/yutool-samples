package com.yupaits.sample.yutool.jpa.service.impl;

import com.yupaits.sample.yutool.jpa.entity.Person;
import com.yupaits.sample.yutool.jpa.query.PersonQuery;
import com.yupaits.sample.yutool.jpa.repository.PersonRepository;
import com.yupaits.sample.yutool.jpa.service.PersonService;
import com.yupaits.sample.yutool.jpa.vo.PersonVo;
import com.yupaits.yutool.orm.jpa.base.JpaServiceImpl;
import com.yupaits.yutool.orm.jpa.support.IAggregatePage;
import com.yupaits.yutool.commons.exception.BusinessException;
import com.yupaits.yutool.orm.commons.support.Aggregates;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author ts495
 * @date 2020/11/8
 */
@Service("personService")
public class PersonServiceImpl extends JpaServiceImpl<Long, Person, PersonRepository> implements PersonService {
    protected PersonServiceImpl() {
        super(Person.class);
    }

    @Override
    public void setDefaultVoConfig() {
        setDefaultVoClass(PersonVo.class);
    }

    @Override
    public void setDefaultEntityBuilder() {

    }

    @Override
    public IAggregatePage<PersonVo> aggregatePage(Pageable pageable, Aggregates aggregates, PersonQuery personQuery) throws BusinessException {
        Specification<Person> spec = personQuery.toSpec();
        return pageVoAggregate(pageVo(spec, pageable), spec, aggregates);
    }
}
