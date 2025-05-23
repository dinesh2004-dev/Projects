package com.farmSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:config.properties")
public class Demo implements WebMvcConfigurer,CommandLineRunner{
	
	
	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
		
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**");
				
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
