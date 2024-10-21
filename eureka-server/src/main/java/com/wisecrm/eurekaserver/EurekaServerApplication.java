package com.wisecrm.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.*;
import org.springframework.cloud.netflix.eureka.server.*;

@SpringBootApplication
@EnableEurekaServer
@RefreshScope
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
