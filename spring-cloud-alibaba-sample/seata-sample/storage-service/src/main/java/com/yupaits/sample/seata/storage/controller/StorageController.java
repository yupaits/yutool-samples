package com.yupaits.sample.seata.storage.controller;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@Slf4j
@RestController
public class StorageController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StorageController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/storage/{commodityCode}/{count}")
    public String storage(@PathVariable String commodityCode, @PathVariable int count) {
        log.info("Storage Service Begin - xid: {}", RootContext.getXID());
        int result = jdbcTemplate.update(
                "update storage_tbl set count = count - ? where commodity_code = ?",
                count, commodityCode
        );
        log.info("Storage Service End");
        if (result == 1) {
            return SUCCESS;
        }
        return FAIL;
    }
}
