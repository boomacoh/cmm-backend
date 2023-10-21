package com.cuemymusic.club.service.container.app.config.beans;


import com.cuemymusic.club.service.authentication.entities.AuthUser;
import com.cuemymusic.club.service.authentication.mapper.AuthenticationMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
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

import java.awt.image.BufferedImage;
import java.util.Properties;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.cuemymusic.club.service")
@AllArgsConstructor
public class BeanConfiguration {
    private final UserRepository repository;
    private final AuthenticationMapper authenticationMapper;
    @Bean
    public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
        log.debug("Registering BufferedImage converter");
        return new BufferedImageHttpMessageConverter();
    }
    private static final String MQTT_PUBLISHER_ID = "spring-server";
    private static final String MQTT_SERVER_ADDRESS= "tcp://localhost:1883";
    @Bean
    public IMqttClient iMqttClient(){
            IMqttClient instance = null;
            try{
                instance = new MqttClient(MQTT_SERVER_ADDRESS, MQTT_PUBLISHER_ID);

                MqttConnectOptions options = new MqttConnectOptions();
                options.setAutomaticReconnect(true);
                options.setCleanSession(true);
                options.setConnectionTimeout(10);

                if (!instance.isConnected()) {
                    instance.connect(options);
                }
            }catch (Exception e){
                log.error("Couldn't Connect To Broker");
            }
            return instance;
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
