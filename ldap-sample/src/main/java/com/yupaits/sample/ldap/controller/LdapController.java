package com.yupaits.sample.ldap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yupaits
 * @date 2020/1/13
 */
@RestController
public class LdapController {

    private final LdapTemplate ldapTemplate;

    @Autowired
    public LdapController(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }
}
