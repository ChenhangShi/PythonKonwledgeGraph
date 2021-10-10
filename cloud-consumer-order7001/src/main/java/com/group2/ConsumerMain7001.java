package com.group2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YangPx
 * @create 2021-09-25 19:29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain7001.class, args);
    }
}
