package com.cuemymusic.reports.service.container.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.cuemymusic.firmware.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.cuemymusic.firmware.service")
@EnableJpaRepositories(basePackages = "com.cuemymusic.firmware.service.dataaccess")
public class ReportsApplication {
    public static void main(String[] args) {
        SpringApplication.
                run(ReportsApplication.class,args);
    }
}

