package com.yupaits.sample.seata.account.controller;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@Slf4j
@RestController
public class AccountController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/account")
    public String account(@RequestParam String userId, @RequestParam int money) {
        log.info("Account Service - xid: {}", RootContext.getXID());
        if (RandomUtils.nextBoolean()) {
            throw new RuntimeException("This is a mock exception.");
        }
        int result = jdbcTemplate.update("update account_tbl set money = money - ? where user_id = ?", money, userId);
        log.info("Account Service End");
        if (result == 1) {
            return SUCCESS;
        }
        return FAIL;
    }
}
