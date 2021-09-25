package com.group2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YangPx
 * @create 2021-09-25 20:40
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8002.class, args);
    }
}
