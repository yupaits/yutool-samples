package com.yupaits.sample.oauth2.authorization.service.impl;

import com.yupaits.sample.oauth2.authorization.dto.UserDetailsImpl;
import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author yupaits
 * @date 2019/8/26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户名 %s 不存在", username));
        }
        UserDetailsImpl userDetails = new UserDetailsImpl();
        BeanUtils.copyProperties(user, userDetails);
        userDetails.setUserId(String.valueOf(user.getId()));
        userDetails.setRoles(userService.getUserRoles(user.getId()));
        return userDetails;
    }
}
