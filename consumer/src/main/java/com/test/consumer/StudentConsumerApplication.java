package com.test.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.test.consumer")
public class StudentConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentConsumerApplication.class, args);

    }
}
