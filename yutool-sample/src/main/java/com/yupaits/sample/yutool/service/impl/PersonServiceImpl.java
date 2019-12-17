package com.yupaits.sample.yutool.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yupaits.sample.yutool.mapper.PersonMapper;
import com.yupaits.sample.yutool.model.Person;
import com.yupaits.sample.yutool.service.PersonService;
import com.yupaits.sample.yutool.vo.PersonVo;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.base.BaseResultServiceImpl;
import com.yupaits.yutool.orm.support.AuditLogger;
import com.yupaits.yutool.orm.support.VoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 人类 Service实现
 * @author yupaits
 * @date 2019-07-30
 */
@Service
@DS("#header.tenantName")
public class PersonServiceImpl extends BaseResultServiceImpl<Long, Person, PersonMapper> implements PersonService {
    @Autowired
    public PersonServiceImpl(OptService optService, AuditLogger auditLogger) {
        super(Person.class, optService, auditLogger);
    }

    @Override
    public void setDefaultVoConfig() {
        setDefaultVoClass(PersonVo.class);
        setDefaultVoBuilder(new VoBuilder<PersonVo, Person>() {
            @Override
            public void buildVo(PersonVo vo, Person model) {
            }
        });
    }

    @Override
    public void setDefaultModelBuilder() {
    }
}