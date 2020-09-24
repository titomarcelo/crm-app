package com.tmao.crm.commons.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/", "/*cutomers*/**").access("hasRole('USER')")
                .and()
                .formLogin();

        // http
        // .csrf().disable()
        // .authorizeRequests()
        // .antMatchers("/customers/**").hasRole("ADMIN")
        // .antMatchers("/anonymous*").anonymous()
        // .antMatchers("/login*").permitAll()
        // .anyRequest().authenticated()
        // .and()
        // .formLogin()
        // .loginPage("/login.html")
        // .loginProcessingUrl("/perform_login")
        // .defaultSuccessUrl("/index.jsp", true)
        // .failureUrl("/login.html?error=true")
        // // .failureHandler(authenticationFailureHandler())
        // .and()
        // .logout()
        // .logoutUrl("/perform_logout")
        // .deleteCookies("JSESSIONID")
        // // .logoutSuccessHandler(logoutSuccessHandler())
        // ;
        //
        // http.authorizeRequests()
        // .antMatchers("/login").permitAll()
        // .antMatchers("/", "/*cutomers*/**").access("hasRole('USER')")
        // .and()
        // .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
