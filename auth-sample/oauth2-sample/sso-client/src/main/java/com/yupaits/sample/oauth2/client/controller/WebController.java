package com.yupaits.sample.oauth2.client.controller;

import com.yupaits.yutool.commons.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yupaits
 * @date 2019/8/26
 */
@Controller
public class WebController {

    private final OAuth2RestTemplate restTemplate;

    @Autowired
    public WebController(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/index")
    public String indexPage(@RequestParam(required = false) String code, Model model) {
        if (code != null) {
            return "redirect:/index";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Map> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Result> response = restTemplate.exchange(PostController.POST_SERVER_URL + "/list", HttpMethod.POST,  entity, Result.class);
        Result result = response.getBody();
        if (result != null && result.isSuccess()) {
            model.addAttribute("posts", result.getData());
        }
        return "index";
    }

    @GetMapping("/secured-page")
    public String securedPage() {
        return "securedPage";
    }
}
