package com.farmSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.farmSystem.service.EmailService;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:config.properties")
public class Demo implements WebMvcConfigurer,CommandLineRunner{
	
	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
		
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**");
	}

	@Override
	public void run(String... args) throws Exception {
		
//		String to = "dineshreddy1421@gmail.com";
//		
//		
//		emailService.sendWelcomeMessage(to);
	}

}
