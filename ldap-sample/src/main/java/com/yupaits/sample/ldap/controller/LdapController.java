package com.yupaits.sample.ldap.controller;

import com.yupaits.sample.ldap.model.LdapGroup;
import com.yupaits.sample.ldap.model.LdapPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

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

    @GetMapping("/person/list")
    public List<LdapPerson> list() {
        return ldapTemplate.findAll(LdapPerson.class);
    }

    @GetMapping("/person/{uid}")
    public LdapPerson getByUid(@PathVariable String uid) {
        return ldapTemplate.findOne(query().where("uid").is(uid), LdapPerson.class);
    }

    @GetMapping("/person/auth")
    public boolean checkAuth(@RequestParam String username, @RequestParam String password) {
        ldapTemplate.authenticate(query().where("uid").is(username), password);
        return true;
    }

    @GetMapping("/group/list")
    public List<LdapGroup> groupList() {
        return ldapTemplate.findAll(LdapGroup.class);
    }

    @GetMapping("/group/{groupName}")
    public LdapGroup getByName(@PathVariable String groupName) {
        return ldapTemplate.findOne(query().where("cn").is(groupName), LdapGroup.class);
    }
}
