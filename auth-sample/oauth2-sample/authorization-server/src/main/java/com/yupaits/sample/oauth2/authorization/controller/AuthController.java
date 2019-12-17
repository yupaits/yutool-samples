package com.yupaits.sample.oauth2.authorization.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author yupaits
 * @date 2019/8/24
 */
@Controller
public class AuthController {

    @GetMapping("/user")
    @ResponseBody
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/oauth/logout")
    public String logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        return "redirect:/index";
    }
}
