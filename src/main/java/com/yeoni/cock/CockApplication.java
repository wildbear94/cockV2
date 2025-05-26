package com.yeoni.cock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.yeoni.cock.mapper")
public class CockApplication {
    public static void main(String[] args) {
        SpringApplication.run(CockApplication.class, args);
    }
} 