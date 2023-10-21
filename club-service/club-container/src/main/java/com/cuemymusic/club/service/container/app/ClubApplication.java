package com.cuemymusic.club.service.container.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.cuemymusic.club.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.cuemymusic.club.service")
@EnableJpaRepositories(basePackages = "com.cuemymusic.club.service.dataaccess")
public class ClubApplication {
    public static void main(String[] args) {
        SpringApplication.
                run(ClubApplication.class,args);
    }
}

