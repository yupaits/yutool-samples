package com.yupaits.sample.springsecurityjwt.security;

import com.yupaits.sample.springsecurityjwt.dto.UserDetailsImpl;
import com.yupaits.sample.user.model.User;
import com.yupaits.sample.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService实现
 * @author yupaits
 * @date 2019/8/23
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
