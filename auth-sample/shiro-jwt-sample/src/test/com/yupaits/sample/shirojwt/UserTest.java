package com.yupaits.sample.shirojwt;

import com.yupaits.sample.shirojwt.utils.EncryptUtils;
import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yupaits
 * @date 2019/8/22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1_addUser() {
        String salt = RandomStringUtils.randomAlphanumeric(12);
        String password = EncryptUtils.encryptPassword("password", salt);
        User user = User.builder()
                .username("yupaits")
                .salt(salt)
                .password(password)
                .build();
        Assert.assertTrue(userService.save(user));
    }
}
