package com.mossad.mmigdal.spring.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class WebMain {


    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }

}
