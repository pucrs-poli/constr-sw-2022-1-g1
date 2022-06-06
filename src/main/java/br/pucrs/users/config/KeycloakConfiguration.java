package br.pucrs.users.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }
}
