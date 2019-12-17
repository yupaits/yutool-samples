package com.yupaits.sample.xxljob.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yupaits
 * @date 2019/7/13
 */
@JobHandler(value = "sampleJobHandler")
@Component
public class SampleJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World!");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at: " + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }
}
