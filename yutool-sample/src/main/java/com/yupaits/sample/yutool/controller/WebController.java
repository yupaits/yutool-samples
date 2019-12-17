package com.yupaits.sample.yutool.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yupaits
 * @date 2019/8/12
 */
@Controller
@Api(tags = "页面路由")
public class WebController {

    @ApiOperation("Web网页消息测试页面")
    @GetMapping("/web_msg")
    public String webMsgPage() {
        return "web_msg";
    }
}
