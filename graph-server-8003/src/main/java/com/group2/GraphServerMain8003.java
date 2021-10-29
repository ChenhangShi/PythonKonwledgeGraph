package com.group2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableNeo4jRepositories("com.group2.repository")
//@EntityScan(basePackages = "com.group2.entity")
public class GraphServerMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(GraphServerMain8003.class,args);
    }
}
