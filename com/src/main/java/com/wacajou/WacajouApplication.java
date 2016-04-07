package com.wacajou;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.wacajou.module.model.DAOModule;

@SpringBootApplication
public class WacajouApplication {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WacajouApplication.class, args);
		// getAllBean(ctx);
	}

	private static void getAllBean(ApplicationContext ctx) {
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

	
}
