package com.group2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author YangPx
 * @create 2021-09-27 14:35
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9001.class, args);
    }
}
