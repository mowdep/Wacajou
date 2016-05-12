package com.wacajou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("one")
public class TestClass {

    @Autowired
    Environment env;

    @Bean
    public boolean test() {
        System.out.println(env.getProperty("test.one"));
        return true;
        //TODO Create db profile with connexion on startup
    }
}