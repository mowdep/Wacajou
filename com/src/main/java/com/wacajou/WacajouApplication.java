package com.wacajou;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.wacajou.architecture.CreateArchi;
import com.wacajou.architecture.CreateConfigFile;

@SpringBootApplication
public class WacajouApplication {



	public static void main(String[] args) {
		/* Launch application */
		ApplicationContext ctx = SpringApplication.run(
				WacajouApplication.class, args);
		getAllBean(ctx);
		CreateConfigFile cf = new CreateConfigFile();
		cf.getFile();
	 	CreateArchi archi = new CreateArchi(cf.getPath());
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
