package com.yupaits.sample.seata.business.controller;

import com.yupaits.sample.seata.business.service.OrderService;
import com.yupaits.sample.seata.business.service.StorageService;
import io.seata.common.util.StringUtils;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@Slf4j
@RestController
public class HomeController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private static final String USER_ID = "U100001";
    private static final String COMMODITY_CODE = "C00321";
    private static final int ORDER_COUNT = 2;

    private final RestTemplate restTemplate;
    private final OrderService orderService;
    private final StorageService storageService;

    @Autowired
    public HomeController(RestTemplate restTemplate, OrderService orderService, StorageService storageService) {
        this.restTemplate = restTemplate;
        this.orderService = orderService;
        this.storageService = storageService;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "seata-tx")
    @GetMapping("/seata/rest")
    public String rest() {
        String storageUrl = String.format("http://127.0.0.1:8133/storage/%s/%d", COMMODITY_CODE, ORDER_COUNT);
        String orderUrl = String.format("http://127.0.0.1:8132/order?userId=%s&commodityCode=%s&orderCount=%d", USER_ID, COMMODITY_CODE, ORDER_COUNT);
        String result = restTemplate.getForObject(storageUrl, String.class);
        if (!StringUtils.equals(SUCCESS, result)) {
            throw new RuntimeException();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(orderUrl, entity, String.class);
        result = response.getBody();
        if (!StringUtils.equals(SUCCESS, result)) {
            throw new RuntimeException();
        }
        return SUCCESS;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "seata-tx")
    @GetMapping("/seata/feign")
    public String feign() {
        String result = storageService.storage(COMMODITY_CODE, ORDER_COUNT);
        if (!StringUtils.equals(SUCCESS, result)) {
            throw new RuntimeException();
        }
        result = orderService.order(USER_ID, COMMODITY_CODE, ORDER_COUNT);
        if (!StringUtils.equals(SUCCESS, result)) {
            throw new RuntimeException();
        }
        return SUCCESS;
    }
}
