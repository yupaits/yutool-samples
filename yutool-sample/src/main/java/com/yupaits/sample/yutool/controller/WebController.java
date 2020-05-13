package com.yupaits.sample.yutool.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author yupaits
 * @date 2019/8/12
 */
@Controller
@ApiIgnore
public class WebController {

    @ApiOperation("Web网页消息测试页面")
    @GetMapping("/web_msg")
    public String webMsgPage() {
        return "web_msg";
    }
}
