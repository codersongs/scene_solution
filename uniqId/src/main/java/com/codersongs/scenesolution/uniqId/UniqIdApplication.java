package com.codersongs.scenesolution.uniqId;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UniqIdApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniqIdApplication.class, args);
    }
}
