package com.cuemymusic.user.service.container.app.config.beans;
import com.cuemymusic.user.service.domain.ports.output.repository.common.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.authentication.entities.AuthUser;
import com.cuemymusic.user.service.authentication.mapper.AuthenticationMapper;
import com.cuemymusic.user.service.domain.UserDomainService;
import com.cuemymusic.user.service.domain.UserDomainServiceImpl;
import com.cuemymusic.user.service.domain.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.cuemymusic.user.service")
@AllArgsConstructor
public class BeanConfiguration {
    private final UserRepository repository;
    private final AuthenticationMapper authenticationMapper;

    @Bean
    public UserDomainService userDomainService(){
        return  new UserDomainServiceImpl();
    }
    @Bean
    public UserDetailsService userDetailsService() {

        return username -> serviceImpl(username);

    }
    private UserDetails serviceImpl(String username){

        User user = repository.findByEmail(username);
        AuthUser authUser =  authenticationMapper.domainUserToAuthUser(
                user,
                user.getTokens()
                        .stream()
                        .map( e -> authenticationMapper.domainTokenToAuthToken(e))
                        .toList()
        );
        authUser.getTokens()
                .stream()
                .forEach(
                        e -> e.setUser(authUser)
                );
        log.warn("Auth USER " + authUser.getTokens());
        return authUser;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("auk.sw.solutions@gmail.com");
        mailSender.setPassword("jskyiovmaucqsafa");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
