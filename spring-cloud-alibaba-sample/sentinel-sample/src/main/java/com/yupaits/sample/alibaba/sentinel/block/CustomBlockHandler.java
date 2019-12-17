package com.yupaits.sample.alibaba.sentinel.block;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @author yupaits
 * @date 2019/8/27
 */
@Slf4j
public class CustomBlockHandler {

    public static SentinelClientHttpResponse handleException(HttpRequest request, byte[] body,
                                                             ClientHttpRequestExecution execution, BlockException ex) {
        log.error("Oops: " + ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("custom block info");
    }
}
