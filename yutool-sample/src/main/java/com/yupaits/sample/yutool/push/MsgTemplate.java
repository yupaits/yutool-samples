package com.yupaits.sample.yutool.push;

import com.yupaits.yutool.push.support.IMsgTemplate;
import lombok.Getter;

/**
 * 消息模板枚举
 * @author yupaits
 * @date 2019/8/9
 */
@Getter
public enum MsgTemplate implements IMsgTemplate {
    /**
     * 邮件测试模板
     */
    EMAIL_TEMPLATE(4, "push", "email.ftl"),
    /**
     * Web网页消息测试模板
     */
    WEB_MSG_TEMPLATE(8, "push", "webmsg.ftl"),
    /**
     * APP通知测试模板
     */
    NOTIFICATION_TEMPLATE(12, "push", "notification.ftl"),
    /**
     * 短信测试模板
     */
    SMS_TEMPLATE(16, "push", "sms.ftl"),
    /**
     * IM消息测试模板
     */
    IM_TEMPLATE(20, "push", "im.ftl");

    private int code;
    private String templateDir;
    private String templateFilename;

    MsgTemplate(int code, String templateDir, String templateFilename) {
        this.code = code;
        this.templateDir = templateDir;
        this.templateFilename = templateFilename;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getTemplateFile() {
        return this.templateDir + this.templateFilename;
    }
}
