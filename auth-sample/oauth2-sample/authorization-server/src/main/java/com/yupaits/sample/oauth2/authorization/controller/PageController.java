package com.yupaits.sample.oauth2.authorization.controller;

import com.yupaits.sample.oauth2.authorization.constant.SecurityConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @author yupaits
 * @date 2019/8/24
 */
@Controller
@FrameworkEndpoint
@SessionAttributes(SecurityConstants.SESSION_ATTRIBUTES)
public class PageController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "用户名或密码有误！");
        }
        return "login";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/oauth/confirm_access")
    public String confirmAccessPage(Map<String, Object> model, HttpServletRequest request) {
        if (model.containsKey(SecurityConstants.SESSION_ATTRIBUTES)) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get(SecurityConstants.SESSION_ATTRIBUTES);
            Set<String> scopes = authorizationRequest.getScope();
            if (CollectionUtils.isNotEmpty(scopes)) {
                model.put("scopes", scopes);
            }
        }
        return "confirmAccess";
    }

    @GetMapping("/oauth/error")
    public String handleError(Map<String, Object> model, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorInfo;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorInfo = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorInfo = error.toString();
        }
        model.put("errorInfo", errorInfo);
        return "oauthError";
    }
}
