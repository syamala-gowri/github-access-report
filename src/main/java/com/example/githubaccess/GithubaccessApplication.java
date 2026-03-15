package com.example.githubaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.githubaccess")
public class GithubaccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubaccessApplication.class, args);
    }
}