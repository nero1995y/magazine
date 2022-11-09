package com.shop.magazine.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSecurityService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String PasswordEncoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public String GetRole() {
        String defaultRole = "ROLE_USER";

        return defaultRole;
    }
}
