package com.wacajou;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan({"com.wacajou"})
public class WacajouApplication {

	public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(WacajouApplication.class, args);
		 System.out.println("Let's inspect the beans provided by Spring Boot:");

	        String[] beanNames = ctx.getBeanDefinitionNames();
	        Arrays.sort(beanNames);
	        for (String beanName : beanNames) {
	            System.out.println(beanName);
	        }
	}
}
