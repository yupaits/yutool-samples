package com.yupaits.sample.yutool.jpa.repository;

import com.yupaits.sample.yutool.jpa.entity.Person;
import com.yupaits.yutool.orm.jpa.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ts495
 * @date 2020/11/8
 */
@Repository
public interface PersonRepository extends BaseRepository<Long, Person> {
}
