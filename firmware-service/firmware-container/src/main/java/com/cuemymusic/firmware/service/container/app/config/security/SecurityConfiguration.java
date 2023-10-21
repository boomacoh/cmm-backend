package com.cuemymusic.firmware.service.container.app.config.security;


import com.cuemymusic.firmware.service.authentication.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.cuemymusic.user.service.domain.valueobject.Permission.*;
import static com.cuemymusic.user.service.domain.valueobject.Role.*;
import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()

                //////////////////////////////////////////////////////////////////

                // Clubs Administration RULES
                .requestMatchers("/api/admin/firmware/**").hasAnyRole(ADMIN.name())
                .requestMatchers(GET, "/api/admin/firmware/").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(GET, "/api/admin/firmware/**").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(GET, "/api/admin/firmware/promote/**").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(GET, "/api/admin/firmware/current").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/api/admin/firmware/").hasAnyAuthority(ADMIN_CREATE.name())

                /////////////////////////////////////////////////////////////////////////////////
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        ;

        return http.build();
    }
}
