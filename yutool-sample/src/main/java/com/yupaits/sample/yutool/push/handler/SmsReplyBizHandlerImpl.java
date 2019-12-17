package com.yupaits.sample.yutool.push.handler;

import com.yupaits.yutool.push.model.reply.SmsReply;
import com.yupaits.yutool.push.support.sms.SmsReplyBizHandler;
import org.springframework.stereotype.Service;

/**
 * 短信回复业务处理实现
 * @author yupaits
 * @date 2019/8/10
 */
@Service
public class SmsReplyBizHandlerImpl implements SmsReplyBizHandler {

    @Override
    public void handleSmsReply(SmsReply smsReply) {
        //短信回复业务处理
    }
}
