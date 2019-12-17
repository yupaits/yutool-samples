package com.yupaits.sample.seata.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@FeignClient(name = "order-service", url = "http://127.0.0.1:8132")
public interface OrderService {

    @PostMapping("/order")
    String order(@RequestParam String userId, @RequestParam String commodityCode, @RequestParam int orderCount);
}
