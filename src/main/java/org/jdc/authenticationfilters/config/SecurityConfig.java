package org.jdc.authenticationfilters.config;

import org.jdc.authenticationfilters.filter.AuthenticationLoggingFilter;
import org.jdc.authenticationfilters.filter.RequestValidationFilter;
import org.jdc.authenticationfilters.filter.StaticKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private StaticKeyAuthenticationFilter filter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http.httpBasic(Customizer.withDefaults());
//        http.addFilterBefore(new RequestValidationFilter(),
//                BasicAuthenticationFilter.class)
//                        .addFilterAfter(new AuthenticationLoggingFilter(),
//                                BasicAuthenticationFilter.class);
        http.addFilterAt(filter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
        return http.build();
    }
}
