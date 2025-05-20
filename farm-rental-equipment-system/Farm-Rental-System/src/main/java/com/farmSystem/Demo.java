package com.farmSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.farmSystem.DTO.BookingsDTO;
import com.farmSystem.Repository.BookingsRepository;
import com.farmSystem.Util.BookingsMapper;
import com.farmSystem.entity.Bookings;
import com.farmSystem.service.EmailService;

import jakarta.transaction.Transactional;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:config.properties")
public class Demo implements WebMvcConfigurer,CommandLineRunner{
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private BookingsRepository bookingRepository;
	@Autowired
	private BookingsMapper bookingMapper;
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
