package com.yupaits.sample.yutool.controller;

import com.yupaits.yutool.push.core.PushTemplate;
import com.yupaits.yutool.push.exception.HandleReplyException;
import com.yupaits.yutool.push.model.reply.SmsReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/8/13
 */
@RestController
@RequestMapping("/sms/notify")
@Api(tags = "短信收发通知回调")
public class SmsNotifyController {
    private static final String SUCCESS = "SUCCESS";

    private final PushTemplate pushTemplate;

    @Autowired
    public SmsNotifyController(PushTemplate pushTemplate) {
        this.pushTemplate = pushTemplate;
    }

    @ApiOperation("短信收发通知")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE)
    public String smsNotify(@RequestBody String smsNotifyStr) throws HandleReplyException {
        SmsReply smsReply = SmsReply.fromXml(smsNotifyStr);
        pushTemplate.onReply(smsReply);
        return SUCCESS;
    }
}
