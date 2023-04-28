package com.example.amigos_mango;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AmigosMangoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigosMangoApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(StudentRepo studentRepo, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address("India","coimbatore","IND626");
			String email = "gokul@gmail.com";
			Student student = new Student(
					"gokul",
					"raj",
					email,
					Gender.MALE,
					address,
					List.of("computer Science","Math"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			//usingMongoTemplate(studentRepo, mongoTemplate, email, student);
			studentRepo.findStudentByEmail(email).ifPresentOrElse(s -> {
				System.out.println(student + "\nAlready exist");
			},()->{System.out.println("Inserting Student :\n"+ student);
				studentRepo.insert(student);});
		};

	}
	private static void usingMongoTemplate(StudentRepo studentRepo, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		List<Student> students= mongoTemplate.find(query,Student.class);
		if(students.size()>1){
			throw new IllegalStateException("Found many student with email"+ email);
		}
		if(students.isEmpty()){
			System.out.println("Inserting Student :\n"+ student);
			studentRepo.insert(student);
		}else {
			System.out.println(student + "\nAlready exist");
		}
	}

}
