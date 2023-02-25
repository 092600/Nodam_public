package com.nodam.nodam_public.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.nodam.nodam_public.domain.user.role.UserRole;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class NodamSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()

            .authorizeHttpRequests()
            .mvcMatchers("/community/post/write").authenticated()
            .mvcMatchers("/api/v4/community/post").authenticated()
            // .mvcMatchers("/api/v4/nosmoking").authenticated()
            // .mvcMatchers("/api/v4/noSmokingStop").authenticated()
            .anyRequest().permitAll();
        
        http
            .formLogin()
            .loginProcessingUrl("/accounts/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .failureHandler(authenticationFailureHandler)

            .loginPage("/accounts/login")
            .defaultSuccessUrl("/");

        http.logout()
            .logoutUrl("/accounts/logout")
            .logoutSuccessUrl("/");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/favicon.ico", "/resources/**", "/error", "/resources/templates/**");
    }


}
