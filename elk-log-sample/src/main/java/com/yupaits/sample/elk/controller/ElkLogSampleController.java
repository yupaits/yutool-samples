package com.yupaits.sample.elk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2019/7/13
 */
@Slf4j
@RestController
public class ElkLogSampleController {
    private static final String OK = "ok";

    @PostMapping("/log")
    public String newLog() {
        log.debug("Debug log.");
        log.info("Info log.");
        log.warn("Warn log.");
        log.error("Error log.");
        return OK;
    }
}
