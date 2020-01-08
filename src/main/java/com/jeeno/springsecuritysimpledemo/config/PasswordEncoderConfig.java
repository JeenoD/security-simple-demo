package com.jeeno.springsecuritysimpledemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义的密码器
 * @author Jeeno
 * @version 1.0.0
 * @date 2020/1/8 17:17
 */
@Component
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
