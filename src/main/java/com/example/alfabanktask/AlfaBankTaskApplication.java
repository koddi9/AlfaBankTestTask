package com.example.alfabanktask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

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
