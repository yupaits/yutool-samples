package com.yupaits.sample.ldap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.util.CollectionUtils;

import javax.naming.Name;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Ldap分组信息
 * @author yupaits
 * @date 2020/1/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entry(objectClasses = {"top", "groupOfUniqueNames"}, base = "ou=Group")
public class LdapGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Name dn;

    @Attribute(name = "cn")
    @DnAttribute("cn")
    private String name;

    @Attribute(name = "uniqueMember")
    private Set<Name> members;

    public void addMember(Name member) {
        if (members == null) {
            members = new HashSet<>();
        }
        members.add(member);
    }

    public void removeMember(Name member) {
        if (!CollectionUtils.isEmpty(members)) {
            members.remove(member);
        }
    }
}
