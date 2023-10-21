package com.cuemymusic.user.service.container.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.cuemymusic.user.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.cuemymusic.user.service")
@EnableJpaRepositories(basePackages = "com.cuemymusic.user.service.dataaccess")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.
                run(UserApplication.class,args);
    }
}

