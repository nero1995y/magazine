package com.shop.magazine.domain.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserSecurityServiceTest {

    @Autowired
    private UserSecurityService userSecurityService;

    @Test
    void passwordEncode() {

        // given
        String password = "password";

        // when
        String encoder = userSecurityService.PasswordEncoder(password);

        // then
        assertThat(encoder).isNotEqualTo(password);

    }

    @Test
    void GetRole(){

        // given when
        String role = userSecurityService.GetRole();

        // then
        assertThat(role).isEqualTo("ROLE_USER");
    }

}