package com.example.alfabanktask;

import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
public class AlfaBankTaskApplication {


    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(AlfaBankTaskApplication.class, args);
//        System.out.println(
//                Arrays.toString(context.getBeanDefinitionNames())
//        );
    }

}
