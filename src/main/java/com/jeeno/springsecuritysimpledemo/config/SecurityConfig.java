package com.jeeno.springsecuritysimpledemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

/**
 * @author 杜家浩
 * @version 1.0.0
 * @date 2020/1/8 16:54
 */
@Slf4j
@Configuration
// 开启security功能（默认导入security包即开启）
@EnableWebSecurity
// 开启全局方法的权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入自定义的实现， UserDetailServiceImpl
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     *     注入自定义的密码加密器
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 注入自定义的无权限处理器
     */
    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                // 配置自定义的UserDetailsService实现,从数据库中提取用户信息
                userDetailsService(userDetailsService)
                // 配置自定义的密码加密器实现，security会将登录传过来的password加密后与数据库中字段比对
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // security 默认提供了两种登录页面basic和form，由于一般都是自定义的登录页面，所以下面只列了此种方式
        // 表单登录
        http.formLogin()
                // 自定义的登录页面
            .loginPage("/loginPage")
                // 登录表单提交路径
            .loginProcessingUrl("/login")
                // 自定义登录成功时的逻辑，不再举例
//                .successHandler()
//                // 自定义登录失败时的逻辑，不再举例
//                .failureHandler()
                // 设置登录相关的url 放行，不然会把请求无限循环跳到/loginPage
            .permitAll()
            .and()
            .authorizeRequests()
                // 针对首页的路径放行
                .antMatchers("/", "/index")
                .permitAll()
                // 设置其他路径均要认证后才能访问
                .anyRequest().authenticated();

        // 自定义的无权限处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        // 禁用csrf防护功能，默认开启
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
