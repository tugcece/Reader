package com.patika.readernotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class ReaderNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReaderNotificationServiceApplication.class, args);
    }

}
