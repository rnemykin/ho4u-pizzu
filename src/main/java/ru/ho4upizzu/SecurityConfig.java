package ru.ho4upizzu;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("pizza_lover")
                .password("$2a$10$SZyRZ.ASrwG5SqSJA8t4kO7tpxaTb8tOSJe7D1aMNoOp/ckRULgwC")
                .roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                .and()
                    .formLogin()
                    .successForwardUrl("/admin-page")
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
    }
}
