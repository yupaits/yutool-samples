package com.yupaits.sample.yutool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupaits.sample.yutool.model.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人类 Mapper
 * @author yupaits
 * @date 2019-07-30
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}