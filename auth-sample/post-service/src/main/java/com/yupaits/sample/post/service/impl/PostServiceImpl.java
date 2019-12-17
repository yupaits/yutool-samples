package com.yupaits.sample.post.service.impl;

import com.yupaits.sample.post.mapper.PostMapper;
import com.yupaits.sample.post.model.Post;
import com.yupaits.sample.post.service.PostService;
import com.yupaits.sample.post.vo.PostVo;
import com.yupaits.yutool.commons.service.OptService;
import com.yupaits.yutool.orm.base.BaseResultServiceImpl;
import com.yupaits.yutool.orm.support.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Service
public class PostServiceImpl extends BaseResultServiceImpl<Long, Post, PostMapper> implements PostService {
    @Autowired
    protected PostServiceImpl(OptService optService, AuditLogger auditLogger) {
        super(Post.class, optService, auditLogger);
    }

    @Override
    public void setDefaultVoConfig() {
        setDefaultVoClass(PostVo.class);
    }

    @Override
    public void setDefaultModelBuilder() {

    }
}
