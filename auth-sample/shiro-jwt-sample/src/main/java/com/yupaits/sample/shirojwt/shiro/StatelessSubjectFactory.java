package com.yupaits.sample.shirojwt.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 基于Jwt的SubjectFactory
 * @author yupaits
 * @date 2019/8/21
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        super.createSubject(context).getSession(false);
        return super.createSubject(context);
    }
}
