package com.yupaits.sample.yutool.controller;

import com.google.common.collect.Lists;
import com.yupaits.sample.yutool.mq.SampleQueue;
import com.yupaits.sample.yutool.push.MsgTemplate;
import com.yupaits.yutool.commons.result.Result;
import com.yupaits.yutool.commons.result.ResultWrapper;
import com.yupaits.yutool.mq.core.RetryableSender;
import com.yupaits.yutool.mq.exception.MqRetryException;
import com.yupaits.yutool.mq.support.RetryProps;
import com.yupaits.yutool.mq.support.RetryStrategy;
import com.yupaits.yutool.push.core.PushTemplate;
import com.yupaits.yutool.push.exception.PushException;
import com.yupaits.yutool.push.model.msg.*;
import com.yupaits.yutool.push.support.PushProps;
import com.yupaits.yutool.push.support.PushType;
import com.yupaits.yutool.push.support.im.ImMsgType;
import com.yupaits.yutool.push.support.im.ImSendType;
import com.yupaits.yutool.push.support.im.yunxin.AccIdCreate;
import com.yupaits.yutool.push.support.im.yunxin.YunxinProvider;
import com.yupaits.yutool.push.support.notification.NotificationType;
import com.yupaits.yutool.push.support.webmsg.GlobalAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yupaits
 * @date 2019/8/9
 */
@Slf4j
@RestController
@RequestMapping("/push")
@Api(tags = "推送测试接口")
public class PushController {

    private final PushTemplate pushTemplate;
    private final YunxinProvider yunxinProvider;
    private final RetryableSender retryableSender;

    @Autowired
    public PushController(PushTemplate pushTemplate, YunxinProvider yunxinProvider, RetryableSender retryableSender) {
        this.pushTemplate = pushTemplate;
        this.yunxinProvider = yunxinProvider;
        this.retryableSender = retryableSender;
    }

    @ApiOperation("邮件推送测试")
    @GetMapping("/email")
    public Result pushEmail() throws PushException {
        EmailMsg emailMsg = new EmailMsg().setMsgTemplate(MsgTemplate.EMAIL_TEMPLATE)
                .setFrom("yupaits@163.com")
                .putParam("username", "yupaits")
                .setSubject("这是一封测试邮件");
        MultiValueMap<PushType, String> receivers = new LinkedMultiValueMap<>();
        receivers.addAll(PushType.EMAIL, Lists.newArrayList("yupaits@example.com", "ts495606653@hotmail.com"));
        //发送邮件
        pushTemplate.push(emailMsg, PushProps.builder()
                .receivers(receivers)
                .build());
        //延迟发送邮件
        pushTemplate.push(emailMsg, PushProps.builder()
                .receivers(receivers)
                .delayed(true)
                .delayMillis(3000L)
                .build());
        return ResultWrapper.success();
    }

    @ApiOperation("Web网页消息推送测试")
    @GetMapping("/webmsg")
    public Result pushWebMsg() throws PushException {
        WebMsg webMsg = new WebMsg().setMsgTemplate(MsgTemplate.WEB_MSG_TEMPLATE)
                .setTitle("Web网页消息测试")
                .setAction(GlobalAction.NOTICE);
        MultiValueMap<PushType, String> receivers = new LinkedMultiValueMap<>();
        receivers.add(PushType.WEB_MSG, "123456");
        //推送Web网页消息
        pushTemplate.push(webMsg, PushProps.builder()
                .receivers(receivers)
                .build());
        //延迟推送Web网页消息
        webMsg.setTitle("Web网页延迟消息测试");
        pushTemplate.push(webMsg, PushProps.builder()
                .receivers(receivers)
                .delayed(true)
                .delayMillis(3000L)
                .build());
        return ResultWrapper.success();
    }

    @ApiOperation("APP通知推送测试")
    @GetMapping("/notification")
    public Result pushNotification() throws PushException {
        Notification notification = new Notification().setMsgTemplate(MsgTemplate.NOTIFICATION_TEMPLATE)
                .setType(NotificationType.NOTIFY)
                .setTitle("APP测试通知");
        MultiValueMap<PushType, String> receivers = new LinkedMultiValueMap<>();
        receivers.add(PushType.NOTIFICATION, "user_001");
        //推送APP通知
        pushTemplate.push(notification, PushProps.builder()
                .receivers(receivers)
                .build());
        //延迟推送APP通知
        pushTemplate.push(notification, PushProps.builder()
                .receivers(receivers)
                .delayed(true)
                .delayMillis(3000L)
                .build());
        return ResultWrapper.success();
    }

    @ApiOperation("短信推送测试")
    @GetMapping("/sms")
    public Result pushSms() throws PushException {
        SmsMsg smsMsg = new SmsMsg().setMsgTemplate(MsgTemplate.SMS_TEMPLATE)
                .putParam("username", "yupaits");
        MultiValueMap<PushType, String> receivers = new LinkedMultiValueMap<>();
        receivers.add(PushType.SMS, "13866668888");
        //推送短信
        pushTemplate.push(smsMsg, PushProps.builder()
                .receivers(receivers)
                .build());
        //延迟推送短信
        pushTemplate.push(smsMsg, PushProps.builder()
                .receivers(receivers)
                .delayed(true)
                .delayMillis(3000L)
                .build());
        return ResultWrapper.success();
    }

    @ApiOperation("IM消息推送测试")
    @GetMapping("/im")
    public Result pushIm() throws PushException {
        ImMsg imMsg = new ImMsg().setMsgTemplate(MsgTemplate.IM_TEMPLATE)
                .setType(ImMsgType.TEXT)
                .setSendType(ImSendType.PERSON)
                .setFrom("test_user1");
        MultiValueMap<PushType, String> receivers = new LinkedMultiValueMap<>();
        receivers.add(PushType.IM, "test_user2");
        //推送IM消息
        pushTemplate.push(imMsg, PushProps.builder()
                .receivers(receivers)
                .build());
        //延迟推送IM消息
        pushTemplate.push(imMsg, PushProps.builder()
                .receivers(receivers)
                .delayed(true)
                .delayMillis(3000L)
                .build());
        return ResultWrapper.success();
    }

    @ApiOperation("创建网易云通信ID")
    @PostMapping("/im/accid")
    public Result createAccId(@RequestBody AccIdCreate accIdCreate) throws PushException {
        yunxinProvider.createAccId(accIdCreate);
        return ResultWrapper.success();
    }

    @ApiOperation("重试消息测试接口")
    @GetMapping("/mq/retry/test")
    public Result testMqRetry() throws MqRetryException {
        RetryProps retryProps = RetryProps.builder()
                .retryable(true)
                .queueEnum(SampleQueue.SAMPLE_TTL_QUEUE)
                .strategy(RetryStrategy.PERIODIC).firstDelayMillis(5000L).intervalMillis(2000L).times(new AtomicInteger(4))
//                .strategy(RetryStrategy.PROGRESSIVE).delays(Lists.newArrayList(2000L, 4000L, 6000L))
                .build();
        retryableSender.sendRetryableMessage("重试消息测试内容", retryProps);
        log.info("开始发送重试消息");
        return ResultWrapper.success();
    }
}
