package com.yupaits.sample.ldap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.io.Serializable;

/**
 * Ldap用户信息
 * @author yupaits
 * @date 2020/1/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(objectClasses = {"top", "inetOrgPerson"}, base = "ou=People")
public class LdapPerson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Name dn;

    private String gidNumber;

    private String uid;

    private String uidNumber;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "cn")
    private String commonName;

    private String mail;

    private String givenName;

    private String displayName;

    private String userPassword;
}
