package com.wacajou;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration()
public class WacajouApplication {
	@Autowired
    Environment env;
	
	public static void main(String[] args) {
		
		/* Launch application */
		ApplicationContext ctx = SpringApplication.run(WacajouApplication.class, args);
		getAllBean(ctx);
	}

	@Bean
	public String ConfigPath(){
		System.out.println("Chemin actuel de configuration : " + env.getProperty("config.path") + "\\config\\");
		return env.getProperty("config.path");
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
