package com.yupaits.sample.yutool.service.meta;

import com.google.common.collect.Lists;
import com.yupaits.yutool.metadata.mapper.MetaFieldSecureMapper;
import com.yupaits.yutool.metadata.support.obj.AbstractObjInfoFilter;
import com.yupaits.yutool.metadata.support.obj.ObjInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 对象信息过滤器
 * @author yupaits
 * @date 2020/5/13
 */
@Component
public class ObjInfoFilter extends AbstractObjInfoFilter {

    @Autowired
    public ObjInfoFilter(ObjInfoManager objInfoManager, MetaFieldSecureMapper metaFieldSecureMapper) {
        super(objInfoManager, metaFieldSecureMapper);
    }

    @Override
    public List<Long> grantedRoleIds() {
        return Lists.newArrayList();
    }
}
