package com.insmart.app;

import com.insmart.app.utils.Bcript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@EnableWebMvc
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    Bcript bcript;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable().cors().disable().csrf().disable();
//        http.servletApi().rolePrefix("ROLE_");
//        http.authorizeRequests()
//                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin")
//                .password(bcript.passwordEncoder().encode("123456"))
//                .authorities("ADMIN");
//    }
}
