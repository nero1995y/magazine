package com.shop.magazine.global.service;

import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.service.UserService;
import com.shop.magazine.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUsername(username);

        if(username != null) {
            return new PrincipalDetails(user);
        }

        return null;
    }
}
