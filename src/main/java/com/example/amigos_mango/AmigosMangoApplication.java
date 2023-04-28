package com.example.amigos_mango;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AmigosMangoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigosMangoApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(StudentRepo studentRepo){
		return args -> {
			Address address = new Address("India","coimbatore","IND626");
			Student student = new Student(
					"gokul",
					"raj",
					"gokul@gmail.com",
					Gender.MALE,
					address,
					List.of("computer Science","Math"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			studentRepo.insert(student);
		};

	}

}
