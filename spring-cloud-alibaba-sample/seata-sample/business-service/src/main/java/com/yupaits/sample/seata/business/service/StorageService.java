package com.yupaits.sample.seata.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@FeignClient(name = "storage-service", url = "http://127.0.0.1:8133")
public interface StorageService {

    @GetMapping("/storage/{commodityCode}/{count}")
    String storage(@PathVariable String commodityCode, @PathVariable int count);
}
